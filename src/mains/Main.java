package mains;

import lejos.nxt.Motor;
import lejos.nxt.NXTRegulatedMotor;
import lejos.robotics.navigation.DifferentialPilot;
import lejos.util.Delay;
import lejos.nxt.Button;
import tools.ChangeSquare;

public class Main {
	public static void main(String[] args) {
		
		
		
		Button.waitForAnyPress();
		NXTRegulatedMotor motorG = Motor.C;
		NXTRegulatedMotor motorD = Motor.B;
	
		DifferentialPilot pilote = new DifferentialPilot(55.5, 162.5, motorG, motorD);
		
		
		motorG.setSpeed(600);
		motorD.setSpeed(600);
		Motor.B.setAcceleration(2000);
		Motor.C.setAcceleration(2000);

		for(int i=1;i<4*11-1;i++){
		
			//pilote.travel(200);
			
			//pilote.rotate(90);
			
			pilote.forward();
			Delay.msDelay(200);
			pilote.stop();
			
			/*Motor.B.setSpeed(600);
			Motor.C.setSpeed(600);
			Motor.C.rotate((int)(2*Math.PI*163*90/360*360/(27.75*2*Math.PI)), true);
			Motor.B.rotate(-(int)(2*Math.PI*163*90/360*360/(27.75*2*Math.PI)), true);
			Motor.B.setSpeed(0);
			Motor.C.setSpeed(0);
			Delay.msDelay(100);*/
			Button.waitForAnyPress();
			}
		/*while(true){
		pilote.travel(1000);
		pilote.rotate(90);
		pilote.travel(1000);
		pilote.rotate(90);
		pilote.travel(1000);
		pilote.rotate(90);
		pilote.travel(1000);
		pilote.rotate(90);
		Button.waitForAnyPress();
		}*/
	}
}
