package by.htp.belavia;

import by.htp.belavia.steps.Steps;

public class Main {

	public static void main(String[] args) {
		Steps steps = new Steps();
		
		steps.initBrowser();
		steps.initialBelavia();
	}

}

/*
The following ticket is found: 
                             ↗ Минск (MSQ), BY  ↘ Москва (MOW), RU
-------------------------------------------------------------------
Date of departure: 
                                Departure to there (✈): 01.03.2018
                                    Departure back (✈): 28.05.2018
-------------------------------------------------------------------
Minimum ticket price: 142 BYN
The cost of tickets in other currencies for today.
-------------------------------------------------------------------
| The ticket price at the rate of the National Bank (EUR): 72,47€ |
| The ticket price at the rate of the National Bank (USD): 58,96$ |
-------------------------------------------------------------------
*/