package tools;

import lejos.nxt.Motor;
import lejos.nxt.NXTRegulatedMotor;
import lejos.robotics.RegulatedMotor;
import lejos.robotics.navigation.DifferentialPilot;


public class ChangeSquare extends DifferentialPilot {
	
	public static NXTRegulatedMotor motorG = Motor.C;
	public static NXTRegulatedMotor motorD = Motor.B;
	
	public static double wheelDiameter = 55.5;
	public static double trackWidth = 10;
	
	static DifferentialPilot pilote = new DifferentialPilot(wheelDiameter, trackWidth, motorG, motorD);
	
	public ChangeSquare(final double wheelDiameter, final double trackWidth, final NXTRegulatedMotor leftMotor, final NXTRegulatedMotor rightMotor) {		
		super(wheelDiameter, trackWidth, leftMotor, rightMotor);	
		ChangeSquare.wheelDiameter = wheelDiameter;
		ChangeSquare.trackWidth = trackWidth;
		ChangeSquare.motorG = leftMotor;
		ChangeSquare.motorD = rightMotor;
		ChangeSquare.pilote = new DifferentialPilot(wheelDiameter, trackWidth, motorG, motorD);
	}
	
	public void goFrontSquare(){
		pilote.travel(100);
	}
	public void goBackSquare(){
		pilote.travel(-100);
	}
	public void goLeftSquare(){
		pilote.rotate(-90);
		pilote.travel(100);
	}
	public void goRightSquare(){
		pilote.rotate(90);
		pilote.travel(100);
	}
}
