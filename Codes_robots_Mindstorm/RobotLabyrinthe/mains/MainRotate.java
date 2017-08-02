package mains;
import lejos.nxt.Motor;

import lejos.nxt.Button;

public class MainRotate {
	public static void main(String[] args) {
		Motor.B.setSpeed(600);
		Motor.C.setSpeed(600);
		Motor.B.setAcceleration(1000);
		Motor.C.setAcceleration(1000);
		
		while(true){
		Button.waitForAnyPress();
		Motor.C.rotate((int)(2*Math.PI*162.5*90/360*360/(27.75*2*Math.PI)), true);
		Motor.B.rotate(-(int)(2*Math.PI*162.5*90/360*360/(27.75*2*Math.PI)), true);
		}
		
	}

}
