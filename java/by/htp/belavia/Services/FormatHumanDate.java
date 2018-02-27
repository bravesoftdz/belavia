package by.htp.belavia.Services;

public class FormatHumanDate {

	public String formHumanDate(String value) {

		String[] datesDeparture = value.split("-");
		String year = "20" + datesDeparture[0];
		String month = datesDeparture[1];
		String day = datesDeparture[2];
		String newDate = day + "." + month + "." + year;
		return newDate;
	}

	public String formHumanDepartureNormal(String value) {
		String[] datesDeparture = value.split("-");
		String year = datesDeparture[0];
		String month = datesDeparture[1];
		String day = datesDeparture[2];
		String newDate = day + "." + month + "." + year;
		return newDate;
	}

}
