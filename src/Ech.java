import java.util.LinkedList;

public class Ech {
	private LinkedList<Evt> echeancier;
	private double lambda;
	private double mu;
	private double date_maximale;
	private double depart_max;
	private int dernier_id;

	public Ech(double lambda, double mu, double date_max) {
		Evt premier_evt = new Evt(false, 0, 0);
		echeancier = new LinkedList<Evt>();
		echeancier.add(premier_evt);
		date_maximale = date_max;
		depart_max = 0;
		dernier_id = 0;
		this.lambda = lambda;
		this.mu = mu;
	}

	public void traiter_premier_event() {
		int evt_now = indice_min_elt();
		//System.out.println("J'aurai trait "+echeancier.get(evt_now));
		Evt evt_courant = echeancier.get(evt_now);
		//echeancier.pop();
		System.out.println("Traitement de "+evt_courant);
		if (evt_courant.is_depart() == false) {
			double date_suivante = evt_courant.get_date() + Utile.loi_exp(lambda);
			double date_depart;
			if (echeancier.size() ==1) {
				date_depart = evt_courant.get_date() + Utile.loi_exp(mu);
			} else {
				date_depart = depart_max + Utile.loi_exp(mu);
			}
			depart_max = date_depart;
			evt_courant.set_depart(true);
			evt_courant.set_date(date_depart);
			//inserer_evt(evt_courant);
			if (date_suivante <= date_maximale) {
				Evt nvl_evt = new Evt(false, date_suivante, ++dernier_id);
				inserer_evt(nvl_evt);
			}
		} else {
			echeancier.remove(evt_now);
		}
	}

	private int indice_min_elt(){
		int indice = 0;
		double min = echeancier.get(0).get_date();
		int i = 0;
		for( i=0; i<echeancier.size(); i++) {
			double d = echeancier.get(i).get_date();
			if( d < min) {
				min = d;
				//System.out.println("Win de "+d+" Over "+min);
				indice = i;
			}
		}
		//System.out.println("Min en "+indice);
		//System.out.println("Min me dit de traiter"+echeancier.get(indice));


		return indice;


	}

	private void inserer_evt(Evt evt) {
	/*	int i = 0;
		i = 0;
		while (i < echeancier.size() && (echeancier.get(i).get_date() < evt.get_date()))
			i++;
		echeancier.add(i, evt);
		i = 0;*/
		echeancier.addFirst(evt);
	}

	public boolean est_vide() {
		return echeancier.isEmpty();
	}
}
