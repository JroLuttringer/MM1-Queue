/**
	Classe Stats
	Stocke les valeurs nécessaires au calcul de statistiques post-exécution
	et contient les fonctions permettant d'effectuer ces calculs
	*/
public class Stats {

	private double lambda;
	private double mu;
	private double duree;

	private double clients_total = 0;
	private double clients_sans_attente = 0;
	private double tps_sejour_total = 0;
	private double client_moyen_sys = 0; // pour calcul du nombre moyen de clients dans le systeme
	private double duree_reelle;

	/** Constructeur stats
	@param lambda Paramètre lambda de la file
	@param mu Paramètre mu de la file
	@param duree Duree de l'expérience
	*/
	public Stats(double lambda, double mu, double duree){
		this.lambda = lambda;
		this.mu = mu;
		this.duree = duree;
	}

	/**
		Calcul et affiche les résultats théoriques de l'expérience
		@param debug Permet de décider du format de l'output (pour rapport)
	*/
	public void print_theoriques(int debug){
		double ro = lambda/mu;
		double nombre_clients = lambda * duree;
		double prob_ss_attente = 1 - ro;
		double prob_occupee = ro;
		double esp_client = ro / (1-ro);
		double temps_sej =  1 / (mu*(1-ro));
		if(debug == 42){
			System.out.print(lambda+","+mu+","+duree+","+ro+","+nombre_clients+","+prob_ss_attente+","+ro
				+","+lambda+","+esp_client+","+temps_sej);
			return ;
		}

		System.out.println("==========\nRESULTATS THEORIQUES\n===========");
		if(lambda < mu)
			System.out.println("File stable");
		else
			System.out.println("File non stable");
		System.out.println("Ro : " +  ro);
		System.out.println("Nombre de clients :" +  nombre_clients);
		System.out.println("Probabilité de service sans attente : " + prob_ss_attente);
		System.out.println("Probabilité file occupée " + ro);
		System.out.println("Debit : "+ lambda);
		System.out.println("Esp nb client : " + esp_client);
		System.out.println("Temps séjour moyen " + temps_sej);
		System.out.println("\n");
	}


	/**
		Calcul et affiche les résultats de la simulation 
		@param debug Permet de décider du format de l'output (pour rapport)
	*/
	public void print_simulation(int debug){

		double client_attente = clients_total-clients_sans_attente;
		double prop_ss_attente = clients_sans_attente/clients_total;
		double prop_ac_attente = 1-prop_ss_attente;
		double debit = clients_total/duree_reelle;
		double tps_sej = tps_sejour_total/clients_total;
		if(debug == 42){
			System.out.println(","+clients_total+","+prop_ss_attente+","+prop_ac_attente+","+debit+","+(debit*tps_sej)+","+tps_sej);
			return ;
		}
		System.out.println("==========\nRESULTATS SIMULATION\n===========");
		System.out.println("Total client : " + clients_total);
		System.out.println("Client sans attente : " + clients_sans_attente);
		System.out.println("Client avec attente : " + client_attente);
		System.out.println("Proportion client sans attente : " + prop_ss_attente);
		System.out.println("Proportion client avec attente : " + prop_ac_attente);
		System.out.println("Debit : " + debit);
		System.out.println("Nombre moyen de client (debit*temps de séjour) : " + debit*tps_sej);
		System.out.println("Calcul iteratif du nombre moyen de client : " + (client_moyen_sys/duree_reelle));
		System.out.println("Temps sejour moyen : " + tps_sej);

	}

	public void set_clients(double c){
		clients_total = c;
	}

	public void incrementer_clients_sans_attente(){
		clients_sans_attente++;
	}

	public void incrementer_client_moyen(double x){
		client_moyen_sys+=x;
	}

	public void add_temps_sejour(double x){
		tps_sejour_total += x;
	}

	public void set_duree_reelle(double x){
		duree_reelle = x;
	}

}
