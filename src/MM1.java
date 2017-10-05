
public class MM1 {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		double lambda = 5;
		double mu=6;
		double date_max=10000000;
		Ech e = new Ech(lambda, mu, date_max);
		while (!e.est_vide()){
			e.traiter_premier_event();
		}
		System.out.println("Ended");

	}

}
