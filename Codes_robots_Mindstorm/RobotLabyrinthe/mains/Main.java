package mains;

import lejos.nxt.Motor;
import lejos.nxt.NXTRegulatedMotor;
import lejos.nxt.SensorPort;
import lejos.util.Delay;
import tools.ChangeSquare;
import lejos.nxt.Button;
import lejos.nxt.LightSensor;
import lejos.nxt.LCD;


@SuppressWarnings("unused")
public class Main {

	public static void main(String[] args) {
		
		
		
		Button.waitForAnyPress();
		NXTRegulatedMotor motorG = Motor.C;
		NXTRegulatedMotor motorD = Motor.B;
	
		ChangeSquare pilote = new ChangeSquare(55.5, 100, motorG, motorD);
		
		// Capteurs lumineux
				LightSensor lightG = new LightSensor(SensorPort.S4);
				LightSensor lightD = new LightSensor(SensorPort.S3);
		
		motorG.setSpeed(400);
		motorD.setSpeed(400);
		Motor.B.setAcceleration(1500);
		Motor.C.setAcceleration(1500);

		//for(int i=1;i<4*11-1;i++){
		while(true){
			//pilote.travel(200);
			
			//pilote.rotate(90);
			
			//pilote.forward();
			//Integer a = lightG.getNormalizedLightValue();
			//Integer b = lightD.getNormalizedLightValue();
			//pilote.avanceUneCase();
			//LCD.drawString(a.toString(), 1, 1);
			//LCD.drawString(a.toString(), 1, 2);
			//Delay.msDelay(1000);
			//LCD.clear();
			//pilote.stop();
			pilote.recalage();
			
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
