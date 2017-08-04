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

@SuppressWarnings("unused")
public class ChangeSquare extends DifferentialPilot {

	
	
	/*
	 * Eléments à changer en fonction de la construction mécanique du robot
	 * 
	 */
	
	private UltrasonicSensor ultrasonic = new UltrasonicSensor(SensorPort.S1); //capteur Ultrason
	private LightSensor lightG = new LightSensor(SensorPort.S4); //capteur lumineux gauche
	private LightSensor lightD = new LightSensor(SensorPort.S3); //capteur lumineux droit
	public NXTRegulatedMotor tete = Motor.A; //moteur qui permet de tourner l'axe sur lequel se trouve le capteur ultrason
	public Double distanceDeRecalage = (double) 90; //spécifie sur combien de mm le robot va effectuer son recalage
	//attention, (distanceDeRecalage + distanceApresRecalageLigne = 240)
	
	
	
	/*
	 * Initialisations
	 *
	 */
	
	public NXTRegulatedMotor motorG = Motor.C; //moteur gauche
	public NXTRegulatedMotor motorD = Motor.B; //moteur droit
	public double wheelDiameter = 55.5; //diamètre des roues
	public double trackWidth = 100; //écart entre les roues (attention, à régler assez empiriquement
										   //jusqu'à avoir des rotations précises du robot)


		
	/*
	 * Variables utilisées dans plusieurs fonctions
	 * 
	 */
	
	public Double dist = (double) 0;
	public Double distFace = (double) 0;
	public String cote = "";
	private static final int valeurSeuilGauche = 487; // Valeur seuil pour différencier le noir et le blanc
	private static final int valeurSeuilDroit = 487; // Valeur seuil pour différencier le noir et le blanc


		
	/*
	 * Constructeur
	 * 
	 */
	
	public ChangeSquare(final double wheelDiameter, final double trackWidth, final NXTRegulatedMotor leftMotor,
			final NXTRegulatedMotor rightMotor) {
		
		super(wheelDiameter, trackWidth, leftMotor, rightMotor);
		this.wheelDiameter = wheelDiameter;
		this.trackWidth = trackWidth;
		this.motorG = leftMotor;
		this.motorD = rightMotor;
	}

	
	
	/*
	 * Fonctions  simples de changement de case
	 * 
	 */
	
	
	//Pour aller sur la case d'en face
	
	public void goFrontSquare() {
		
		motorG.setSpeed(420);
		motorD.setSpeed(420);
		Motor.B.setAcceleration(1000);
		Motor.C.setAcceleration(1000);
		avanceUneCase();
		recalage();
	}
	
	//Pour aller sur la case d'en face alors qu'on va tourner après (on ne se recale donc pas)
	
	public void goFrontSquareBeforeTurn() {
		
		motorG.setSpeed(420);
		motorD.setSpeed(420);
		Motor.B.setAcceleration(1000);
		Motor.C.setAcceleration(1000);
		avanceUneCase();
		travel(-distanceDeRecalage);
	}

	//Pour faire demi-tour 
	
	public void goBackSquare() {
		
		motorG.setSpeed(200);
		motorD.setSpeed(200);
		Motor.B.setAcceleration(1000);
		Motor.C.setAcceleration(1000);
		rotate(180);
		motorG.setSpeed(420);
		motorD.setSpeed(420);
		Motor.B.setAcceleration(1000);
		Motor.C.setAcceleration(1000);
		avanceUneCase();
		recalage();
	}
	
	//Pour faire demi-tour alors qu'on va tourner après (on ne se recale donc pas)
	
	public void goBackSquareBeforeTurn() {
		
		motorG.setSpeed(200);
		motorD.setSpeed(200);
		Motor.B.setAcceleration(1000);
		Motor.C.setAcceleration(1000);
		rotate(180);
		motorG.setSpeed(420);
		motorD.setSpeed(420);
		Motor.B.setAcceleration(1000);
		Motor.C.setAcceleration(1000);
		avanceUneCase();
		travel(-distanceDeRecalage);
	}

	//Pour aller sur la case de gauche 
	
