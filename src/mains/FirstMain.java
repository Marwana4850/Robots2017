package mains;

import lejos.nxt.Motor;

import lejos.nxt.NXTRegulatedMotor;
import lejos.nxt.Button;
import lejos.nxt.LCD;
import lejos.nxt.Motor;
import java.lang.Integer;
import lejos.nxt.comm.RConsole;


public class FirstMain {
	public static void main(String[] args) {
		
		LCD.drawString("Program 1", 0, 0);
		Button.waitForAnyPress();
		LCD.clear();
		
		LCD.drawString("FORWARD",0,0);
		Motor.B.forward();
		Motor.C.forward();
		
		//System.out.println("hey");
		Integer speed_b = Motor.B.getSpeed();
		speed_b.toString();
		
		Integer acc_b = Motor.B.getAcceleration();
		acc_b.toString();
		int speed_c = Motor.C.getSpeed();
		int acc_c = Motor.C.getAcceleration();
		
		//Button.waitForAnyPress();
		//LCD.drawString("BACKWARD",0,0);
		//Motor.B.backward();
		//Motor.C.backward();
		//Button.waitForAnyPress();
		LCD.drawString("speed_b: " + speed_b, 1, 1);
		Button.waitForAnyPress();
		
		Integer angle = Motor.A.getLimitAngle();
		angle.toString();
		LCD.drawString("angle: " + angle, 2, 2);
		LCD.drawString("acc_b: " + acc_b, 2, 2);
		//RConsole.openAny(100000);
		//RConsole.println("coucou");
		//System.out.println("angle" + angle);
		Motor.B.stop();
		Motor.C.stop();
		Button.waitForAnyPress();
		
		
		
	}

}
