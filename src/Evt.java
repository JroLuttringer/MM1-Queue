
public class Evt {
	private boolean depart;
	private double date;
	private int id_client;
	
	public Evt(boolean dep, double dat, int id){
		depart = dep;
		date = dat;
		id_client = id;
	}
	
	public boolean is_depart(){
		return depart;
	}
	
	public double get_date(){
		return date;
	}
	
	public int get_id_client(){
		return id_client;
	}
	
	public void set_depart(boolean d){
		depart = d;
	}
	
	public void set_date(double d){
		date = d;
	}
	
	public void set_id_client(int id){
		id_client = id ;
	}
	
	public String toString(){
		if(is_depart())
			return "Date="+date+" Depart "+"client #"+id_client;
		else
			return "Date="+date+" Arrivee "+"client #"+id_client;
	}
}
