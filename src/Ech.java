import java.util.LinkedList;

public class Ech {
	private LinkedList<Evt> echeancier;
	private double lambda;
	private double mu;
	private double date_maximale;
	private double depart_max;
	private int dernier_id;
	private double arrivee_suivante = 0;
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

	public void demarrer_xp() {
		/* Récupération du prochain evenement à traiter et recup des info à afficher */
		while(arrivee_suivante < date_maximale){
			double depart_suivant;
			if(echeancier.isEmpty())
				depart_suivant = arrivee_suivante + 1;
			else
				depart_suivant = echeancier.getFirst().get_date_depart();

			if (arrivee_suivante < depart_suivant){
				double depart;
				if(!echeancier.isEmpty()){
					s.incrementer_clients_sans_attente();
					depart = arrivee_suivante + Utile.loi_exp(mu);
				} else {
					depart = depart_max + Utile.loi_exp(mu);
				}
				echeancier.addLast(new Evt(true, arrivee_suivante, depart, ++dernier_id));
				arrivee_suivante+=Utile.loi_exp(lambda);
			} else {
				Evt e = echeancier.pop();
				s.add_temps_sejour(e.get_date_depart()-e.get_date_depart());
			}
		}
		s.set_clients(dernier_id);
	}

	public boolean est_vide() {
		return echeancier.isEmpty();
	}

	public Stats get_resultats(){
		return s;
	}
}
