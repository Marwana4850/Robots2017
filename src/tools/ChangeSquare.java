package tools;

import lejos.nxt.Motor;
import lejos.nxt.NXTRegulatedMotor;
import lejos.robotics.RegulatedMotor;
import lejos.robotics.navigation.DifferentialPilot;


public class ChangeSquare extends DifferentialPilot {
	
	static NXTRegulatedMotor motorG = Motor.C;
	static NXTRegulatedMotor motorD = Motor.B;

	public ChangeSquare(final double wheelDiameter, final double trackWidth, final RegulatedMotor leftMotor, final RegulatedMotor rightMotor) {		
		super(wheelDiameter, trackWidth, leftMotor, rightMotor);	
		final DifferentialPilot pilote = new DifferentialPilot(wheelDiameter, trackWidth, motorG, motorD);
	}
	
	public static void goFrontSquare(){
		pilote.travel(100);
	}
	public static void goBackSquare(){
		pilote.travel(-100);
	}
	public static void goLeftSquare(){
		pilote.rotate(-90);
		pilote.travel(100);
	}
	public static void goRightSquare(){
		pilote.rotate(90);
		pilote.travel(100);
	}
}
