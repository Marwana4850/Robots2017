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
	public static double trackWidth = 10;
	
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
		pilote.travel(400);
	}
	public void goBackSquare(){
		pilote.travel(-400);
	}
	public void goLeftSquare(){
		pilote.rotate(-90);
		pilote.travel(400);
	}
	public void goRightSquare(){
		pilote.rotate(90);
		pilote.travel(400);
	}
	
	private UltrasonicSensor ultrasonic = new UltrasonicSensor(SensorPort.S1);
	
	public void recalage(){ 
		//cette fonction doit �tre appel�e quand la case o� on veut aller est celle en face du robot
		//si ce n'est pas le cas il faut faire une rotation
	
		this.tete.rotateTo(-90); //regarde � gauche
		
		Delay.msDelay(400);
		Double dist = (double) this.ultrasonic.getDistance(); //attention c'est en cm !
		LCD.drawString(dist.toString(), 1, 1);
		//Button.waitForAnyPress();
		LCD.clear();
		
		Double distanceDeRecalage = (double) 150;
		Double a = (double) 0;
		Double b = (double) 0;
		Double angle = (double) 0;
		Double parcours = (double) 0;
		Integer signe = 1;
		
		if(dist == 255 || dist >= 40){
			this.tete.rotateTo(90);//regarde � droite
			Delay.msDelay(400);
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
					signe = 1;
				}
				if(dist<20){
					a = dist*10;
					b = 400 - a;
					LCD.drawString(b.toString(), 1, 4);
					LCD.drawString(a.toString(), 1, 5);
					signe = -1;
				}
				angle = signe*Math.atan((b-200)/distanceDeRecalage)*180/Math.PI; //positif = sens non trigo...
				LCD.drawString(angle.toString(), 1, 2);
				this.tete.rotateTo(0);
				
				if(angle>=5 || angle<=-5){
				pilote.rotate(angle);
				Delay.msDelay(500);
				parcours = Math.sqrt(distanceDeRecalage*distanceDeRecalage + (b-200)*(b-200));
				LCD.drawString(parcours.toString(), 1, 3);
				pilote.travel(parcours);
				Delay.msDelay(500);
				pilote.rotate((-1)*angle); //pour se remettre � peu pr�s droit
				}
			}
		}
		else{
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
			angle = signe*Math.atan((b-200)/distanceDeRecalage)*180/Math.PI;
			LCD.drawString(angle.toString(), 1, 2);
			this.tete.rotateTo(0);
			
			if(angle>=5 || angle<=-5){
			pilote.rotate(angle);
			Delay.msDelay(500);
			parcours = Math.sqrt(distanceDeRecalage*distanceDeRecalage + (b-200)*(b-200));
			LCD.drawString(parcours.toString(), 1, 3);
			pilote.travel(parcours);
			Delay.msDelay(500);
			pilote.rotate((-1)*angle); //pour se remettre � peu pr�s droit
			}
			
		}
		//Button.waitForAnyPress();
		LCD.clear();
		//apr�s cette fonction de recalage, on arrive un peu avant ligne blanche, 
		//il faut alors se remettre droit gr�ce � celle-ci
		// -> en fait il suffit d'appeler la fonction avanceUneCase()
		
		/*Fonction :
		tourner la t�te � gauche (tete.rotateTo(-180)), si dist>... tourner la t�te � droite (tete.rotateTo(180)
		si dist>... ne rien faire
		
		(b = dist si dist >20 -> donc a = (40-b))
		(a = dist sinon -> donc b=(40-a))
		
		si dist < ... d'un des 2 c�t�s, angle = arctan((b-20)/40)
		->rotate(angle) 
		hyp = sqrt(40� + (b-20)�)
		->travel(hyp)
		*/
	
	}
	
	
	// Valeur seuil pour diff�rencier le noir et le blanc
		private static final int valeurSeuilGauche = 487;
		private static final int valeurSeuilDroit = 487;
	
	// Capteurs lumineux
		private LightSensor lightG = new LightSensor(SensorPort.S4);
		private LightSensor lightD = new LightSensor(SensorPort.S3);
	
	public void avanceUneCase() {
		NXTRegulatedMotor second = null;
		long start = 0;
		long stop = 0;
		pilote.forward();
		while (lightG.getNormalizedLightValue() < valeurSeuilGauche
				&& lightD.getNormalizedLightValue() < valeurSeuilDroit)
			;
		start = System.currentTimeMillis();
		if (lightG.getNormalizedLightValue() > valeurSeuilGauche) {
			while (lightD.getNormalizedLightValue() < valeurSeuilDroit)
				;
			stop = System.currentTimeMillis();
			second = motorD;
		} else {
			while (lightG.getNormalizedLightValue() < valeurSeuilGauche)
				;
			stop = System.currentTimeMillis();
			second = motorG;
		}
		long delai = stop - start;
		pilote.stop();
		if (delai > 15) {
			second.rotate((int) (delai / 1.3));
		}
		Delay.msDelay(1000);
		pilote.travel(240);
	}
	
}
