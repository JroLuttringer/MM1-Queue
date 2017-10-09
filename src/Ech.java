import java.util.LinkedList;

public class Ech {

	private LinkedList<Evt> file; // client en attente
	private double lambda;
	private double mu;
	private double date_maximale; // borne temporelle de la simulation
	private double depart_max;	// départ le plus tardif calculé
	private int dernier_id;	// compteur de client
	private double arrivee_suivante; // date de la prochaine arrivee
	private Stats s; // Pour calcul des statistiques
	private double ancienne_date = 0;
	private double number_client = 0;

	public Ech(double lambda, double mu, double date_max) {
		this.lambda = lambda;
		this.mu = mu;
		this.s = s;
		date_maximale = date_max;

		dernier_id = 0;
		s = new Stats(lambda, mu, date_maximale);
		arrivee_suivante = 0; // init de l'echéancier avec une arrivee à t=0
		depart_max = 1;
		file = new LinkedList<Evt>();
		//file.add(new Evt(false, 0, -1, 0)); Pour ancienne implémentation
	}

	public boolean est_vide(){
		return arrivee_suivante > date_maximale && file.isEmpty();
	}

	public void traiter_arrivee(double date, int verbose){
		if(verbose==1){
			System.out.format("Date= %f Arrive client #%d \n",
				arrivee_suivante, dernier_id);
		}

		double depart;
		if(file.isEmpty()) { // file vide -> le client n'attend pas
			s.incrementer_clients_sans_attente();
			depart = arrivee_suivante + Utile.loi_exp(mu);
		} else {
			depart = depart_max + Utile.loi_exp(mu);
		}

		// FIFO -> prochain départ en fin de file
		file.addLast(new Evt(true, arrivee_suivante, depart, ++dernier_id));
		arrivee_suivante += Utile.loi_exp(lambda);

		// Si prochaine arrivee trop lointaine, on ne traite plus que les départs
		if(arrivee_suivante > date_maximale)
			arrivee_suivante = Double.POSITIVE_INFINITY;

		s.incrementer_client_moyen(number_client*(date-ancienne_date));
		ancienne_date = date;
		number_client++;
		depart_max = depart;
	}


	public void traiter_depart(int verbose){
		// Mise à jour des statistiques
		Evt e = file.pop();
		s.add_temps_sejour(e.get_date_depart() - e.get_date_arrivee());

		if(verbose == 1){
			System.out.format("Date= %f Depart client #%d arrive à t=%f\n",
		 		e.get_date_depart(),e.get_id_client(),e.get_date_arrivee());
		}
		s.incrementer_client_moyen(number_client*(e.get_date_depart()-ancienne_date));
		ancienne_date = e.get_date_depart();
		number_client--;
	}

	public void demarrer_xp(int verbose) {
		double depart_suivant;

		while(!est_vide()){
			if(file.isEmpty())	// file vide -> pas de départ à traiter
				depart_suivant = Double.POSITIVE_INFINITY;
			else	// Sinon -> depart suivant = premier départ de la file car FIFO
			 	depart_suivant = file.getFirst().get_date_depart();

			// Traitement de l'evenement suivant
			if(arrivee_suivante < depart_suivant)
				traiter_arrivee(arrivee_suivante, verbose);
			else
				traiter_depart(verbose);

			s.set_clients(dernier_id); // Mise à jour nombre de clients total
		} // fin while !vide
	}

	public Stats get_resultats(){
		return s;
	}




// ===================== ANCIENNE VERSION  ====================//
 	public void old(){
		double depart;
		while (!file.isEmpty()){
			Evt premier = file.pop();
			System.out.println(premier);
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
		s.set_clients(dernier_id);
	}


	public void inserer_evt(Evt e){
		double date_e = Math.max(e.get_date_depart(), e.get_date_arrivee());
		if(date_e > depart_max){
			file.addLast(e);
		}
		else{
			int i =0;
			while(i<file.size() && file.get(i).get_date_depart() < date_e)
				i++;
			file.add(i, e);
		}
	}


}
