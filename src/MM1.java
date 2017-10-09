/**
	Classe principale
	Parse les arguments et lance l'expérience
	Affiche les statistiques
*/
public class MM1 {

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
			 	verbose = Integer.parseInt(args[3]);
		}

		/* Verbose à 42 utilisée uniquement pour générer des logs
			lisibles facilement via script */
		if(verbose != 42)
			System.out.println("Launched");

		/* Lancement de la simultion */
		Ech e = new Ech(lambda, mu, date_max);
		e.demarrer_xp(verbose);

		if(verbose != 42)
			System.out.println("Ended");

		/* Affichages des statistiques */
		Stats resultats_xp = e.get_resultats();
		resultats_xp.print_theoriques(verbose);
		resultats_xp.print_simulation(verbose);
	}
}
