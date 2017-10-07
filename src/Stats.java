
public class Stats {

	private double lambda;
	private double mu;
	private double duree;

	private int clients_total = 0;
	private int clients_sans_attente = 0;
	private double temps_attente_total = 0;

	public Stats(double lambda, double mu, double duree){
		this.lambda = lambda;
		this.mu = mu;
		this.duree = duree;
	}

	public void print_theoriques(){
		double ro = lambda/mu;
		double nombre_clients = lambda * duree;
		double prob_ss_attente = 1 - ro;
		double prob_occupee = ro;
		double esp_client = ro / 1-ro;
		double temps_sej =  1 / (mu*(1-ro));


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

	public void print_simulation(){
		double client_attente = clients_total-clients_sans_attente;
		double prop_ss_attente = clients_sans_attente/clients_total;
		double prop_ac_attente = 1-prop_ss_attente;
		double debit = clients_total/duree;

		System.out.println("==========\nRESULTATS SIMULATION\n===========");
		System.out.println("Total client : " + clients_total);
		System.out.println("Client sans attente : " + clients_sans_attente);
		System.out.println("Client avec attente : " + client_attente);
		System.out.println("Proportion client sans attente : " + prop_ss_attente);
		System.out.println("Proportion client avec attente : " + prop_ac_attente);
		System.out.println("Debit : " + debit);
	}

	public void incrementer_clients(){
		clients_total++;
	}

	public void incrementer_clients_sans_attente(){
		clients_sans_attente++;
	}

	public void add_temps_attente(double att){
		temps_attente_total += att;
	}


}
