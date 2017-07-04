package tools;

import lejos.nxt.Motor;
import lejos.nxt.Button;
import lejos.nxt.LCD;

public class SmoothMove {
	
	public static void smoothForward(int acc_max, int dec_max, int temps){
		Integer acc_b = 10;
		Integer acc_c = 10;
		int previousAcc_b = 0;
		int previousAcc_c = 0;
		
		Integer speed_b = 10;
		Integer speed_c = 10;
	    int previousSpeed_b = 0;
		int previousSpeed_c = 0;
		
		LCD.drawString("Program 2", 0, 0);
		Button.waitForAnyPress();
		
		int start = (int)System.currentTimeMillis();
		int time = (int)System.currentTimeMillis();
		
		Motor.B.forward();
		Motor.C.forward();
		
		while((time - start) < temps){
			if(((int)System.currentTimeMillis() - time) > 200){
				time = (int)System.currentTimeMillis();
				previousAcc_b = acc_b;
				previousAcc_c = acc_c;
				acc_b = Motor.B.getAcceleration();
				acc_c = Motor.C.getAcceleration();
				
				LCD.drawString(acc_b.toString(), 1, 1);
				LCD.drawString(acc_c.toString(), 2, 2);
				LCD.drawString(speed_b.toString(), 1, 3);
				LCD.drawString(speed_c.toString(), 2, 4);
			}
			if(acc_b > acc_max){
				Motor.B.setAcceleration(previousAcc_b);
				Motor.C.setAcceleration(previousAcc_b);
		}
		}
		
		Motor.B.stop();
		Motor.C.stop();
		
	}

}
