package mains;

import lejos.nxt.Motor;
import lejos.nxt.NXTRegulatedMotor;
import lejos.nxt.SensorPort;
import lejos.nxt.UltrasonicSensor;
import lejos.util.Delay;
import tools.ChangeSquare;
import lejos.nxt.Button;
import lejos.nxt.LCD;
import lejos.nxt.LightSensor;

public class MainSquares {
	public static void main(String[] args) {
		
		NXTRegulatedMotor motorG = Motor.C;
		NXTRegulatedMotor motorD = Motor.B;
		//ChangeSquare pilote = new ChangeSquare(55.5, 162.5, motorG, motorD); //pour robot � 2 capteurs US (Hydra)
		ChangeSquare pilote = new ChangeSquare(55.5, 100, motorG, motorD);
		
		
		// Valeur seuil pour diff�rencier le noir et le blanc
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
		
		motorG.setSpeed(100);
		motorD.setSpeed(100);
		Motor.B.setAcceleration(2000);
		Motor.C.setAcceleration(2000);
		
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
		
		pilote.recalage();
		pilote.avanceUneCase();
		pilote.rotate(90); //vers la gauche
		pilote.recalage();
		pilote.avanceUneCase();
		pilote.rotate(-90);
		pilote.recalage();
		pilote.avanceUneCase();
		pilote.recalage();
		pilote.avanceUneCase();
		pilote.recalage();
		pilote.avanceUneCase();
		pilote.rotate(-90);
		pilote.recalage();
		pilote.avanceUneCase();
		pilote.rotate(-90);
		pilote.recalage();
		pilote.avanceUneCase();
		pilote.recalage();
		pilote.avanceUneCase();
		pilote.rotate(90);
		pilote.recalage();
		pilote.avanceUneCase();
		pilote.rotate(-90);
		pilote.recalage();
		pilote.avanceUneCase();
		pilote.rotate(180);
		pilote.recalage();
		pilote.avanceUneCase();
		pilote.recalage();
		pilote.avanceUneCase();
		pilote.recalage();
		pilote.avanceUneCase();
		LCD.clear();
		}
		/*UltrasonicSensor ultrasonic = new UltrasonicSensor(SensorPort.S1);
		Integer distance = ultrasonic.getDistance();
		LCD.drawString(distance.toString(), 1, 1);
		Button.waitForAnyPress();*/
	}

}