	public void goLeftSquare() {
		
		motorG.setSpeed(200);
		motorD.setSpeed(200);
		Motor.B.setAcceleration(1000);
		Motor.C.setAcceleration(1000);
		rotate(-90);
		motorG.setSpeed(420);
		motorD.setSpeed(420);
		Motor.B.setAcceleration(1000);
		Motor.C.setAcceleration(1000);
		avanceUneCase();
		recalage();
	}
	
	//Pour aller sur la case de gauche alors qu'on va tourner après (on ne se recale donc pas)
	
	public void goLeftSquareBeforeTurn() {
		
		motorG.setSpeed(200);
		motorD.setSpeed(200);
		Motor.B.setAcceleration(1000);
		Motor.C.setAcceleration(1000);
		rotate(90);
		motorG.setSpeed(420);
		motorD.setSpeed(420);
		Motor.B.setAcceleration(1000);
		Motor.C.setAcceleration(1000);
		avanceUneCase();
		travel(-distanceDeRecalage);
	}

	//Pour aller sur la case de droite 
	
	public void goRightSquare() {
		
		motorG.setSpeed(200);
		motorD.setSpeed(200);
		Motor.B.setAcceleration(1000);
		Motor.C.setAcceleration(1000);
		rotate(90);
		motorG.setSpeed(420);
		motorD.setSpeed(420);
		Motor.B.setAcceleration(1000);
		Motor.C.setAcceleration(1000);
		avanceUneCase();
		recalage();
	}
	
	//Pour aller sur la case de droite alors qu'on va tourner après (on ne se recale donc pas)
	
	public void goRightSquareBeforeTurn() {
		
		motorG.setSpeed(200);
		motorD.setSpeed(200);
		Motor.B.setAcceleration(1000);
		Motor.C.setAcceleration(1000);
		rotate(-90);
		motorG.setSpeed(420);
		motorD.setSpeed(420);
		Motor.B.setAcceleration(1000);
		Motor.C.setAcceleration(1000);
		avanceUneCase();
		travel(distanceDeRecalage);
	}
	
	

	/*
	 * Fonction qui lit le parcours que doit faire le robot (parcours spécifié dans le main)
	 * 
	 */
	
	public void parcours(String[] parcours) {
		
		String x = "";
		
		for (int i = 0 ; i < (parcours.length-2) ; i++) {
			x = parcours[i];
			if (x.equals("r") && !parcours[i+1].equals("r") && !parcours[i+1].equals("l")) {// pour aller à droite
				goRightSquare();
			}
			if (x.equals("l") && !parcours[i+1].equals("r") && !parcours[i+1].equals("l")) {// pour aller à gauche
				goLeftSquare();
			}
			if (x.equals("r") && (parcours[i+1].equals("r") || parcours[i+1].equals("l"))) {// pour à droite avant de tourner
				goRightSquare();
			}
			if (x.equals("l") && (parcours[i+1].equals("r") || parcours[i+1].equals("l"))) {// pour aller à gauche avant de tourner
				goLeftSquare();
			}
			if (x.equals("f") && !parcours[i+1].equals("r") && !parcours[i+1].equals("l")) {// pour aller tout droit
				goFrontSquare();
			}
			if (x.equals("b") && !parcours[i+1].equals("r") && !parcours[i+1].equals("l")) {// pour faire demi-tour
				goBackSquare();
			}
			if (x.equals("f") && (parcours[i+1].equals("r") || parcours[i+1].equals("l"))) {// pour aller tout droit avant de tourner
				goFrontSquare();
			}
			if (x.equals("b") && (parcours[i+1].equals("r") || parcours[i+1].equals("l"))) {// pour faire demi-tour avant de tourner
				goBackSquare();
			}
		}
		
		String y = parcours[parcours.length-1]; // dernier déplacment
		if (y.equals("r")) {// pour aller à droite
			goRightSquare();
		}
		if (y.equals("l")) {// pour aller à gauche
			goLeftSquare();
		}
		if (y.equals("f")) {// pour aller tout droit
			goFrontSquare();
		}
		if (y.equals("b")) {// pour faire demi-tour
			goBackSquare();
		}
	}

	
	
