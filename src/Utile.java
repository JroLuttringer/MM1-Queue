import java.util.Random;

public class Utile {

	public static double nb_alea() {
		Random r = new Random(123456789);
		return r.nextDouble();

	}

	public static double loi_exp(double lambda) {
		return -Math.log(1 - nb_alea()) / lambda;
	}

}
