package tools;

import lejos.nxt.Button;
import lejos.nxt.LCD;
import lejos.nxt.LightSensor;
import lejos.nxt.Motor;
import lejos.nxt.NXTRegulatedMotor;
import lejos.nxt.SensorPort;
import lejos.nxt.UltrasonicSensor;
import lejos.robotics.RegulatedMotor;
import lejos.robotics.navigation.DifferentialPilot;
import lejos.util.Delay;

public class ChangeSquare extends DifferentialPilot {

	public static NXTRegulatedMotor motorG = Motor.C;
	public static NXTRegulatedMotor motorD = Motor.B;
	public static NXTRegulatedMotor tete = Motor.A;

	public static double wheelDiameter = 55.5;
	public static double trackWidth = 100;

	public static Double dist = (double) 0;
	public static String c�t� = "";
			
	static DifferentialPilot pilote = new DifferentialPilot(wheelDiameter, trackWidth, motorG, motorD);

	public ChangeSquare(final double wheelDiameter, final double trackWidth, final NXTRegulatedMotor leftMotor,
			final NXTRegulatedMotor rightMotor) {
		super(wheelDiameter, trackWidth, leftMotor, rightMotor);
		ChangeSquare.wheelDiameter = wheelDiameter;
		ChangeSquare.trackWidth = trackWidth;
		ChangeSquare.motorG = leftMotor;
		ChangeSquare.motorD = rightMotor;
		ChangeSquare.pilote = new DifferentialPilot(wheelDiameter, trackWidth, motorG, motorD);
	}

	public void goFrontSquare() {
		ChangeSquare pilote = new ChangeSquare(55.5, 100, motorG, motorD);
		motorG.setSpeed(420);
		motorD.setSpeed(420);
		//Motor.B.setAcceleration(1500);
		//Motor.C.setAcceleration(1500);
		// pilote.travel(400);
		pilote.avanceUneCase();
		pilote.recalage();
	}
	
	public void goFrontSquareBeforeTurn() {
		ChangeSquare pilote = new ChangeSquare(55.5, 100, motorG, motorD);
		motorG.setSpeed(420);
		motorD.setSpeed(420);
		//Motor.B.setAcceleration(1500);
		//Motor.C.setAcceleration(1500);
		// pilote.travel(400);
		pilote.avanceUneCase();
	}

	public void goBackSquare() {
		ChangeSquare pilote = new ChangeSquare(55.5, 100, motorG, motorD);
		motorG.setSpeed(200);
		motorD.setSpeed(200);
		Motor.B.setAcceleration(1000);
		Motor.C.setAcceleration(1000);
		// pilote.travel(-400);
		pilote.rotate(180);
		Motor.B.setAcceleration(1600);
		Motor.C.setAcceleration(1600);
		pilote.avanceUneCase();
		pilote.recalage();
	}
	
	public void goBackSquareBeforeTurn() {
		ChangeSquare pilote = new ChangeSquare(55.5, 100, motorG, motorD);
		motorG.setSpeed(200);
		motorD.setSpeed(200);
		Motor.B.setAcceleration(1000);
		Motor.C.setAcceleration(1000);
		// pilote.travel(-400);
		pilote.rotate(180);
		Motor.B.setAcceleration(1600);
		Motor.C.setAcceleration(1600);
		pilote.avanceUneCase();
	}

	public void goLeftSquare() {
		ChangeSquare pilote = new ChangeSquare(55.5, 100, motorG, motorD);
		motorG.setSpeed(200);
		motorD.setSpeed(200);
		Motor.B.setAcceleration(1000);
		Motor.C.setAcceleration(1000);
		pilote.rotate(90);
		Motor.B.setAcceleration(1600);
		Motor.C.setAcceleration(1600);
		pilote.avanceUneCase();
		pilote.recalage();
		// pilote.travel(400);
	}
	public void goLeftSquareBeforeTurn() {
		ChangeSquare pilote = new ChangeSquare(55.5, 100, motorG, motorD);
		motorG.setSpeed(200);
		motorD.setSpeed(200);
		Motor.B.setAcceleration(1000);
		Motor.C.setAcceleration(1000);
		pilote.rotate(90);
		Motor.B.setAcceleration(1600);
		Motor.C.setAcceleration(1600);
		pilote.avanceUneCase();
		// pilote.travel(400);
	}

