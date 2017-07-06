package mains;

import lejos.nxt.Motor;
import lejos.nxt.NXTRegulatedMotor;
import tools.ChangeSquare;
import lejos.nxt.Button;

public class MainSquares {
	public static void main(String[] args) {
		
		NXTRegulatedMotor motorG = Motor.C;
		NXTRegulatedMotor motorD = Motor.B;
		ChangeSquare pilote = new ChangeSquare(55.5, 162.5, motorG, motorD);
		
		Button.waitForAnyPress();
		/*pilote.goFrontSquare();
		pilote.goBackSquare();
		pilote.goLeftSquare();
		pilote.goBackSquare();
		pilote.rotate(90);
		pilote.goRightSquare();
		pilote.goBackSquare();
		pilote.rotate(-90);*/
		
		pilote.recalage();
	}

}
