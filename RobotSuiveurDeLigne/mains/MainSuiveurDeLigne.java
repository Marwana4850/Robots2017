package mains;

import lejos.nxt.Motor;
import lejos.nxt.NXTRegulatedMotor;
import lejos.nxt.SensorPort;
import lejos.nxt.UltrasonicSensor;
import lejos.util.Delay;
import tools.ChangeSquare;
import tools.ToolOne;

import java.util.ArrayList;

import lejos.nxt.Button;
import lejos.nxt.LCD;
import lejos.nxt.LightSensor;



public class MainSuiveurDeLigne {

	public static void main(String[] args) {
		
		LightSensor lightG = new LightSensor(SensorPort.S4); //capteur lumineux gauche
		LightSensor lightD = new LightSensor(SensorPort.S3); //capteur lumineux droit
		Integer LGauche = lightG.getNormalizedLightValue();
		Integer LDroite = lightD.getNormalizedLightValue();
		Integer valeurSeuilNoir = 497;
		
		NXTRegulatedMotor motorG = Motor.C;
		NXTRegulatedMotor motorD = Motor.B;
		Integer VitesseMoteurs = 250;
		Integer VitesseMoteurDroit = 200;
		Integer VitesseMoteurGauche = 200;
		Integer VitesseGauche = 0;
		Integer VitesseDroite = 0;
		
		ToolOne PID1 = new ToolOne();
		ToolOne PID2 = new ToolOne();
		Integer EcartALaValeurNoire = 0;
		Integer EcartARetenir = 0; //deviendra EcartPrecedent
		Integer k = 1;
		Integer valPID = 0;
		
		
		while(true){
			
			LGauche = lightG.getNormalizedLightValue();
			LDroite = lightD.getNormalizedLightValue();
			motorG.setSpeed(VitesseMoteurs);
			motorD.setSpeed(VitesseMoteurs);
			motorG.forward();
			motorD.forward();
			
			if(LGauche > valeurSeuilNoir && LDroite > valeurSeuilNoir){
				
				//aller tout droit
				motorG.setSpeed(VitesseMoteurs);
				motorD.setSpeed(VitesseMoteurs);	
			}
			else if(LDroite < valeurSeuilNoir){ //on voit du blanc à droite
				
				//tourner à gauche		
				while(LDroite < valeurSeuilNoir){
					//PID
					EcartARetenir = PID1.getEcartALaValeurNoire(LDroite, valeurSeuilNoir);
					
					valPID = PID1.valeurPID();
					PID1.setEcartPrecedent(EcartARetenir);
					
					//Vg > Vd
					VitesseGauche = valPID*k + VitesseMoteurDroit;
					motorD.setSpeed(VitesseMoteurDroit);
					motorG.setSpeed(VitesseGauche);
					
					//on remesure
					LDroite = lightD.getNormalizedLightValue();
				}
				PID1 = new ToolOne(); //on remet à 0 les valeurs
			}
			else if(LGauche < valeurSeuilNoir){ //on voit du blanc à gauche
				
				//tourner à droite
				while(LGauche < valeurSeuilNoir){
					//PID
					EcartARetenir = PID2.getEcartALaValeurNoire(LGauche, valeurSeuilNoir);
					
					valPID = PID2.valeurPID();
					PID2.setEcartPrecedent(EcartARetenir);
					
					//Vd > Vg
					VitesseDroite = valPID*k + VitesseMoteurGauche;
					motorD.setSpeed(VitesseDroite);
					motorG.setSpeed(VitesseMoteurGauche);
					
					//on remesure
					LGauche = lightG.getNormalizedLightValue();
				}
				PID2 = new ToolOne(); //on remet à 0 les valeurs
			}
			
			
			
		}
	}
}
