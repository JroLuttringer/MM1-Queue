
public class MM1 {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		double lambda ;
		double mu ;
		double date_max ;
		int verbose = 0;

		/* Recuperation des arguments */
		if(args.length != 4 && args.length != 3){
			System.out.println("Usage :  java MM1 lambda mu duree [debug]");
			return ;
		} else {
			lambda = Double.parseDouble(args[0]);
			mu =Double.parseDouble(args[1]);
			date_max =Double.parseDouble(args[2]);
			if(args.length > 3)
			 	verbose = Math.min(1,Integer.parseInt(args[3])); // > 1 = verbose active
		}

		/* Lancement de la simultion */
		Ech e = new Ech(lambda, mu, date_max);
		e.demarrer_xp();
		System.out.println("Ended");

		/* Affichages des statistiques */
		Stats resultats_xp = e.get_resultats();
		resultats_xp.print_theoriques();
		resultats_xp.print_simulation();
	}
}
