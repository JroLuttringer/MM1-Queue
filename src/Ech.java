import java.util.LinkedList;


public class Ech {
	
	private LinkedList<Evt> echeancier;
	private double lambda;
	private double mu;
	private double date_maximale;
	
	private double depart_max;
	private int dernier_id;
	
	public Ech(double lambda, double mu, double date_max){
		Evt premier_evt = new Evt(false, 0, 0);
		echeancier = new LinkedList<Evt>();
		echeancier.add(premier_evt);
		date_maximale = date_max;
		depart_max = 0;
		dernier_id = 0;
		this.lambda = lambda;
		this.mu = mu;
	}
	
	public void traiter_premier_event(){
		Evt evt_courant = echeancier.pop();
		System.out.println(evt_courant.toString());
		if( evt_courant.is_depart() == false){			
			// calcul arrivée client suivant
			double date_suivante = evt_courant.get_date() + Utile.loi_exp(lambda);
			if( date_suivante <= date_maximale){
				Evt nvl_evt = new Evt(false, date_suivante, dernier_id +1);
				dernier_id++;
				inserer_evt(nvl_evt);	
			}
			// calcul départ client courant
			double date_depart = depart_max + Utile.loi_exp(mu);
			depart_max = date_depart;
			evt_courant.set_depart(true);
			evt_courant.set_date(date_depart);
			inserer_evt(evt_courant);
		}  // fin event arrivee
		else {
			
		}
		
	}
	
	private void inserer_evt(Evt evt){
		int i =0;
		while(i < echeancier.size()){ /// ->>>> trouver date max et inserer après
			
			i++;	
		}
		echeancier.add(i, evt);		
		System.out.println(i);
	}
	
	public boolean est_vide(){
		return echeancier.isEmpty();
	}
	
}