	public void goRightSquare() {
		ChangeSquare pilote = new ChangeSquare(55.5, 100, motorG, motorD);
		motorG.setSpeed(200);
		motorD.setSpeed(200);
		Motor.B.setAcceleration(1000);
		Motor.C.setAcceleration(1000);
		pilote.rotate(-90);
		Motor.B.setAcceleration(1600);
		Motor.C.setAcceleration(1600);
		pilote.avanceUneCase();
		pilote.recalage();
		// pilote.travel(400);
	}
	public void goRightSquareBeforeTurn() {
		ChangeSquare pilote = new ChangeSquare(55.5, 100, motorG, motorD);
		motorG.setSpeed(200);
		motorD.setSpeed(200);
		Motor.B.setAcceleration(1000);
		Motor.C.setAcceleration(1000);
		pilote.rotate(-90);
		Motor.B.setAcceleration(1600);
		Motor.C.setAcceleration(1600);
		pilote.avanceUneCase();
		// pilote.travel(400);
	}

	public void parcours(String[] parcours) {
		ChangeSquare pilote = new ChangeSquare(55.5, 100, motorG, motorD);
		String x = "";
		for (int i=0 ; i<parcours.length ; i++) {
			x = parcours[i];
			if (x.equals("r") && !parcours[i+1].equals("r") && !parcours[i+1].equals("l")) {// pour aller � droite
				pilote.goRightSquare();
			}
			if (x.equals("l") && !parcours[i+1].equals("r") && !parcours[i+1].equals("l")) {// pour aller � gauche
				pilote.goLeftSquare();
			}
			if (x.equals("r") && (parcours[i+1].equals("r") || parcours[i+1].equals("l"))) {// pour � droite avant de tourner
				pilote.goRightSquareBeforeTurn();
			}
			if (x.equals("l") && (parcours[i+1].equals("r") || parcours[i+1].equals("l"))) {// pour aller � gauche avant de tourner
				pilote.goLeftSquareBeforeTurn();
			}
			if (x.equals("f") && !parcours[i+1].equals("r") && !parcours[i+1].equals("l")) {// pour aller tout droit
				pilote.goFrontSquare();
			}
			if (x.equals("b") && !parcours[i+1].equals("r") && !parcours[i+1].equals("l")) {// pour faire demi-tour
				pilote.goBackSquare();
			}
			if (x.equals("f") && (parcours[i+1].equals("r") || parcours[i+1].equals("l"))) {// pour aller tout droit avant de tourner
				pilote.goFrontSquareBeforeTurn();
			}
			if (x.equals("b") && (parcours[i+1].equals("r") || parcours[i+1].equals("l"))) {// pour faire demi-tour avant de tourner
				pilote.goBackSquareBeforeTurn();
			}
		}
	}

	private UltrasonicSensor ultrasonic = new UltrasonicSensor(SensorPort.S1);

	public void recalage() {
		// cette fonction doit �tre appel�e quand la case o� on veut aller est
		// celle en face du robot
		// si ce n'est pas le cas il faut faire une rotation

		motorG.setSpeed(420);
		motorD.setSpeed(420);
		//Motor.B.setAcceleration(1500);
		//Motor.C.setAcceleration(1500);
		
		Double distanceDeRecalage = (double) 60;
		Double a = (double) 0;
		Double b = (double) 0;
		Double angle = (double) 0;
		Double parcours = (double) 0;
		Integer signe = 1;

		if (dist < 40) {

			if (c�t�.contentEquals("g")) {
				if (dist >= 20) {
					b = dist * 10;
					LCD.drawString(b.toString(), 1, 4);
					a = 400 - b;
					LCD.drawString(a.toString(), 1, 5);
					signe = +1;
				}
				if (dist < 20) {
					a = dist * 10;
					b = 400 - a;
					LCD.drawString(b.toString(), 1, 4);
					LCD.drawString(a.toString(), 1, 5);
					signe = -1;
				}
				if (a <= 200) {
					angle = signe * Math.atan((b - 200) / distanceDeRecalage) * 180 / Math.PI;
					LCD.drawString(angle.toString(), 1, 2);

					motorG.setSpeed(420);
					motorD.setSpeed(420);
					//Motor.B.setAcceleration(1500);
					//Motor.C.setAcceleration(1500);

					pilote.rotate(angle);
					parcours = Math.sqrt(distanceDeRecalage * distanceDeRecalage + (b - 200) * (b - 200));
					LCD.drawString(parcours.toString(), 1, 3);
					pilote.travel(parcours);
					pilote.rotate((-1) * angle); // pour se remettre � peu pr�s droit											
					
				}
			}
			//Delay.msDelay(3000);
		} 
		
		if(c�t�.contentEquals("d") && dist < 40){
			
			if (dist >= 20) {
				b = dist * 10;
				LCD.drawString(b.toString(), 1, 4);
				a = 400 - b;
				LCD.drawString(a.toString(), 1, 5);
				signe = -1;
			}
			if (dist < 20) {
				a = dist * 10;
				b = 400 - a;
				LCD.drawString(b.toString(), 1, 4);
				LCD.drawString(a.toString(), 1, 5);
				signe = +1;
			}
			if (a <= 200) {
				angle = signe * Math.atan((b - 200) / distanceDeRecalage) * 180 / Math.PI;
				LCD.drawString(angle.toString(), 1, 2);

				motorG.setSpeed(420);
				motorD.setSpeed(420);
				//Motor.B.setAcceleration(700);
				//Motor.C.setAcceleration(700);

				pilote.rotate(angle);
				parcours = Math.sqrt(distanceDeRecalage * distanceDeRecalage + (b - 200) * (b - 200));
				LCD.drawString(parcours.toString(), 1, 3);
				pilote.travel(parcours);
				pilote.rotate((-1) * angle); // pour se remettre � peu pr�s droit
			}
		}
		
		motorG.setSpeed(420);
		motorD.setSpeed(420);
		//Motor.B.setAcceleration(1500);
		//Motor.C.setAcceleration(1500);
		
		// Button.waitForAnyPress();
		LCD.clear();
		// apr�s cette fonction de recalage, on arrive un peu avant ligne
		// blanche,
		// il faut alors se remettre droit gr�ce � celle-ci
		// -> en fait il suffit d'appeler la fonction avanceUneCase()

		/*
		 * Fonction : tourner la t�te � gauche (tete.rotateTo(-180)), si
		 * dist>... tourner la t�te � droite (tete.rotateTo(180) si dist>... ne
		 * rien faire
		 * 
		 * (b = dist si dist >20 -> donc a = (40-b)) (a = dist sinon -> donc
		 * b=(40-a))
		 * 
		 * si dist < ... d'un des 2 c�t�s, angle = arctan((b-20)/40)
		 * ->rotate(angle) hyp = sqrt(40� + (b-20)�) ->travel(hyp)
		 */

	}

