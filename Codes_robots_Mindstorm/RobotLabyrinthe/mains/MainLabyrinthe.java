package mains;

import lejos.nxt.Motor;
import lejos.nxt.NXTRegulatedMotor;
import tools.ChangeSquare;
import lejos.nxt.Button;
import lejos.nxt.LCD;


public class MainLabyrinthe {

	public static void main(String[] args) {
		
		while(true){
		
		Button.waitForAnyPress();	
		LCD.clear();
			
		
			
		/*
		 * El�ments moteurs du robot
		 *
		 */
			
		NXTRegulatedMotor motorG = Motor.C;
		NXTRegulatedMotor motorD = Motor.B;
		Double wheelDiameter = 55.5; //diam�tre des roues
		Double trackWidth = (double) 165; //�cart entre les roues 
										  //(attention, � r�gler assez empiriquement jusqu'� avoir des rotations pr�cises du robot)
		ChangeSquare pilote = new ChangeSquare(wheelDiameter, trackWidth, motorG, motorD); 
		//attention, ne pas oublier de changer les ports des ultrasons et capteurs lumineux dans la classe ChangeSquare
		
		
		
		/*
		 * Parcours case par case dans le labyrinthe
		 * 
		 */
		
		// "f"="devant", "b"="demi-tour", "l"="� gauche", "r"= "� droite" (par rapport � l'orientation du robot)
		String[] parcours = {"f","l","l","r","f","r","b","r","r","b","l","f","f","l","r","r","r"};
		//String[] parcours = {"f","l","r","f","f","r"};
		pilote.parcours(parcours);
				
		}
	}	
}
