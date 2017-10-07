import java.util.LinkedList;

public class Ech {
	private LinkedList<Evt> echeancier;
	private double lambda;
	private double mu;
	private double date_maximale;
	private double depart_max;
	private int dernier_id;
	private Stats s;

	public Ech(double lambda, double mu, double date_max) {
		this.lambda = lambda;
		this.mu = mu;
		this.s = s;
		date_maximale = date_max;

		depart_max = 0;
		dernier_id = 0;
		s = new Stats(lambda, mu, date_maximale);
		Evt premier_evt = new Evt(false, 0, -1, 0);
		echeancier = new LinkedList<Evt>();
		echeancier.add(premier_evt);
	}

	public void traiter_premier_event() {
		/* Récupération du prochain evenement à traiter et recup des info à afficher */
		Evt evt_courant = echeancier.pop();
		String info_affichage = "";//evt_courant.toString();

		/* Traitement si arrivée */
		if (evt_courant.is_depart() == false) {
			s.incrementer_clients();

			/* Calcul dates arrive suivante et départ du client */

			double date_depart;
			if (echeancier.isEmpty()) {
				s.incrementer_clients_sans_attente();
				date_depart = evt_courant.get_date_arrivee() + Utile.loi_exp(mu); // vide : départ = arrivée + service
			}	else {
				date_depart = depart_max + Utile.loi_exp(mu);	// non vide : départ = max(départs)+service
			}
			depart_max = date_depart;

			/* Transformation de l'evenement en départ et insertion dans la file */
			evt_courant.set_depart(true);
			evt_courant.set_date_depart(date_depart);
			inserer_evt(evt_courant);

			/* Création et insertion dans la file de l'arrivée suivant */
			double arrivee_suivante = evt_courant.get_date_arrivee() + Utile.loi_exp(lambda);
			if (arrivee_suivante <= date_maximale) {
				Evt nvl_evt = new Evt(false,  arrivee_suivante, -1, ++dernier_id);
				inserer_evt(nvl_evt);
			}


		} // fin if(!depart)

		/* Traitement si départ */
		else{
			s.add_temps_sejour(evt_courant.get_date_depart()-evt_courant.get_date_arrivee());
		}
		return ;
	}


	private void inserer_evt(Evt evt) {
		int i = 0;
		double date_evt=Math.max(evt.get_date_depart(), evt.get_date_arrivee());
		boolean found_place = false;
	//	if(!evt.is_depart())
			while (i < echeancier.size() && Math.max(echeancier.get(i).get_date_depart(), echeancier.get(i).get_date_arrivee()) < date_evt)
				i++;
	//	else{
	//		if(echeancier.isEmpty()) i=0;
	//		else {
	//			i=echeancier.size() -1;
	//			while(i>=0 && Math.max(echeancier.get(i).get_date_depart(), echeancier.get(i).get_date_arrivee()) > date_evt){
	//				i--;
	//			}
		//	}
		}


		echeancier.add(i, evt);
	}

	public boolean est_vide() {
		return echeancier.isEmpty();
	}

	public Stats get_resultats(){
		return s;
	}
}