	/*
	 * Fonctions qui permettent le déplacement du robot dans le labyrinthe, sans recalage
	 */
	
	public void goSimple(String d){
		motorG.setSpeed(200);
		motorD.setSpeed(200);
		Motor.B.setAcceleration(1000);
		Motor.C.setAcceleration(1000);

		if(d.equals("f")){
			travel(-405);
		}
		if(d.equals("b")){
			rotate(180);
			travel(-405);
		}
		if(d.equals("r")){
			rotate(90);
			travel(-405);
		}
		if(d.equals("l")){
			rotate(-90);
			travel(-405);
		}
	}
	public void parcoursSimple(String[] parcours) {
			
			String x = "";
	
			for (int i = 0 ; i < (parcours.length) ; i++) {
				x = parcours[i];
				if (x.equals("r")) {// pour aller à droite
					goSimple("r");
				}
				if (x.equals("l")) {// pour aller à gauche
					goSimple("l");
				}
				if (x.equals("f")) {// pour aller tout droit
					goSimple("f");
				}
				if (x.equals("b")) {// pour faire demi-tour
					goSimple("b");
				}
			}
			
		}
	
	
	
	/*
	 * Fonctions qui permettent le déplacement du robot dans le labyrinthe, avec recalage sur ligne mais sans recalage US
	 */
	
	public void goAvecLigne(String d){
		motorG.setSpeed(200);
		motorD.setSpeed(200);
		Motor.B.setAcceleration(1000);
		Motor.C.setAcceleration(1000);
		if(d.equals("f")){
			avanceUneCase();
			travel(-distanceDeRecalage);
			motorG.setSpeed(200);
			motorD.setSpeed(200);
			Motor.B.setAcceleration(1000);
			Motor.C.setAcceleration(1000);
		}
		if(d.equals("b")){
			rotate(180);
			avanceUneCase();
			travel(-distanceDeRecalage);
			motorG.setSpeed(200);
			motorD.setSpeed(200);
			Motor.B.setAcceleration(1000);
			Motor.C.setAcceleration(1000);
		}
		if(d.equals("r")){
			rotate(90);
			avanceUneCase();
			travel(-distanceDeRecalage);
			motorG.setSpeed(200);
			motorD.setSpeed(200);
			Motor.B.setAcceleration(1000);
			Motor.C.setAcceleration(1000);
		}
		if(d.equals("l")){
			rotate(-90);
			avanceUneCase();
			travel(-distanceDeRecalage);
			motorG.setSpeed(200);
			motorD.setSpeed(200);
			Motor.B.setAcceleration(1000);
			Motor.C.setAcceleration(1000);
		}
	}
	public void parcoursAvecLigne(String[] parcours) {
			
			String x = "";
	
			for (int i = 0 ; i < (parcours.length) ; i++) {
				x = parcours[i];
				if (x.equals("r")) {// pour aller à droite
					goAvecLigne("r");
				}
				if (x.equals("l")) {// pour aller à gauche
					goAvecLigne("l");
				}
				if (x.equals("f")) {// pour aller tout droit
					goAvecLigne("f");
				}
				if (x.equals("b")) {// pour faire demi-tour
					goAvecLigne("b");
				}
			}
			
		}
	
	

