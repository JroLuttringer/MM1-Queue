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
		//System.out.println("=============");
		//System.out.println("Traitement de "+evt_courant.toString());
		if( evt_courant.is_depart() == false){			
			// calcul arrivée client suivant
			double date_suivante = evt_courant.get_date() + Utile.loi_exp(lambda);
			//System.out.println("Date d'arrivée suivante : "+date_suivante);
			double date_depart;
			
			if(echeancier.isEmpty()) {
				date_depart = evt_courant.get_date() + Utile.loi_exp(mu);
			}
			else {
				//System.out.println("Depart max avant : "+ depart_max);
				date_depart = depart_max + Utile.loi_exp(mu);
			}
			
			//System.out.println("Date de depart: "+date_depart);
			// calcul départ client courant
			depart_max = date_depart;
			//System.out.println("Date depart :" + date_depart);
			//System.out.println("Depat max après : "+depart_max);
			evt_courant.set_depart(true);
			evt_courant.set_date(date_depart);
			//System.out.println("Inserer 1 ");
			inserer_evt(evt_courant);
			if( date_suivante <= date_maximale){
				Evt nvl_evt = new Evt(false, date_suivante, ++dernier_id);
				//System.out.println("Inserer 2");
				inserer_evt(nvl_evt);	
			}
		}  // fin event arrivee
		else {
			
		}
		
		//System.out.println("Fin : date départ max : " + depart_max);
		//System.out.println("=============");
		
	}
	
	private void inserer_evt(Evt evt){
		int i = 0;

		if (evt.is_depart()) {
			i = echeancier.size()-1;
			//System.out.println(i);
			while(!echeancier.isEmpty() && i >= 0  && (echeancier.get(i).get_date() > evt.get_date() )) /// ->>>> trouver date max et inserer après
				i--;		
			if(echeancier.isEmpty()) i=0;
			echeancier.add(i, evt);	
		}
		else {
			i = 0;
			while(i < echeancier.size() && (echeancier.get(i).get_date() < evt.get_date() )) /// ->>>> trouver date max et inserer après
				i++;	
			echeancier.add(i, evt);	
		}
		
		
		//System.out.println(echeancier.get(i).get_date());

		i=0;
		/*while(i < echeancier.size()){ /// ->>>> trouver date max et inserer après
			System.out.println("File["+i+"] : "+echeancier.get(i).toString());
			i++;
		}*/
	}
	
	public boolean est_vide(){
		return echeancier.isEmpty();
	}
	
}
