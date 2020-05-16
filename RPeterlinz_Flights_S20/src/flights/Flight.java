package flights;

public class Flight {
	private String airline;
	private String from;
	private String to;
	private String price;

	public Flight(String airline, String from, String to, String price) {
		super();
		this.airline = airline;
		this.from = from;
		this.to = to;
		this.price = price;
	}


	public String getAirline() {
		return airline;
	}


	public String getFrom() {
		return from;
	}


	public String getTo() {
		return to;
	}


	public String getPrice() {
		return price;
	}
}