	/*
	 * Fonction qui recale (latéralement) le robot au centre de la case, en se basant sur "dist" obtenue dans la fonction avanceUneCase()
	 * 
	 */ 
	
	
	public void recalage() {
		
		
		/*Eléments à changer en fonction de ses préférences*/
		
		Double distanceMiniDeRecalage = (double) 200; //spécifie à partir de quelle distance au mur le robot va se recaler

		
		/*Variables*/
		
		Double a = (double) 0; //a prendra la valeur de la distance robot-mur la plus petite
		Double b = (double) 0; //b prendra la valeur de la distance robot-mur la plus grande
		Double angle = (double) 0; //angle pour se recaler
		Double parcours = (double) 0; //distance de laquelle le robot doit avancer pour se recaler
		Integer signe = 1;

		
		if (dist < 40) { //attention dist est en cm

			
			if (cote.contentEquals("g")) { //le robot a mesuré la distance robot-mur à gauche
				
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
					rotate(-angle);
					parcours = Math.sqrt(distanceDeRecalage * distanceDeRecalage + (b - 200) * (b - 200));
					LCD.drawString(parcours.toString(), 1, 3);
					travel(-parcours);
					rotate(-(-1) * angle); // pour se remettre à peu près droit après le recalage											
					
				}
				
			}
		
			
			if(cote.contentEquals("d")){ //le robot a mesuré la distance robot-mur à droite
			
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
					rotate(-angle);
					parcours = Math.sqrt(distanceDeRecalage * distanceDeRecalage + (b - 200) * (b - 200));
					LCD.drawString(parcours.toString(), 1, 3);
					travel(-parcours);
					rotate(-(-1) * angle); // pour se remettre à peu près droit après le recalage
				}
			
			}	
	
		}	
		
		if(a > distanceMiniDeRecalage || dist >= 40){
			travel(-distanceDeRecalage); //s'il n'y a pas besoin de se recaler, le robot avance tout droit
		}
		
		//Button.waitForAnyPress();
		LCD.clear();	
	}

	

	/*
	 * Fonction qui permet au robot de changer de case
	 * 
	 */
	
	/*Le robot se remet droit sur la ligne blanche puis continue à avancer tout en mesurant sa distance aux murs*/
	
	
	public void avanceUneCase() {
		
		
		/*Variables*/
		
		NXTRegulatedMotor prems = null; //premier moteur dont la roue passe la ligne blanche
		NXTRegulatedMotor second = null; //second moteur dont la roue passe la ligne blanche
		long start = 0; //temps auquel la première roue passe la ligne
		long stop = 0; //temps auquel la deuxième roue passe la ligne
		int theta = 0; //angle (par rapport à sa roue arrêtée) duquel le robot doit tourner pour se remettre droit
		int angularSpeed = 0; //vitesse angulaire de rotation du robot quand une roue est arrêtée et l'autre non
		int distanceApresRecalageLigne = 150; //distance de laquelle le robot avance juste après s'être recalé sur la ligne
		int delaiMiniRecalage = 30; //delai minimum entre le passage des 2 roues sur la ligne pour que le robot se recale
		
		
		/*Le robot avance jusqu'à avoir détecté la ligne blanche sur ses 2 capteurs lumineux*/
		
		backward();
		
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
			theta = (int) (Math.atan(0.2 * delai * 0.001 / (trackWidth*0.001))* 180 / Math.PI); // en degrés
			int sp = motorD.getSpeed();
			angularSpeed = (int) ((2 * Math.PI * 0.5 * wheelDiameter * (sp /360) / (2 * Math.PI * trackWidth)) * 360); // en degrés
			prems.setSpeed(0); //attention ne pas utiliser prems.stop() car la latence est trop longue
			Delay.msDelay(theta*1000/angularSpeed); //attend un certain temps avant de redémarrer le 2e moteur
			motorG.setSpeed(420);
			motorD.setSpeed(420);
		}
		
		
		/*Le robot mesure sa distance aux murs pendant qu'il avance*/
		
		travel(-distanceApresRecalageLigne, true);
		
		this.tete.rotateTo(-90); // regarde à gauche
		cote = "g";
		dist = (double) this.ultrasonic.getDistance(); // attention c'est en cm !							
		LCD.drawString(dist.toString(), 1, 1);
		
		if (dist == 255 || dist >= 40) { //s'il ne voit pas de mur à gauche
			this.tete.rotateTo(90);// regarde à droite	
			cote = "d";
			dist = (double) this.ultrasonic.getDistance();
			LCD.drawString(dist.toString(), 1, 2);
			}
		this.tete.rotateTo(0);
		distFace = (double) this.ultrasonic.getDistance();
		
		while(isMoving()){
			distFace = (double) this.ultrasonic.getDistance(); // en cm
			if(distFace <= 75){ //si le robot s'approche trop près du mur d'en face
				stop();
			}
		}
		
		//Button.waitForAnyPress();
		LCD.clear();
	}
}

