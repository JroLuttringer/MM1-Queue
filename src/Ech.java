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
		double depart_suivant;
		double arrivee_suivante;
		double depart;
		while (!echeancier.isEmpty()){
			Evt premier = echeancier.pop();
			if(premier.is_depart()){
				s.add_temps_sejour(premier.get_date_depart()-premier.get_date_arrivee());
			} else {
				if(echeancier.isEmpty()){
					s.incrementer_clients_sans_attente();
					depart = premier.get_date_arrivee() + Utile.loi_exp(mu);
				} else {
					depart = depart_max + Utile.loi_exp(mu);
				}
				premier.set_depart(true);
				premier.set_date_depart(depart);
				inserer_evt(premier);
				depart_max=depart;
				arrivee_suivante = premier.get_date_arrivee()+Utile.loi_exp(lambda);
				if(arrivee_suivante < date_maximale){
					Evt nvl_arrivee = new Evt(false, arrivee_suivante, -1, ++dernier_id);
					inserer_evt(nvl_arrivee);
				}
			}
		}
		s.set_clients(dernier_id);
/*
		while(arrivee_suivante < date_maximale){

			if(echeancier.isEmpty())
				depart_suivant = arrivee_suivante + 1;
			else
				depart_suivant = echeancier.getFirst().get_date_depart();

			if (arrivee_suivante < depart_suivant){
				if(echeancier.isEmpty()){
					s.incrementer_clients_sans_attente();
					depart = arrivee_suivante + Utile.loi_exp(mu);
				} else {
					depart = echeancier.getLast().get_date_depart() + Utile.loi_exp(mu);
				}
				echeancier.addLast(new Evt(true, arrivee_suivante, depart, ++dernier_id));
				arrivee_suivante+=Utile.loi_exp(lambda);
			} else {
				Evt e = echeancier.pop();
				depart_max = depart_max;
				s.add_temps_sejour(e.get_date_depart()-e.get_date_arrivee());
			}
		}
		s.set_clients(dernier_id);
		while(!echeancier.isEmpty()){
			Evt e = echeancier.pop();
			s.add_temps_sejour(e.get_date_depart()-e.get_date_arrivee());
		}
		System.out.println(echeancier.size());
*/
	}

	public void inserer_evt(Evt e){
		double date_e = Math.max(e.get_date_depart(), e.get_date_arrivee());
		if(date_e > depart_max)
			echeancier.addLast(e);
		else{
			int i =0;
			while(echeancier.get(i).get_date_depart() < date_e)
				i++;
			echeancier.add(i, e);
		}

	}

	public Stats get_resultats(){
		return s;
	}
}
