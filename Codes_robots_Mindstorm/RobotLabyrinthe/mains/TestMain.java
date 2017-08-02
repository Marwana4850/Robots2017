package mains;

import lejos.nxt.Motor;

import lejos.nxt.Button;
import lejos.nxt.LCD;
import java.lang.Integer;
import lejos.util.Delay;

public class TestMain {
	public static void main(String[] args) {
		//SmoothMove move1 = new SmoothMove();
		//move1.smoothForward(100, 100, 1000);

		while(true){
		LCD.drawString("Program 1", 0, 0);
		Button.waitForAnyPress();
		LCD.clear();
		
		LCD.drawString("FORWARD",0,0);
		
		Integer before = (int)System.currentTimeMillis();
		Motor.B.setSpeed(400);
		//Motor.B.forward();
		Motor.B.rotate(180, true);
		Integer start = (int)System.currentTimeMillis();
		//Delay.msDelay(300);
		Integer before2 = (int)System.currentTimeMillis();
		Motor.C.setSpeed(400);
		//Motor.C.forward();
		Motor.C.rotate(180, true);
		Integer start2 = (int)System.currentTimeMillis();

		//Motor.B.setAcceleration(3000);
		//Motor.C.setAcceleration(3000);
		
		//Button.waitForAnyPress();
		Delay.msDelay(1000);
		
		Integer after = (int)System.currentTimeMillis();
		//Motor.B.stop();
		Motor.B.setSpeed(0);
		Integer end = (int)System.currentTimeMillis();
		Integer after2 = (int)System.currentTimeMillis();
		//Motor.C.stop();
		Motor.C.setSpeed(0);
		Integer end2 = (int)System.currentTimeMillis();
		
		LCD.drawString(start.toString(), 0, 1);
		LCD.drawString(start2.toString(), 1, 2);
		Integer delta = start2 - start;
		LCD.drawString(delta.toString(), 2, 3);
		Integer delta2 = before2 - before;
		LCD.drawString(before.toString(), 0, 5);
		LCD.drawString(before2.toString(), 1, 6);
		LCD.drawString(delta2.toString(), 2, 7);
		
		Button.waitForAnyPress();
		
		LCD.drawString(after.toString(), 0, 1);
		LCD.drawString(end.toString(), 1, 2);
		Integer delta3 = end - after;
		LCD.drawString(delta3.toString(), 2, 3);
		Integer delta4 = end2 - after2;
		LCD.drawString(after2.toString(), 0, 5);
		LCD.drawString(end2.toString(), 1, 6);
		LCD.drawString(delta4.toString(), 2, 7);
		
		Button.waitForAnyPress();
		
		
		}
	}
}
