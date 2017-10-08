import java.util.LinkedList;

public class Ech {
	private LinkedList<Evt> file;
	private double lambda;
	private double mu;
	private double date_maximale;
	private double depart_max;
	private int dernier_id;
	private double arrivee_suivante;
	private Stats s;

	public Ech(double lambda, double mu, double date_max) {
		this.lambda = lambda;
		this.mu = mu;
		this.s = s;
		date_maximale = date_max;

		depart_max = 0;
		dernier_id = 0;
		s = new Stats(lambda, mu, date_maximale);
		arrivee_suivante = 0;
		file = new LinkedList<Evt>();
		file.add(new Evt(false, 0, -1, 0));
	}

	public boolean est_vide(){
		return arrivee_suivante > date_maximale && file.isEmpty();
	}

	public void demarrer_xp() {
		/* Récupération du prochain evenement à traiter et recup des info à afficher */
		double depart_suivant;
		while(!est_vide()){
			if(file.isEmpty())
				depart_suivant = Double.POSITIVE_INFINITY;
			else
			 	depart_suivant = file.getFirst().get_date_depart();

			if(arrivee_suivante < depart_suivant){
				double depart;
				if(file.isEmpty()){
					//s.incrementer_clients_sans_attente();
					depart = arrivee_suivante + Utile.loi_exp(mu);
				} else {
					depart = depart_max + Utile.loi_exp(lambda);
				}
				depart_max = depart;
				file.addLast(new Evt(true, arrivee_suivante, depart, ++dernier_id));
				arrivee_suivante += Utile.loi_exp(lambda);
				if(arrivee_suivante > date_maximale)
					arrivee_suivante = Double.POSITIVE_INFINITY;

			} else {
				Evt e = file.pop();
				//s.add_temps_sejour(e.get_date_depart() - e.get_date_arrivee());
			}
		}

		/*double depart;
		while (!file.isEmpty()){
			Evt premier = file.pop();
			if(premier.is_depart()){
				s.add_temps_sejour(premier.get_date_depart()-premier.get_date_arrivee());
			} else {
				if(file.isEmpty()){
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
		s.set_clients(dernier_id);*/
	}

	public void inserer_evt(Evt e){
		double date_e = Math.max(e.get_date_depart(), e.get_date_arrivee());
	/*	if(date_e > depart_max){
			file.addLast(e);
		}
		else{
			int i =0;*/
			int i =0;
			while(i<file.size() &&file.get(i).get_date_depart() < date_e)
				i++;
			file.add(i, e);
	//	}
	}

	public Stats get_resultats(){
		return s;
	}
}
