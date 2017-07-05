package tools;

import lejos.nxt.Motor;
import lejos.nxt.NXTRegulatedMotor;
import lejos.nxt.SensorPort;
import lejos.nxt.UltrasonicSensor;
import lejos.robotics.RegulatedMotor;
import lejos.robotics.navigation.DifferentialPilot;


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
		//cette fonction doit être appelée quand la case où on veut aller est celle en face du robot
		//si ce n'est pas le cas il faut faire une rotation
	
		this.tete.rotateTo(-180);
		
		int dist = this.ultrasonic.getDistance();
		int distanceDeRecalage = 15;
		int a = 0;
		int b = 0;
		double angle = 0;
		double parcours = 0;
		
		if(dist>40){
			this.tete.rotateTo(180);
			dist = this.ultrasonic.getDistance();
			this.tete.rotateTo(0);
			
			if(dist<40){
				if(dist>=20){
					b = dist;
					a = 40 - b;
				}
				if(dist<20){
					a = dist;
					b = 40 - a;
				}
				angle = Math.atan((b-20)/distanceDeRecalage);
				pilote.rotate(angle);
				parcours = Math.sqrt(distanceDeRecalage*distanceDeRecalage + (b-20)*(b-20));
				pilote.travel(parcours);	
				pilote.rotate(-angle); //pour se remettre à peu près droit
			}
		}
		else{
			
			angle = Math.atan((b-20)/distanceDeRecalage);
			pilote.rotate(angle);
			parcours = Math.sqrt(distanceDeRecalage*distanceDeRecalage + (b-20)*(b-20));
			pilote.travel(parcours);	
			pilote.rotate(-angle); //pour se remettre à peu près droit
			
		} //après cette fonction de recalage, on arrive un peu avant ligne blanche, 
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
	
}
