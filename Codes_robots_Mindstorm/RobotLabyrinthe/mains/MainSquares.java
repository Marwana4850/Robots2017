package mains;

import lejos.nxt.Motor;
import lejos.nxt.NXTRegulatedMotor;
import lejos.nxt.SensorPort;
import tools.ChangeSquare;

import lejos.nxt.Button;
import lejos.nxt.LCD;
import lejos.nxt.LightSensor;
import lejos.nxt.UltrasonicSensor;
import lejos.util.Delay;

@SuppressWarnings("unused")
public class MainSquares {

	public static void main(String[] args) {
		
		NXTRegulatedMotor motorG = Motor.C;
		NXTRegulatedMotor motorD = Motor.B;
		//ChangeSquare pilote = new ChangeSquare(55.5, 162.5, motorG, motorD); //pour robot à 2 capteurs US (Hydra)
		ChangeSquare pilote = new ChangeSquare(55.5, 100, motorG, motorD);
		
		
		// Valeur seuil pour différencier le noir et le blanc
			final int valeurSeuilGauche = 487;
			final int valeurSeuilDroit = 487;
		
		// Capteurs lumineux
			LightSensor lightG = new LightSensor(SensorPort.S4);
			LightSensor lightD = new LightSensor(SensorPort.S3);
		
		while(true){
		Button.waitForAnyPress();
		/*pilote.goFrontSquare();
		pilote.goBackSquare();
		pilote.goLeftSquare();
		pilote.goBackSquare();
		pilote.rotate(90);
		pilote.goRightSquare();
		pilote.goBackSquare();
		pilote.rotate(-90);*/
		
		motorG.setSpeed(420);
		motorD.setSpeed(420);
		Motor.B.setAcceleration(1500);
		Motor.C.setAcceleration(1500);
		
		/*pilote.travel(200);
		LCD.drawString("RECALAGE", 3 , 6);
		Delay.msDelay(1000);
		pilote.recalage();
		LCD.drawString("avance", 3 , 6);
		Delay.msDelay(1000);
		pilote.avanceUneCase();
		pilote.rotate(90);
		LCD.drawString("RECALAGE", 3 , 6);
		Delay.msDelay(1000);
		pilote.recalage();
		LCD.drawString("avance", 3 , 6);
		Delay.msDelay(1000);
		pilote.avanceUneCase();
		pilote.rotate(-90);
		LCD.drawString("RECALAGE", 3 , 6);
		Delay.msDelay(1000);
		pilote.recalage();
		LCD.drawString("avance", 3 , 6);
		pilote.avanceUneCase();
		Delay.msDelay(1000);*/
		
		//String[] parcours = {"f","l","r","f","f","r"};
		String[] parcours = {"f","l","l","r","f","r","b","r","r","b","l","f","f","l","r","r","b"};
		pilote.parcours(parcours);	
		
		LCD.clear();
		}
		/*UltrasonicSensor ultrasonic = new UltrasonicSensor(SensorPort.S1);
		Integer distance = ultrasonic.getDistance();
		LCD.drawString(distance.toString(), 1, 1);
		Button.waitForAnyPress();*/
	}

}
