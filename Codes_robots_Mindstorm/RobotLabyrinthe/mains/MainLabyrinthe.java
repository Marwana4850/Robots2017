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
		 * Eléments moteurs du robot
		 *
		 */
			
		//attention, ne pas oublier de changer les ports des ultrasons et capteurs lumineux dans la classe ChangeSquare
			
		NXTRegulatedMotor motorG = Motor.C;
		NXTRegulatedMotor motorD = Motor.B;
		Double wheelDiameter = 55.5; //diamètre des roues
		Double trackWidth = (double) 100; //écart entre les roues 
										  //(attention, à régler assez empiriquement jusqu'à avoir des rotations précises du robot)
		
		ChangeSquare pilote = new ChangeSquare(wheelDiameter, trackWidth, motorG, motorD); 
		
		
		
		/*
		 * Parcours case par case dans le labyrinthe
		 * 
		 */
		
		// "f"="devant", "b"="demi-tour", "l"="à gauche", "r"= "à droite"
		
		String[] parcours = {"f","l","l","r","f","r","b","r","r","b","l","f","f","l","r","r","b"};
		//String[] parcours = {"f","l","r","f","f","r"};
		pilote.parcours(parcours);	
				
		}
	}	
}
