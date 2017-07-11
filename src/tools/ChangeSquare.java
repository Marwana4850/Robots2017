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

	
	
	/*Eléments à changer en fonction de la construction mécanique du robot*/
	
	private UltrasonicSensor ultrasonic = new UltrasonicSensor(SensorPort.S1); //capteur Ultrason
	private LightSensor lightG = new LightSensor(SensorPort.S4); //capteur lumineux gauche
	private LightSensor lightD = new LightSensor(SensorPort.S3); //capteur lumineux droit
	public static NXTRegulatedMotor motorG = Motor.C; //moteur gauche
	public static NXTRegulatedMotor motorD = Motor.B; //moteur droit
	public static NXTRegulatedMotor tete = Motor.A; //moteur qui permet de tourner l'axe sur lequel se trouve le capteur ultrason
	public static double wheelDiameter = 55.5; //diamètre des roues
	public static double trackWidth = 100; //écart entre les roues (attention, à régler assez empiriquement
										   //jusqu'à avoir des rotations précises du robot)


		
	/*Variables utilisées dans plusieurs fonctions*/
	
	public static Double dist = (double) 0;
	public static Double distFace = (double) 0;
	public static String côté = "";
	static DifferentialPilot pilote = new DifferentialPilot(wheelDiameter, trackWidth, motorG, motorD);
	private static final int valeurSeuilGauche = 487; 	// Valeur seuil pour différencier le noir et le blanc
	private static final int valeurSeuilDroit = 487;


		
	/*Constructeur*/
	
	public ChangeSquare(final double wheelDiameter, final double trackWidth, final NXTRegulatedMotor leftMotor,
			final NXTRegulatedMotor rightMotor) {
		
		super(wheelDiameter, trackWidth, leftMotor, rightMotor);
		ChangeSquare.wheelDiameter = wheelDiameter;
		ChangeSquare.trackWidth = trackWidth;
		ChangeSquare.motorG = leftMotor;
		ChangeSquare.motorD = rightMotor;
		ChangeSquare.pilote = new DifferentialPilot(wheelDiameter, trackWidth, motorG, motorD);
	}

	
	
	/*Fonctions  simples de changement de case*/
	
	//Pour aller sur la case d'en face
	
	public void goFrontSquare() {
		
		ChangeSquare pilote = new ChangeSquare(55.5, 100, motorG, motorD);
		motorG.setSpeed(420);
		motorD.setSpeed(420);
		Motor.B.setAcceleration(1000);
		Motor.C.setAcceleration(1000);
		pilote.avanceUneCase();
		pilote.recalage();
	}
	
	//Pour aller sur la case d'en face alors qu'on va tourner après (on ne se recale donc pas)
	
	public void goFrontSquareBeforeTurn() {
		
		ChangeSquare pilote = new ChangeSquare(55.5, 100, motorG, motorD);
		motorG.setSpeed(420);
		motorD.setSpeed(420);
		Motor.B.setAcceleration(1000);
		Motor.C.setAcceleration(1000);
		pilote.avanceUneCase();
	}

	//Pour faire demi-tour 
	
	public void goBackSquare() {
		
		ChangeSquare pilote = new ChangeSquare(55.5, 100, motorG, motorD);
		motorG.setSpeed(200);
		motorD.setSpeed(200);
		Motor.B.setAcceleration(1000);
		Motor.C.setAcceleration(1000);
		pilote.rotate(180);
		motorG.setSpeed(420);
		motorD.setSpeed(420);
		Motor.B.setAcceleration(1000);
		Motor.C.setAcceleration(1000);
		pilote.avanceUneCase();
		pilote.recalage();
	}
	
	//Pour faire demi-tour alors qu'on va tourner après (on ne se recale donc pas)
	
	public void goBackSquareBeforeTurn() {
		
		ChangeSquare pilote = new ChangeSquare(55.5, 100, motorG, motorD);
		motorG.setSpeed(200);
		motorD.setSpeed(200);
		Motor.B.setAcceleration(1000);
		Motor.C.setAcceleration(1000);
		pilote.rotate(180);
		motorG.setSpeed(420);
		motorD.setSpeed(420);
		Motor.B.setAcceleration(1000);
		Motor.C.setAcceleration(1000);
		pilote.avanceUneCase();
	}

	//Pour aller sur la case de gauche 
	
	public void goLeftSquare() {
		
		ChangeSquare pilote = new ChangeSquare(55.5, 100, motorG, motorD);
		motorG.setSpeed(200);
		motorD.setSpeed(200);
		Motor.B.setAcceleration(1000);
		Motor.C.setAcceleration(1000);
		pilote.rotate(90);
		motorG.setSpeed(420);
		motorD.setSpeed(420);
		Motor.B.setAcceleration(1000);
		Motor.C.setAcceleration(1000);
		pilote.avanceUneCase();
		pilote.recalage();
	}
	
	//Pour aller sur la case de gauche alors qu'on va tourner après (on ne se recale donc pas)
	
	public void goLeftSquareBeforeTurn() {
		
		ChangeSquare pilote = new ChangeSquare(55.5, 100, motorG, motorD);
		motorG.setSpeed(200);
		motorD.setSpeed(200);
		Motor.B.setAcceleration(1000);
		Motor.C.setAcceleration(1000);
		pilote.rotate(90);
		motorG.setSpeed(420);
		motorD.setSpeed(420);
		Motor.B.setAcceleration(1000);
		Motor.C.setAcceleration(1000);
		pilote.avanceUneCase();
	}

	//Pour aller sur la case de droite 
	
	public void goRightSquare() {
		
		ChangeSquare pilote = new ChangeSquare(55.5, 100, motorG, motorD);
		motorG.setSpeed(200);
		motorD.setSpeed(200);
		Motor.B.setAcceleration(1000);
		Motor.C.setAcceleration(1000);
		pilote.rotate(-90);
		motorG.setSpeed(420);
		motorD.setSpeed(420);
		Motor.B.setAcceleration(1000);
		Motor.C.setAcceleration(1000);
		pilote.avanceUneCase();
		pilote.recalage();
	}
	
	//Pour aller sur la case de droite alors qu'on va tourner après (on ne se recale donc pas)
	
	public void goRightSquareBeforeTurn() {
		
		ChangeSquare pilote = new ChangeSquare(55.5, 100, motorG, motorD);
		motorG.setSpeed(200);
		motorD.setSpeed(200);
		Motor.B.setAcceleration(1000);
		Motor.C.setAcceleration(1000);
		pilote.rotate(-90);
		motorG.setSpeed(420);
		motorD.setSpeed(420);
		Motor.B.setAcceleration(1000);
		Motor.C.setAcceleration(1000);
		pilote.avanceUneCase();
	}
	
	

	/*Fonction qui lit le parcours que doit faire le robot (parcours spécifié dans le main)*/
	
	public void parcours(String[] parcours) {
		
		ChangeSquare pilote = new ChangeSquare(55.5, 100, motorG, motorD);
		String x = "";
		
		for (int i=0 ; i<parcours.length ; i++) {
			x = parcours[i];
			if (x.equals("r") && !parcours[i+1].equals("r") && !parcours[i+1].equals("l")) {// pour aller à droite
				pilote.goRightSquare();
			}
			if (x.equals("l") && !parcours[i+1].equals("r") && !parcours[i+1].equals("l")) {// pour aller à gauche
				pilote.goLeftSquare();
			}
			if (x.equals("r") && (parcours[i+1].equals("r") || parcours[i+1].equals("l"))) {// pour à droite avant de tourner
				pilote.goRightSquareBeforeTurn();
			}
			if (x.equals("l") && (parcours[i+1].equals("r") || parcours[i+1].equals("l"))) {// pour aller à gauche avant de tourner
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

	

	/*Fonction qui recale (latéralement) le robot au centre de la case, en se basant sur "dist" obtenue dans la fonction avanceUneCase()*/ 
	
	public void recalage() {
		
		/*Eléments à changer en fonction de ses préférences*/
		
		Double distanceDeRecalage = (double) 130; //spécifie sur combien de mm le robot va effectuer son recalage
		//attention, (distanceDeRecalage + distanceAprèsRecalageLigne = 240)
		Double distanceMiniDeRecalage = (double) 200; //spécifie à partir de quelle distance au mur le robot va se recaler
		
		/*Variables*/
		
		Double a = (double) 0; //a prendra la valeur de la distance robot-mur la plus petite
		Double b = (double) 0; //b prendra la valeur de la distance robot-mur la plus grande
		Double angle = (double) 0; //angle pour se recaler
		Double parcours = (double) 0; //distance de laquelle le robot doit avancer pour se recaler
		Integer signe = 1;

		
		if (dist < 40) { //attention dist est en cm

			
			if (côté.contentEquals("g")) { //le robot a mesuré la distance robot-mur à gauche
				
				if (dist >= 20) {
					b = dist * 10;
					a = 400 - b;
					LCD.drawString(b.toString(), 1, 4);
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
				if (a <= distanceMiniDeRecalage) {
					angle = signe * Math.atan((b - 200) / distanceDeRecalage) * 180 / Math.PI;
					LCD.drawString(angle.toString(), 1, 2);
					pilote.rotate(angle);
					parcours = Math.sqrt(distanceDeRecalage * distanceDeRecalage + (b - 200) * (b - 200));
					LCD.drawString(parcours.toString(), 1, 3);
					pilote.travel(parcours);
					pilote.rotate((-1) * angle); // pour se remettre à peu près droit après le recalage											
					
				}
				if(a > distanceMiniDeRecalage){
					pilote.travel(distanceDeRecalage); //s'il n'y a pas besoin de se recaler, le robot avance tout droit
				}
			}
		
			
			if(côté.contentEquals("d")){ //le robot a mesuré la distance robot-mur à droite
			
				if (dist >= 20) {
					b = dist * 10;
					a = 400 - b;
					LCD.drawString(b.toString(), 1, 4);
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
				if (a <= distanceMiniDeRecalage) {
					angle = signe * Math.atan((b - 200) / distanceDeRecalage) * 180 / Math.PI;
					LCD.drawString(angle.toString(), 1, 2);
					pilote.rotate(angle);
					parcours = Math.sqrt(distanceDeRecalage * distanceDeRecalage + (b - 200) * (b - 200));
					LCD.drawString(parcours.toString(), 1, 3);
					pilote.travel(parcours);
					pilote.rotate((-1) * angle); // pour se remettre à peu près droit après le recalage
				}
				if(a > distanceMiniDeRecalage){
					pilote.travel(distanceDeRecalage); //s'il n'y a pas besoin de se recaler, le robot avance tout droit
				}
			}	
		}	
		
		LCD.clear();	
	}

	

	/*Fonction qui permet au robot de changer de case*/
	/*Le robot se remet droit sur la ligne blanche puis continue à avancer tout en mesurant sa distance aux murs*/
	
	public void avanceUneCase() {
		
		/*Variables*/
		
		NXTRegulatedMotor prems = null; //premier moteur dont la roue passe la ligne blanche
		NXTRegulatedMotor second = null; //second moteur dont la roue passe la ligne blanche
		long start = 0; //temps auquel la première roue passe la ligne
		long stop = 0; //temps auquel la deuxième roue passe la ligne
		int têta = 0; //angle (par rapport à sa roue arrêtée) duquel le robot doit tourner pour se remettre droit
		int angularSpeed = 0; //vitesse angulaire de rotation du robot quand une roue est arrêtée et l'autre non
		int distanceAprèsRecalageLigne = 110; //distance de laquelle le robot avance juste après s'être recalé sur la ligne
		int delaiMiniRecalage = 30; //delai minimum entre le passage des 2 roues sur la ligne pour que le robot se recale
		
		
		/*Le robot avance jusqu'à avoir détecté la ligne blanche sur ses 2 capteurs lumineux*/
		
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
		}
		else {
			while (lightG.getNormalizedLightValue() < valeurSeuilGauche)
				;
			stop = System.currentTimeMillis();
			prems = motorD;
			second = motorG;
		}
		long delai = stop - start;
		
		
		/*Recalage dynamique sur la ligne blanche (seule une des 2 roues s'arrête, l'autre continue)*/
		
		if (delai > delaiMiniRecalage) { 
			têta = (int) (Math.atan(0.2 * delai * 0.001 / (trackWidth*0.001))* 180 / Math.PI); // en degrés
			int sp = motorD.getSpeed();
			angularSpeed = (int) ((2 * Math.PI * 0.5 * wheelDiameter * (sp /360) / (2 * Math.PI * trackWidth)) * 360); // en degrés
			prems.setSpeed(0); //attention ne pas utiliser prems.stop() car la latence est trop longue
			Delay.msDelay(têta*1000/angularSpeed); //attend un certain temps avant de redémarrer le 2e moteur
			motorG.setSpeed(420);
			motorD.setSpeed(420);
		}
		
		
		/*Le robot mesure sa distance aux murs pendant qu'il avance*/
		
		pilote.travel(distanceAprèsRecalageLigne, true);
		
		this.tete.rotateTo(-90); // regarde à gauche
		côté = "g";
		dist = (double) this.ultrasonic.getDistance(); // attention c'est en cm !							
		LCD.drawString(dist.toString(), 1, 1);
		
		if (dist == 255 || dist >= 40) { //s'il ne voit pas de mur à gauche
			this.tete.rotateTo(90);// regarde à droite	
			côté = "d";
			dist = (double) this.ultrasonic.getDistance();
			LCD.drawString(dist.toString(), 1, 2);
			}
		this.tete.rotateTo(0);
		distFace = (double) this.ultrasonic.getDistance();
		
		while(pilote.isMoving()){
			distFace = (double) this.ultrasonic.getDistance(); // en cm
			if(distFace <= 75){ //si le robot s'approche trop près du mur d'en face
				pilote.stop();
			}
		}
		
		LCD.clear();
	}
}

