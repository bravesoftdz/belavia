package by.htp.belavia.entity;

public class TicketPrice implements Comparable<TicketPrice>{

	private int ticketPrice;
	private String ticketDate;
	
	public TicketPrice(int price, String date) {
		super();
		this.ticketPrice = price;
		this.ticketDate = date;
	}
	
	public int getPrice() {
		return ticketPrice;
	}
	
	public String getDate() {
		return ticketDate;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((ticketDate == null) ? 0 : ticketDate.hashCode());
		result = prime * result + ticketPrice;
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		TicketPrice other = (TicketPrice) obj;
		if (ticketDate == null) {
			if (other.ticketDate != null)
				return false;
		} else if (!ticketDate.equals(other.ticketDate))
			return false;
		if (ticketPrice != other.ticketPrice)
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "TicketValue [price=" + ticketPrice + ", date=" + ticketDate + "]";
	}	
	
	@Override
	public int compareTo(TicketPrice o) {
		return Integer.compare(ticketPrice, o.ticketPrice);		
	}
}
