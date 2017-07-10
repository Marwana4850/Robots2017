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
	
	static DifferentialPilot pilote = new DifferentialPilot(wheelDiameter, trackWidth, motorG, motorD);
	
	public ChangeSquare(final double wheelDiameter, final double trackWidth, final NXTRegulatedMotor leftMotor, final NXTRegulatedMotor rightMotor) {		
		super(wheelDiameter, trackWidth, leftMotor, rightMotor);	
		ChangeSquare.wheelDiameter = wheelDiameter;
		ChangeSquare.trackWidth = trackWidth;
		ChangeSquare.motorG = leftMotor;
		ChangeSquare.motorD = rightMotor;
		ChangeSquare.pilote = new DifferentialPilot(wheelDiameter, trackWidth, motorG, motorD);
	}
	
	public void goFrontSquare(){
		ChangeSquare pilote = new ChangeSquare(55.5, 100, motorG, motorD);
		motorG.setSpeed(420);
		motorD.setSpeed(420);
		Motor.B.setAcceleration(1500);
		Motor.C.setAcceleration(500);
		//pilote.travel(400);
		pilote.recalage();
		pilote.avanceUneCase();
	}
	public void goBackSquare(){
		ChangeSquare pilote = new ChangeSquare(55.5, 100, motorG, motorD);
		motorG.setSpeed(200);
		motorD.setSpeed(200);
		Motor.B.setAcceleration(500);
		Motor.C.setAcceleration(500);
		//pilote.travel(-400);
		pilote.rotate(180);
		pilote.recalage();
		pilote.avanceUneCase();
	}
	public void goLeftSquare(){
		ChangeSquare pilote = new ChangeSquare(55.5, 100, motorG, motorD);
		motorG.setSpeed(200);
		motorD.setSpeed(200);
		Motor.B.setAcceleration(500);
		Motor.C.setAcceleration(500);
		pilote.rotate(90);
		pilote.recalage();
		pilote.avanceUneCase();
		//pilote.travel(400);
	}
	public void goRightSquare(){
		ChangeSquare pilote = new ChangeSquare(55.5, 100, motorG, motorD);
		motorG.setSpeed(200);
		motorD.setSpeed(200);
		Motor.B.setAcceleration(500);
		Motor.C.setAcceleration(500);
		pilote.rotate(-90);
		pilote.recalage();
		pilote.avanceUneCase();
		//pilote.travel(400);
	}
	public void parcours(String[] parcours){
		ChangeSquare pilote = new ChangeSquare(55.5, 100, motorG, motorD);
		for(String x : parcours){
			if(x.equals("r")){//pour aller à droite
				pilote.goRightSquare();
			}
			if(x.equals("l")){//pour aller à gauche
				pilote.goLeftSquare();
			}
			if(x.equals("f")){//pour aller tout droit
				pilote.goFrontSquare();
			}
			if(x.equals("b")){//pour faire demi-tour
				pilote.goBackSquare();
			}
		}
	}
	
	private UltrasonicSensor ultrasonic = new UltrasonicSensor(SensorPort.S1);
	
	public void recalage(){ 
		//cette fonction doit être appelée quand la case où on veut aller est celle en face du robot
		//si ce n'est pas le cas il faut faire une rotation
	
		this.tete.rotateTo(-90); //regarde à gauche
		
		//Delay.msDelay(400);
		Double dist = (double) this.ultrasonic.getDistance(); //attention c'est en cm !
		LCD.drawString(dist.toString(), 1, 1);
		//Button.waitForAnyPress();
		LCD.clear();
		
		Double distanceDeRecalage = (double) 100;
		Double a = (double) 0;
		Double b = (double) 0;
		Double angle = (double) 0;
		Double parcours = (double) 0;
		Integer signe = 1;
		
		if(dist == 255 || dist >= 40){
			this.tete.rotateTo(90);//regarde à droite
			//Delay.msDelay(400);
			dist = (double) this.ultrasonic.getDistance();
			LCD.drawString(dist.toString(), 1, 1);
			//Button.waitForAnyPress();
			LCD.clear();
			
			this.tete.rotateTo(0);
			
			if(dist<40){
				if(dist>=20){
					b = dist*10;
					LCD.drawString(b.toString(), 1, 4);
					a = 400 - b;
					LCD.drawString(a.toString(), 1, 5);
					signe = -1;
				}
				if(dist<20){
					a = dist*10;
					b = 400 - a;
					LCD.drawString(b.toString(), 1, 4);
					LCD.drawString(a.toString(), 1, 5);
					signe = 1;
				}
				if(a <= 150){
				angle = signe*Math.atan((b-200)/distanceDeRecalage)*180/Math.PI; 
				LCD.drawString(angle.toString(), 1, 2);
				//Delay.msDelay(1000);
				this.tete.rotateTo(0);
				
				//if(angle>=20 || angle<=-20){
				
				motorG.setSpeed(420);
				motorD.setSpeed(420);
				Motor.B.setAcceleration(500);
				Motor.C.setAcceleration(500);
				
				pilote.rotate(angle);
				//Delay.msDelay(500);
				parcours = Math.sqrt(distanceDeRecalage*distanceDeRecalage + (b-200)*(b-200));
				LCD.drawString(parcours.toString(), 1, 3);
				pilote.travel(parcours);
				//Delay.msDelay(500);
				pilote.rotate((-1)*angle); //pour se remettre à peu près droit
				//}
				}
				this.tete.rotateTo(0);
			}
		}
		else{
			if(dist>=20){
				b = dist*10;
				LCD.drawString(b.toString(), 1, 4);
				a = 400 - b;
				LCD.drawString(a.toString(), 1, 5);
				signe = 1;
			}
			if(dist<20){
				a = dist*10;
				
				b = 400 - a;
				LCD.drawString(b.toString(), 1, 4);
				LCD.drawString(a.toString(), 1, 5);
				signe = -1;
			}		
			if(a <= 150){
			angle = signe*Math.atan((b-200)/distanceDeRecalage)*180/Math.PI;
			LCD.drawString(angle.toString(), 1, 2);
			//Delay.msDelay(1000);
			this.tete.rotateTo(0);
			
			//if(angle>=20 || angle<=-20){
			
			motorG.setSpeed(420);
			motorD.setSpeed(420);
			Motor.B.setAcceleration(700);
			Motor.C.setAcceleration(700);
			
			pilote.rotate(angle);
			//Delay.msDelay(500);
			parcours = Math.sqrt(distanceDeRecalage*distanceDeRecalage + (b-200)*(b-200));
			LCD.drawString(parcours.toString(), 1, 3);
			pilote.travel(parcours);
			//Delay.msDelay(500);
			pilote.rotate((-1)*angle); //pour se remettre à peu près droit
			//}
			}
			this.tete.rotateTo(0);
			
		}
		//Button.waitForAnyPress();
		LCD.clear();
		//après cette fonction de recalage, on arrive un peu avant ligne blanche, 
		//il faut alors se remettre droit grâce à celle-ci
		// -> en fait il suffit d'appeler la fonction avanceUneCase()
		
		/*Fonction :
		tourner la tête à gauche (tete.rotateTo(-180)), si dist>... tourner la tête à droite (tete.rotateTo(180)
		si dist>... ne rien faire
		
		(b = dist si dist >20 -> donc a = (40-b))
		(a = dist sinon -> donc b=(40-a))
		
		si dist < ... d'un des 2 côtés, angle = arctan((b-20)/40)
		->rotate(angle) 
		hyp = sqrt(40² + (b-20)²)
		->travel(hyp)
		*/
	
	}
	
	
	// Valeur seuil pour différencier le noir et le blanc
		private static final int valeurSeuilGauche = 487;
		private static final int valeurSeuilDroit = 487;
	
	// Capteurs lumineux
		private LightSensor lightG = new LightSensor(SensorPort.S4);
		private LightSensor lightD = new LightSensor(SensorPort.S3);
	
	public void avanceUneCase() {
		motorG.setSpeed(420);
		motorD.setSpeed(420);
		Motor.B.setAcceleration(2000);
		Motor.C.setAcceleration(2000);
		NXTRegulatedMotor second = null;
		NXTRegulatedMotor prems = null;
		long start = 0;
		long stop = 0;
		int têta = 0;
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
		pilote.stop();
		if (delai > /*15*/ 100) {
			//second.rotate((int) (delai/2.2));
			têta = (int) Math.atan(0.2*delai/trackWidth);
			prems.setSpeed(0);
			second.rotate(têta);
		}
		//Delay.msDelay(1000);
		motorG.setSpeed(420);
		motorD.setSpeed(420);
		Motor.B.setAcceleration(1200);
		Motor.C.setAcceleration(1200);
		pilote.travel(200);
		}
	
}
