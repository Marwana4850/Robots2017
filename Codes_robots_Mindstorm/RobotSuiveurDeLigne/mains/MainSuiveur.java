package mains;

import lejos.nxt.Motor;
import lejos.nxt.NXTRegulatedMotor;
import lejos.nxt.SensorPort;
import lejos.nxt.UltrasonicSensor;
import lejos.robotics.navigation.DifferentialPilot;
import lejos.util.Delay;
import tools.ChangeSquare;
import tools.PID;

import lejos.nxt.Button;
import lejos.nxt.LCD;
import lejos.nxt.LightSensor;



@SuppressWarnings("unused")
public class MainSuiveur { //Pour suivre une ligne NOIRE sur fond BLANC

	public static void main(String[] args) {
		
		Button.waitForAnyPress();	
		LCD.clear();
		
		
		/*
		 * A changer en fonction de la constitution m�canique du robot
		 */
		
		LightSensor lightG = new LightSensor(SensorPort.S4); //capteur lumineux gauche
		LightSensor lightD = new LightSensor(SensorPort.S3); //capteur lumineux droit
		NXTRegulatedMotor motorG = Motor.C;
		NXTRegulatedMotor motorD = Motor.B;
		Double wheelDiameter = 55.5; //diam�tre des roues
		Double trackWidth = (double) 165; //�cart entre les roues 
										  //(attention, � r�gler assez empiriquement jusqu'� avoir des rotations pr�cises du robot)
		
		/*
		 * Initialisations
		 */
		
		Integer LGauche = lightG.getNormalizedLightValue();
		Integer LDroite = lightD.getNormalizedLightValue();
		PID PID1 = new PID();
		PID PID2 = new PID();
		Integer EcartALaValeurNoire = 0;
		Integer EcartARetenir = 0; //deviendra EcartPrecedent
		Integer valPID = 0;
		DifferentialPilot pilote = new DifferentialPilot(wheelDiameter, trackWidth, motorG, motorD);
		//ou : ChangeSquare pilote = new ChangeSquare(wheelDiameter, trackWidth, motorG, motorD);
		boolean intersection = false; //indique si on a d�tect� une intersection
		Integer VitesseGauche = 0;
		Integer VitesseDroite = 0;
		
		
		/*
		 * Valeurs � r�gler si on le souhaite
		 */
		
		Integer valeurSeuilNoir = 505; //Valeur seuil de diff�renciation du noir et du blanc (au-dessus c'est blanc)
		Integer VitesseMoteurs = 250; //Vitesse des moteurs quand le robot va tout droit
		Integer VitesseMoteurDroit = 250; //Vitesse du moteur droit quand le robot tourne vers la droite
										//La valeur de la vitesse du moteur gauche est alors calcul�e par le PID1
		Integer VitesseMoteurGauche = 250; //Vitesse du moteur gauche quand le robot tourne vers la gauche
										//La valeur de la vitesse du moteur droit est alors calcul�e par le PID2
		Float k = (float) 0.6; 
		String ProchaineRoute = "l"; //direction � prendre � la prochaine intersection
		
		
		
		/*
		 * Boucle principale : d�placement du robot en suivant la ligne
		 */
		
		while(true){
			
			LGauche = lightG.getNormalizedLightValue();
			LDroite = lightD.getNormalizedLightValue();
			LCD.drawString(LGauche.toString(), 1, 1);
			LCD.drawString(LDroite.toString(), 1, 2);
			motorG.setSpeed(VitesseMoteurs);
			motorD.setSpeed(VitesseMoteurs);
			motorG.forward();
			motorD.forward();
			
			if(LGauche > valeurSeuilNoir && LDroite > valeurSeuilNoir && !intersection){ //hors intersection et on ne voit que du blanc
				LCD.clear();
				LCD.drawString("Blanc", 1, 3);
				//aller tout droit
				motorG.setSpeed(VitesseMoteurs);
				motorD.setSpeed(VitesseMoteurs);	
			}
			
			
			else if(LDroite < valeurSeuilNoir && LGauche > valeurSeuilNoir && !intersection){ //hors intersection et on voit du noir � droite
				LCD.clear();
				LCD.drawString("Noir droite", 1, 3);
				//tourner � gauche		
				
				while(LDroite < valeurSeuilNoir && LGauche > valeurSeuilNoir && !intersection){
					
					//PID
					EcartARetenir = PID1.getEcartALaValeurNoire(LDroite, valeurSeuilNoir);
					valPID = PID1.valeurPID();
					PID1.setEcartPrecedent(EcartARetenir);
					
					//Vg > Vd
					VitesseGauche = (int) (valPID*k + VitesseMoteurDroit);
					motorD.setSpeed(VitesseMoteurDroit);
					motorG.setSpeed(VitesseGauche);
					
					//on remesure
					LGauche = lightG.getNormalizedLightValue();
					LDroite = lightD.getNormalizedLightValue();
					
					if(LGauche < valeurSeuilNoir && LGauche < valeurSeuilNoir){ //d�tection d'intersection
						intersection = true;
					}
				}
				PID1 = new PID(); //on remet � 0 les valeurs
			}
			else if(LGauche < valeurSeuilNoir && LDroite > valeurSeuilNoir && !intersection){ //hors intersection et on voit du noir � gauche
				LCD.clear();
				LCD.drawString("Noir gauche", 1, 3);
				//tourner � droite
				
				while(LGauche < valeurSeuilNoir && LDroite > valeurSeuilNoir && !intersection){
					
					//PID
					EcartARetenir = PID2.getEcartALaValeurNoire(LGauche, valeurSeuilNoir);
					valPID = PID2.valeurPID();
					PID2.setEcartPrecedent(EcartARetenir);
					
					//Vd > Vg
					VitesseDroite = (int) (valPID*k + VitesseMoteurGauche);
					motorD.setSpeed(VitesseDroite);
					motorG.setSpeed(VitesseMoteurGauche);
					
					//on remesure
					LGauche = lightG.getNormalizedLightValue();
					LDroite = lightD.getNormalizedLightValue();
					
					if(LGauche < valeurSeuilNoir && LGauche < valeurSeuilNoir){ //d�tection d'intersection
						intersection = true;
					}
				}
				PID2 = new PID(); //on remet � 0 les valeurs
			}
			else if((LGauche < valeurSeuilNoir && LDroite < valeurSeuilNoir) || intersection){ //on a d�tect� une intersection
				intersection = false; //remet � 0 le bool�en de d�tection d'intersection
				LCD.drawString("intersection � 2 branches", 1, 3);
				Integer ValeurCapteur = valeurSeuilNoir;
				
				if(ProchaineRoute.equals("r")){
					
					//pendant 1.2 sec (ou : jusqu'� avoir franchi une ligne � gauche), on ignore le capteur gauche
					LCD.drawString("va � droite", 1, 4);
					long start = System.currentTimeMillis();
					long end = System.currentTimeMillis();
					
					ValeurCapteur = lightG.getNormalizedLightValue();
					
					while(end - start < 1200 /*ou : ValeurCapteur <= valeurSeuilNoir*/){
						ValeurCapteur = lightG.getNormalizedLightValue();
						
						LGauche = valeurSeuilNoir; //ignore le capteur gauche
						EcartARetenir = PID1.getEcartALaValeurNoire(LDroite, valeurSeuilNoir);
						valPID = PID1.valeurPID();
						PID1.setEcartPrecedent(EcartARetenir);
						VitesseGauche = (int) (valPID*k + VitesseMoteurDroit);
						motorD.setSpeed(VitesseMoteurDroit);
						motorG.setSpeed(VitesseGauche);
						LDroite = lightD.getNormalizedLightValue();	
						
						end = System.currentTimeMillis();
					}
					PID1 = new PID(); //on remet � 0 les valeurs
					LCD.clear();
						
				}
				else if(ProchaineRoute.equals("l")){
					
					//pendant 1.2 sec (ou : jusqu'� avoir franchi une ligne � droite), on ignore le capteur droit
					LCD.drawString("va � gauche", 1, 4);
					long start = System.currentTimeMillis();
					long end = System.currentTimeMillis();
					
					ValeurCapteur = lightD.getNormalizedLightValue();
					
					while(end - start < 1200 /*ou : ValeurCapteur <= valeurSeuilNoir*/){
						ValeurCapteur = lightD.getNormalizedLightValue();
						
						LDroite = valeurSeuilNoir; //ignore le capteur droit
						EcartARetenir = PID2.getEcartALaValeurNoire(LGauche, valeurSeuilNoir);
						valPID = PID2.valeurPID();
						PID2.setEcartPrecedent(EcartARetenir);
						VitesseDroite = (int) (valPID*k + VitesseMoteurGauche);
						motorD.setSpeed(VitesseDroite);
						motorG.setSpeed(VitesseMoteurGauche);
						LGauche = lightG.getNormalizedLightValue();
						
						end = System.currentTimeMillis();
					}
					PID2 = new PID(); //on remet � 0 les valeurs
					LCD.clear();
					
				}
				
			}

		}
	}
	
}