	// Valeur seuil pour diff�rencier le noir et le blanc
	private static final int valeurSeuilGauche = 487;
	private static final int valeurSeuilDroit = 487;

	// Capteurs lumineux
	private LightSensor lightG = new LightSensor(SensorPort.S4);
	private LightSensor lightD = new LightSensor(SensorPort.S3);

	public void avanceUneCase() {
		motorG.setSpeed(420);
		motorD.setSpeed(420);
		//Motor.B.setAcceleration(2000);
		//Motor.C.setAcceleration(2000);
		NXTRegulatedMotor second = null;
		NXTRegulatedMotor prems = null;
		long start = 0;
		long stop = 0;
		int t�ta = 0;
		int angularSpeed = 0;
		pilote.forward();
		while (lightG.getNormalizedLightValue() < valeurSeuilGauche
				&& lightD.getNormalizedLightValue() < valeurSeuilDroit)
			;
		start = System.currentTimeMillis();
		if (lightG.getNormalizedLightValue() > valeurSeuilGauche) {
			while (lightD.getNormalizedLightValue() < valeurSeuilDroit)
				;
			stop = System.currentTimeMillis();
			prems = motorG;
			second = motorD;
		} else {
			while (lightG.getNormalizedLightValue() < valeurSeuilGauche)
				;
			stop = System.currentTimeMillis();
			prems = motorD;
			second = motorG;
		}
		long delai = stop - start;
		//pilote.stop();
		if (delai > /* 15 */ 30) {
			// second.rotate((int) (delai/2.2));
			t�ta = (int) (Math.atan(0.2 * delai * 0.001 / (trackWidth*0.001))* 180 / Math.PI); // en degr�s
			int sp = motorD.getSpeed();
			angularSpeed = (int) ((2 * Math.PI * 0.5 * wheelDiameter * (sp /360) / (2 * Math.PI * trackWidth)) * 360); // en degr�s
			prems.setSpeed(0);
			//prems.stop();
			//second.rotate(t�ta);
			Delay.msDelay(t�ta*1000/angularSpeed);
		}
		// Delay.msDelay(1000);
		motorG.setSpeed(420);
		motorD.setSpeed(420);
		//Motor.B.setAcceleration(1500);
		//Motor.C.setAcceleration(1500);
		
		pilote.travel(230, true);
		
		this.tete.rotateTo(-90); // regarde � gauche
		c�t� = "g";
		dist = (double) this.ultrasonic.getDistance(); // attention c'est en cm !							
		LCD.drawString(dist.toString(), 1, 1);
		if (dist == 255 || dist >= 40) {
			this.tete.rotateTo(90);// regarde � droite	
			c�t� = "d";
			dist = (double) this.ultrasonic.getDistance();
			LCD.drawString(dist.toString(), 1, 2);}
		this.tete.rotateTo(0);
		//Delay.msDelay(3000);
	}

}
