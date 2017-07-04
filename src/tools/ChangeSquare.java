package tools;

import lejos.nxt.Motor;
import lejos.nxt.NXTRegulatedMotor;
import lejos.robotics.RegulatedMotor;
import lejos.robotics.navigation.DifferentialPilot;


public class ChangeSquare extends DifferentialPilot {
	
	public static RegulatedMotor motorG;
	public static RegulatedMotor motorD;
	
	public static double wheelDiameter;
	public static double trackWidth;
	
	public ChangeSquare(final double wheelDiameter, final double trackWidth, final RegulatedMotor leftMotor, final RegulatedMotor rightMotor) {		
		super(wheelDiameter, trackWidth, leftMotor, rightMotor);	
		this.wheelDiameter = wheelDiameter;
		this.trackWidth = trackWidth;
		this.motorG = leftMotor;
		this.motorD = rightMotor;
	}
	
	static DifferentialPilot pilote = new DifferentialPilot(wheelDiameter, trackWidth, motorG, motorD);
	
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
