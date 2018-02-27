package by.htp.belavia.steps;

import java.util.Locale;

import org.openqa.selenium.WebDriver;

import by.htp.belavia.Services.Currency;
import by.htp.belavia.Services.FormatHumanDate;
import by.htp.belavia.constants.Constants;
import by.htp.belavia.driver.Driver;
import by.htp.belavia.entity.TicketPrice;
import by.htp.belavia.pages.BookingPage;

public class Steps {

	private WebDriver driver;
	private int numberOfIterations;

	public void initBrowser() {
		driver = Driver.startWebdriver();
	}

	public void closeDriver() {
		driver.quit();
	}

	public void initialBelavia() {

		numberOfIterations = numberOfWeeks(Constants.DATE_START_CALENDAR, Constants.DATE_STOP_CALENDAR);

		BookingPage bookingPage = new BookingPage(driver);
		bookingPage.openPage();
		bookingPage.enterData();
		waitTimeLoading(3000);

		bookingPage.calendarDisplay();
		waitTimeLoading(2000);

		if (numberOfIterations == 1) {
			bookingPage.getPrices();
		} else {
			for (int i = 0; i < numberOfIterations; i++) {
				bookingPage.getPrices();
				bookingPage.nextPeriodDays();
				waitTimeLoading(1000);
			}
		}
		bookingPage.leavePage();
		resultOut(bookingPage);
	}

	private void waitTimeLoading(int time) {
		try {
			Thread.sleep(time);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	private int numberOfWeeks(String startDate, String endDate) {
		String[] startDateArr = startDate.split("-");
		int startMonth = (int) Integer.parseInt(startDateArr[0]) / 7 + Integer.parseInt(startDateArr[1]) * 4
				+ Integer.parseInt(startDateArr[2]) * 4 * 12;
		String[] endDateArr = endDate.split("-");
		int endMonth = (int) Integer.parseInt(endDateArr[0]) / 7 + Integer.parseInt(endDateArr[1]) * 4
				+ Integer.parseInt(endDateArr[2]) * 4 * 12;
		int result = endMonth - startMonth;
		if (result <= 0) {
			return 1;
		} else
			return result;
	}

	private void resultOut(BookingPage page) {

		FormatHumanDate formatHumanDate = new FormatHumanDate();

		TicketPrice result = page.findMinimumPrice();		
		

		System.out.println("The following ticket is found: " + "\n                                 ↗ "
				+ Constants.DEPARTURE_FROM_NAME + "  ↘ " + Constants.DEPARTURE_TO_NAME);

		System.out.println("-------------------------------------------------------------------");

		String[] datesDeparture = result.getDate().split(":");

		System.out.println("Date of departure: ");
		System.out.println("                                Departure to there (✈): "
				+ formatHumanDate.formHumanDate(datesDeparture[0]));
		System.out.println("                                    Departure back (✈): "
				+ formatHumanDate.formHumanDate(datesDeparture[1]));
		System.out.println("-------------------------------------------------------------------");

		System.out.println("   Minimum ticket price: " + result.getPrice() + " BYN");

		Currency currency = new Currency();
		String curEUR = currency.currencyValue("EUR");

		System.out.println("   The cost of tickets in other currencies for today.");

		System.out.println("-------------------------------------------------------------------");

		System.out.println("| The ticket price at the rate of the National Bank (EUR): "
				+ String.format(new Locale("ru"), "%(.2f", (Double.valueOf(result.getPrice()) / Double.valueOf(curEUR)))
				+ "€ |");

		String curUSD = currency.currencyValue("USD");
		System.out.println("| The ticket price at the rate of the National Bank (USD): "
				+ String.format(new Locale("ru"), "%(.2f", (Double.valueOf(result.getPrice()) / Double.valueOf(curUSD)))
				+ "$ |");

		System.out.println("-------------------------------------------------------------------");

	}

}
