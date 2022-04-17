import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;

interface Accumulate {
   int accumulateValues(Ticket[] data );
}

class Ticket implements Comparable{
    private String departure;
    private String destination;
    private double price;
    private int distance;

    String getDep() {
	return departure;
	}
    String getDest() {
	return destination;
	}
    double getPrice() {
	return price;
	}
     Ticket (){
	departure = "N/A";
	destination = "N/A";
	price = 0.0;
	distance = 0;
	}
     Ticket (String dep,String dest,double pr, int dstn){
	departure = dep;
	destination = dest;
	price = pr;
	distance = dstn;
	}
      public String toString() {
	return "Departure City: "+departure+" Destination City: "+destination+" Ticket Price: "+price+" Distance: "+distance+" km";
	}
       public boolean  equals(Ticket tck) {
	if ((departure.equals(tck.departure))&&destination.equals(tck.destination)) {
		return true;
		}
		else return false;
	}
	@Override
	public int compareTo(Object cty) {
		if (departure.equals(((Ticket)cty).departure)) {
			return 0;
		}
		else return -1;
	}
}
public class Station implements Comparable,Accumulate{
	private String station_name;
	private Ticket[] sold_tickets = new Ticket[11];
	String get_stationName() {
		return station_name;
	}
	void set_stationName(String new_name) {
		station_name = new_name;
	}
	public Station(String filename) {
        try {
            RandomAccessFile myFile = new RandomAccessFile(filename, "r");
            boolean station_first = false;
            String strLine;
            String[] res;
            int ind = 0;
            int ar_size = 11;
            while ((strLine = myFile.readLine()) != null) {
            	if (!station_first) {
            		res = strLine.split(" ");
            		station_name = res[0];
            		station_first = true;
            	}
            	else {
            		res = strLine.split(" ");
                    Ticket oTick = new Ticket(
                            (res[0]),
                            (res[1]),
                            Double.valueOf(res[2]).doubleValue(),
                            Integer.valueOf(res[3]).intValue()
                    );
                    sold_tickets[ind++] = oTick;
                    ar_size--;
            	}
            }
            myFile.close();
            while (ar_size != 0) {
            	Ticket oTick = new Ticket();
            	sold_tickets[ind++] = oTick;
            	ar_size--;
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
	}
	private double avgPrice(String start,String end) {
		double total = 0.0;
		int incr = 0;
		for (Ticket curr : sold_tickets) {
			if (curr.getDep().equals(start)&&curr.getDest().equals(end)) {
				total += curr.getPrice();
				incr++;
			}
		}
		return total/incr;
	}
	public void toSting() {
    	System.out.println("\nStation Name: \n"+station_name+"\n");
    	for (Ticket curr : sold_tickets) {
    		System.out.println(curr.toString());
    	}
	}
	@Override
	public int accumulateValues(Ticket[] data) {
		int total = 0;
		for (Ticket curr : data) {
				total += curr.getPrice();
		}
		return total;
	}
	@Override
	public int compareTo(Object o) {
		if (this.accumulateValues(this.sold_tickets) < ((Station)o).accumulateValues(((Station)o).sold_tickets)) {
			return -1;
		}
		else if (this.accumulateValues(this.sold_tickets) > ((Station)o).accumulateValues(((Station)o).sold_tickets)){
			return 1;
		}
		else return 0;
	}
	public static void main(String[] args) {
		Station test = new Station("my_stations.txt");
		Station test2 = new Station("my_stations2.txt");
		test.toSting();
		test2.toSting();
		System.out.println("First station total sum: "+test.accumulateValues(test.sold_tickets));
		System.out.println("Second station total sum: "+test2.accumulateValues(test2.sold_tickets));
		System.out.println("Avg Price from Varna to C for station 1 "+test.avgPrice("Varna", "C"));
		System.out.println("Avg Price from Varna to C for station 2 "+test2.avgPrice("Varna", "C"));
		System.out.println(test.compareTo(test2));
	}
}