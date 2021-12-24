package barcode.subsystem;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class BarcodeSubsystemController {
	private static final String BARCODE_SERVER = "http://localhost:5000";
	private static final String BARCODE_URI = "/request_bike_id";

	public int convertBikeID(String barcode) {
		try {
			URL url = new URL(BARCODE_SERVER + BARCODE_URI + "?code=" + barcode + "");
			String response = BarcodeBoundary.query(url);
			JSONObject obj = new JSONObject (response.toString());
			return obj.getInt("bikeId");
		} catch (MalformedURLException e){
			System.out.print("URL exception");
			return -1;
		}
	}
}
