
public class Stats {

	private double lambda;
	private double mu;
	private double duree;

	private double clients_total = 0;
	private double clients_sans_attente = 0;
	private double client_cumul = 0;
	private double tps_sejour_total = 0;

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
		double esp_client = ro / (1-ro);
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
		double tps_sej = tps_sejour_total/clients_total;

		System.out.println("==========\nRESULTATS SIMULATION\n===========");
		System.out.println("Total client : " + clients_total);
		System.out.println("Client sans attente : " + clients_sans_attente);
		System.out.println("Client avec attente : " + client_attente);
		System.out.println("Proportion client sans attente : " + prop_ss_attente);
		System.out.println("Proportion client avec attente : " + prop_ac_attente);
		System.out.println("Debit : " + debit);
		System.out.println("Nombre moyen de client :" + debit*tps_sej);
		System.out.println("Temps sejour moyen : " + tps_sej);

	}

	public void incrementer_clients(){
		clients_total++;
	}

	public void incrementer_clients_sans_attente(){
		clients_sans_attente++;
	}

	public void add_client_cumul(double x){
		client_cumul += x;
	}

	public void add_temps_sejour(double x){
		tps_sejour_total += x;
	}

}
