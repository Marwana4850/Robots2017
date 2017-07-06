package mains;

import lejos.nxt.Motor;
import lejos.nxt.NXTRegulatedMotor;
import lejos.nxt.SensorPort;
import lejos.nxt.UltrasonicSensor;
import tools.ChangeSquare;
import lejos.nxt.Button;
import lejos.nxt.LCD;

public class MainSquares {
	public static void main(String[] args) {
		
		NXTRegulatedMotor motorG = Motor.C;
		NXTRegulatedMotor motorD = Motor.B;
		ChangeSquare pilote = new ChangeSquare(55.5, 162.5, motorG, motorD);
		
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
		
		motorG.setSpeed(500);
		motorD.setSpeed(500);
		Motor.B.setAcceleration(1500);
		Motor.C.setAcceleration(1500);
		
		pilote.recalage();
		//pilote.avanceUneCase();
		}
		/*UltrasonicSensor ultrasonic = new UltrasonicSensor(SensorPort.S1);
		Integer distance = ultrasonic.getDistance();
		LCD.drawString(distance.toString(), 1, 1);
		Button.waitForAnyPress();*/
	}

}
