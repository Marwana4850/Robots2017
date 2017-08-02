package mains;

import lejos.nxt.Motor;
import lejos.nxt.NXTRegulatedMotor;
import lejos.robotics.navigation.DifferentialPilot;
import lejos.nxt.Button;

public class MainParcours {
	public static void main(String[] args) {
		
		Button.waitForAnyPress();
		NXTRegulatedMotor motorG = Motor.C;
		NXTRegulatedMotor motorD = Motor.B;
	
		DifferentialPilot pilote = new DifferentialPilot(55.5, 162.5, motorG, motorD);
		motorG.setSpeed(600);
		motorD.setSpeed(600);
		Motor.B.setAcceleration(2000);
		Motor.C.setAcceleration(2000);
		
		
		while(true){
			Button.waitForAnyPress();
			//pilote.arc(100, 180);
			pilote.arc(200, 120);
		}
		
	}
}
