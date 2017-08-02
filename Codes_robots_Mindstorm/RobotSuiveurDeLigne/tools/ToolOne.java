package tools;

public class ToolOne {
	
	Integer EcartALaValeurNoire = 0;
	Integer EcartPrecedent = EcartALaValeurNoire;
	Integer valPID = 0;
	Integer somme_erreur = 0;
	Integer variation_erreur = 0;
	Integer kp = 25;
	Integer ki = 0;
	Integer kd = 100;
			
	public ToolOne() {
		this.EcartALaValeurNoire = 0;
		this.EcartPrecedent = EcartALaValeurNoire;
		this.valPID = 0;
		this.somme_erreur = 0;
		this.variation_erreur = 0;
		this.kp = 25;
		this.ki = 0;
		this.kd = 100;
	}
	
	public Integer getEcartALaValeurNoire(final int LDroiteOuGauche, final int valeurSeuilNoir){
		if(LDroiteOuGauche >= valeurSeuilNoir){ //accessoire
			this.EcartALaValeurNoire = 0;
		}
		else{
			this.EcartALaValeurNoire = Math.abs(LDroiteOuGauche - valeurSeuilNoir);
		}
		return EcartALaValeurNoire;
	}	
	
	public Integer valeurPID(){
		somme_erreur += EcartALaValeurNoire;
		variation_erreur = EcartALaValeurNoire - EcartPrecedent;
		this.valPID = kp*EcartALaValeurNoire + ki*somme_erreur + kd*variation_erreur;
		return valPID;
		
	}
	
	public void setEcartPrecedent(Integer EcartARetenir){
		this.EcartPrecedent = EcartARetenir;
	}
	
}
