/** Classe Utile
	Contient la fonction permettant de générer un nombre aléatoire
	suivant la loi exponentielle
*/

public class Utile {
	/**
		Génére le nombre aléatoire
		@param lambda paramètre de la loi
	*/
	public static double loi_exp(double lambda) {
		return ( (-Math.log(1 - Math.random()) ) / lambda);
	}

}
