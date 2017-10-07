
public class MM1 {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		double lambda = 0;
		double mu = 0;
		double date_max = 0;
		int verbose = 0;

		/* Recuperation des arguments */
		if(args.length != 4 && args.length != 3){
			System.out.println("Usage :  java MM1 lambda mu duree [debug]");
			return ;
		} else {
			lambda = Double.parseDouble(args[0]);
			mu = Double.parseDouble(args[1]);
			date_max = Double.parseDouble(args[2]);
			if(args.length > 3)
			 	verbose = Math.min(1,Integer.parseInt(args[3])); // > 1 = verbose active
		}

		/* Lancement de la simultion */
		Ech e = new Ech(lambda, mu, date_max);
		String traite ; // contiendra l'event qui a été traité à l'itération actuelle
		while (!e.est_vide()) {
			e.traiter_premier_event();
			//if(verbose == 1)
				//System.out.println(traite);
		}
		System.out.println("Ended");

		/* Affichages des statistiques */
		Stats resultats_xp = e.get_resultats();
		resultats_xp.print_theoriques();
		resultats_xp.print_simulation();
	}
}
