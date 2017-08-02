package mains;

import java.io.*;
import lejos.nxt.LCD;
import lejos.nxt.comm.BTConnection;
import lejos.nxt.comm.Bluetooth;

public class Receiver {
	public static void main(String[] args) throws Exception
	{
		LCD.clear();
		LCD.drawString("Receiver wait...", 0, 0);
		LCD.refresh();

		try
		{
			BTConnection connection = Bluetooth.waitForConnection();
			if (connection == null)
				throw new IOException("Connect fail");

			LCD.drawString("Connected.", 1, 0);
			DataInputStream input = connection.openDataInputStream();
			DataOutputStream output = connection.openDataOutputStream();

			int answer1 = input.readInt();
			LCD.drawString("1st = " + answer1, 2, 0);
			int answer2 = input.readInt();
			LCD.drawString("2nd = " + answer2, 3, 0);

			output.writeInt(0);
			output.flush();
			LCD.drawString("Sent data", 4, 0);
						
			input.close();
			output.close();
			connection.close();
			LCD.drawString("Bye ...", 5, 0);
		}
		catch(Exception ioe)
		{
			LCD.clear();
			LCD.drawString("ERROR", 0, 0);
			LCD.drawString(ioe.getMessage(), 2, 0);
			LCD.refresh();
		}

		Thread.sleep(4000);
	}


}
