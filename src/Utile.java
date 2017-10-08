import java.util.Random;

public class Utile {
	//static Random r = new Random();
	/*public static double nb_alea() {
		double alea = r.nextDouble();
		return alea;
	}*/

	public static double loi_exp(double lambda) {
		return ((-Math.log(1 - Math.random())) / lambda);
	}

}
