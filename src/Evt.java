
public class Evt {
	private double date_arrivee;
	private double date_depart;
	private int id_client;
	public boolean depart;

	public Evt(boolean dep, double dat_a, double date_d, int id) {
		depart = dep;
		date_arrivee = dat_a;
		date_depart = date_d;
		id_client = id;
	}

	public boolean is_depart() {
		return depart;
	}

	public double get_date_arrivee() {
		return date_arrivee;
	}

	public double get_date_depart() {
		return date_depart;
	}

	public int get_id_client() {
		return id_client;
	}

	public void set_depart(boolean d) {
		depart = d;
	}

	public void set_date_arrivee(double d) {
		date_arrivee = d;
	}

	public void set_date_depart(double d) {
		date_depart = d;
	}

	public void set_id_client(int id) {
		id_client = id;
	}

	public String toString() {
		if (is_depart())
			return "Date=" + date_depart + " Depart " + "client #" + id_client;
		else
			return "Date=" + date_arrivee + " Arrivee " + "client #" + id_client;
	}
}
