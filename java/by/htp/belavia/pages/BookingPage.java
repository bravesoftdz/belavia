package by.htp.belavia.pages;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import by.htp.belavia.constants.Constants;
import by.htp.belavia.driver.Driver;
import by.htp.belavia.entity.TicketPrice;

public class BookingPage {
	
	private WebDriver driver;
	private List<WebElement> PriceList;
	private List<WebElement> DateList;
	private List<TicketPrice> values = new ArrayList<>();
	private String departureFrom;
	private String departureTo;
	private JavascriptExecutor executorJavaScript;
	private String javascript;

	public String getDepartureFrom() {
		return departureFrom;
	}

	public void setDepartureFrom(String departureFrom) {
		this.departureFrom = departureFrom;
	}

	public String getDepartureTo() {
		return departureTo;
	}

	public void setDepartureTo(String departureTo) {
		this.departureTo = departureTo;
	}

	@FindBy(id = "OriginLocation")
	private WebElement javaScriptFromCity;

	@FindBy(xpath = "//*[@id='OriginLocation_Combobox']")
	private WebElement fromCity;

	@FindBy(id = "DestinationLocation")
	private WebElement javaScriptToCity;

	@FindBy(xpath = "//*[@id='DestinationLocation_Combobox']")
	private WebElement toCity;

	@FindBy(xpath = "//*[@id='step-2']/div[1]/div/label[2]")
	private WebElement radioChk2Directions;

	@FindBy(id = "DepartureDate")
	private WebElement javaScriptDepartureDate;

	@FindBy(id = "DepartureDate_Datepicker")
	private WebElement departureDate;

	@FindBy(id = "ReturnDate")
	private WebElement javaScriptDepartureReturnDate;

	@FindBy(id = "ReturnDate_Datepicker")
	private WebElement departureReturnDate;

	@FindBy(xpath = "//*[@id='step-2']/div[4]/div/button")
	private WebElement btnFind;

	@FindBy(css = "#outbound > div.hdr > div > div.col-mb-5.text-right > a")
	private WebElement calendarGrid;

	@FindBy(xpath = "//*[@id='matrix']/div[1]/div[1]/div[2]/a")
	private WebElement NextPeriodDate;

	public BookingPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(this.driver, this);
	}

	public void openPage() {
		driver.navigate().to(Constants.BASE_URL);
	}

	public void enterData() {
		
		

		executorJavaScript = (JavascriptExecutor) driver;

		javascript = "var s= document.getElementById('OriginLocation');s.type = 'visible'";
		executorJavaScript.executeScript(javascript, this.javaScriptFromCity);
		this.javaScriptFromCity.sendKeys(Constants.DEPARTURE_FROM_CODE_CITY);
		this.fromCity.sendKeys(Constants.DEPARTURE_FROM_NAME);

		javascript = "var s= document.getElementById('DestinationLocation');s.type = 'visible'";
		executorJavaScript.executeScript(javascript, this.javaScriptToCity);
		this.javaScriptToCity.sendKeys(Constants.DEPARTURE_TO_CODE_CITY);
		this.toCity.sendKeys(Constants.DEPARTURE_TO_NAME);

		radioChk2Directions.click();

		javascript = "var DepartureDate = document.getElementById('DepartureDate');" + "DepartureDate.type = 'visible'";
		executorJavaScript.executeScript(javascript, this.javaScriptDepartureDate);

		javascript = "var DepartureDate= document.getElementById('DepartureDate');" + "DepartureDate.value = '"
				+ Constants.DATE_DEPARTURE_JS + "'";
		executorJavaScript.executeScript(javascript, this.javaScriptDepartureDate);

		javascript = "var DepartureDate_Datepicker = document.getElementById('DepartureDate_Datepicker');"
				+ "DepartureDate_Datepicker.value = '" + Constants.DATE_DEPARTURE_CALENDAR + "'";
		executorJavaScript.executeScript(javascript, this.departureDate);

		javascript = "var ReturnDate = document.getElementById('ReturnDate');" + "ReturnDate.type = 'visible'";
		executorJavaScript.executeScript(javascript, this.javaScriptDepartureReturnDate);

		javascript = "var ReturnDate = document.getElementById('ReturnDate');" + "ReturnDate.value = '"
				+ Constants.DATE_DEPARTURE_RERUN_JS + "'";
		executorJavaScript.executeScript(javascript, this.javaScriptDepartureReturnDate);

		javascript = "var ReturnDate_Datepicker= document.getElementById('ReturnDate_Datepicker');"
				+ "ReturnDate_Datepicker.value = '" + Constants.DATE_DEPARTURE_RETURN_CALENDAR + "'";
		executorJavaScript.executeScript(javascript, this.departureReturnDate);

		btnFind.click();
	}

	public void calendarDisplay() {
		if (calendarGrid.isDisplayed()) {
			calendarGrid.click();
		}
	}

	public void getPrices() {
		PriceList = driver.findElements(By.className("price"));
		DateList = driver.findElements(By.name("date"));
		int price;
		TicketPrice ticketPrice;
		String date = "";
		Iterator<WebElement> dates = DateList.iterator();

		for (WebElement WEvalue : PriceList) {
			if (WEvalue.getText().toUpperCase().endsWith(Constants.CURRENCY_BYN)) {
				price = Integer.parseInt(WEvalue.getText().substring(0, 3));
				if (dates.hasNext()) {
					date = dates.next().getAttribute("value");
				}
				ticketPrice = new TicketPrice(price, date);
				values.add(ticketPrice);
			}
		}
	}

	public void nextPeriodDays() {
		NextPeriodDate.click();
	}

	public TicketPrice findMinimumPrice() {
		return Collections.min(values);
	}

	public void leavePage() {
		Driver.stopWebDriver();
	}

}
