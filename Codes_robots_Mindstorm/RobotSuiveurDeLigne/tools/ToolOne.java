package tools;

public class ToolOne {
	
	
	/*
	 * Initialisations
	 */
	
	Integer EcartALaValeurNoire = 0;
	Integer EcartPrecedent = EcartALaValeurNoire;
	Integer valPID = 0;
	Integer somme_erreur = 0;
	Integer variation_erreur = 0;
	Integer kp = 25;
	Integer ki = 0;
	Integer kd = 100;
			
	
	/*
	 * Constructeur
	 */
	
	public ToolOne() {
		this.EcartALaValeurNoire = 0;
		this.EcartPrecedent = EcartALaValeurNoire;
		this.valPID = 0;
		this.somme_erreur = 0;
		this.variation_erreur = 0;
		
		this.kp = 25; //� r�gler
		this.ki = 0; //� r�gler
		this.kd = 100; //� r�gler
	}
	
	
	/*
	 * Fonction qui calcule l'�cart � la valeur consid�r�e comme noire, en fonction des valeurs obtenues par les capteurs lumineux
	 */
	
	public Integer getEcartALaValeurNoire(final int LDroiteOuGauche, final int valeurSeuilNoir){
		if(LDroiteOuGauche >= valeurSeuilNoir){ //accessoire
			this.EcartALaValeurNoire = 0;
		}
		else{
			this.EcartALaValeurNoire = Math.abs(LDroiteOuGauche - valeurSeuilNoir);
		}
		return EcartALaValeurNoire;
	}	
	
	
	/*
	 * Fonction qui calcule un PID
	 */
	
	public Integer valeurPID(){
		somme_erreur += EcartALaValeurNoire;
		variation_erreur = EcartALaValeurNoire - EcartPrecedent;
		this.valPID = kp*EcartALaValeurNoire + ki*somme_erreur + kd*variation_erreur;
		return valPID;
		
	}
	
	
	/*
	 * Fonction qui permet de d�finir la valeur de EcartPrecedent
	 */
	
	public void setEcartPrecedent(Integer EcartARetenir){
		this.EcartPrecedent = EcartARetenir;
	}
	
}
