package by.htp.belavia.Services;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import by.htp.belavia.constants.Constants;

public class Currency {

	public String currencyValue(String cur) {
		HttpURLConnection urlConnection = null;
		BufferedReader reader = null;
		String resultJson = "";

		try {
			URL url = null;
			
			switch (cur) {
			case "EUR":
				url = new URL(Constants.CURRENCY_EUR_TODAY_URL);
				break;
			case "USD":
				url = new URL(Constants.CURRENCY_USD_TODAY_URL);
				break;
			}		
			
			urlConnection = (HttpURLConnection) url.openConnection();
			urlConnection.setRequestMethod("GET");
			urlConnection.connect();
			InputStream inputStream = urlConnection.getInputStream();
			StringBuffer buffer = new StringBuffer();
			reader = new BufferedReader(new InputStreamReader(inputStream));
			String line;
			while ((line = reader.readLine()) != null) {
				buffer.append(line);
			}
			resultJson = buffer.toString();
		} catch (Exception e) {
			e.printStackTrace();
		}
		JsonElement jelement = new JsonParser().parse(resultJson);
		JsonObject jobject = jelement.getAsJsonObject();
		String result = jobject.get("Cur_OfficialRate").getAsString();
		return result;
	}

}
