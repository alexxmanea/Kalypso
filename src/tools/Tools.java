package tools;

import entities.Balloon;
import org.json.JSONObject;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;

public class Tools {
	public static final double ROMANIA_LAT = 45.944285;
	public static final double ROMANIA_LONG = 25.009430;
	public static final int ROMANIA_RADIUS = 250000;
	public static final int COORDINATES_DECIMALS = 6;

	public static int randomInt(int min, int max) {
		return new Random().nextInt(max - min) + min;
	}

	public static double randomDouble(double min, double max) {
		Random random = new Random();
		return min + (max - min) * random.nextDouble();
	}

	public static double round(double value, int places) {
		if (places < 0) {
			throw new IllegalArgumentException();
		}

		BigDecimal bd = BigDecimal.valueOf(value);
		bd = bd.setScale(places, RoundingMode.HALF_UP);
		return bd.doubleValue();
	}

	public static String generateUniqueID(String prefix) {
		return prefix + "-" + UUID.randomUUID().toString();
	}

	public static LocalDate randomDate() {
		long minDay = LocalDate.of(2010, 1, 1).toEpochDay();
		long maxDay = LocalDate.now().toEpochDay();
		long randomDay = ThreadLocalRandom.current().nextLong(minDay, maxDay);
		return LocalDate.ofEpochDay(randomDay);
	}

	public static Pair<Double, Double> randomLocationAroundPoint(double y0, double x0, int radius) {
		Random random = new Random();

		// Convert radius from meters to degrees
		double radiusInDegrees = radius / 111000f;

		double u = random.nextDouble();
		double v = random.nextDouble();
		double w = radiusInDegrees * Math.sqrt(u);
		double t = 2 * Math.PI * v;
		double x = w * Math.cos(t);
		double y = w * Math.sin(t);

		// Adjust the x-coordinate for the shrinking of the east-west distances
		double new_x = x / Math.cos(y0);

		double foundLongitude = round(new_x + x0, COORDINATES_DECIMALS);
		double foundLatitude = round(y + y0, COORDINATES_DECIMALS);

		return new Pair<>(foundLatitude, foundLongitude);
	}

//	public static void sendHttpRequest(JSONObject json) {
//		try {
//			// URL and parameters for the connection, This particulary returns the information passed
//			URL url = new URL("https://my-json-server.typicode.com/alexxmanea/thingworx/db");
//			HttpURLConnection httpConnection = (HttpURLConnection) url.openConnection();
//			httpConnection.setDoOutput(true);
//			httpConnection.setRequestMethod("POST");
//			httpConnection.setRequestProperty("Content-Type", "application/json");
//			httpConnection.setRequestProperty("Accept", "application/json");
//			// Not required
//			// urlConnection.setRequestProperty("Content-Length", String.valueOf(input.getBytes().length));
//
//			// Writes the JSON parsed as string to the connection
//			DataOutputStream wr = new DataOutputStream(httpConnection.getOutputStream());
//			wr.write(json.toString().getBytes());
//			int responseCode = httpConnection.getResponseCode();
//
//			BufferedReader bufferedReader;
//
//			// Creates a reader buffer
//			if (responseCode > 199 && responseCode < 300) {
//				bufferedReader = new BufferedReader(new InputStreamReader(httpConnection.getInputStream()));
//			} else {
//				bufferedReader = new BufferedReader(new InputStreamReader(httpConnection.getErrorStream()));
//			}
//
//			// To receive the response
//			StringBuilder content = new StringBuilder();
//			String line;
//			while ((line = bufferedReader.readLine()) != null) {
//				content.append(line).append("\n");
//			}
//			bufferedReader.close();
//
//			// Prints the response
//			System.out.println(content.toString());
//
//		} catch (Exception e) {
//			System.out.println("Error Message");
//			System.out.println(e.getClass().getSimpleName());
//			System.out.println(e.getMessage());
//		}
//	}

	public static List<JSONObject> createBalloonJSONS() {
//		Balloon[] balloons = new Balloon[Balloon.BALLOONS_NUMBER];
//		balloons[0] = new Balloon("HAB001", "0010", "13", "March", "2015", 41.40338D, 2.17403D);
//		balloons[1] = new Balloon("HAB002", "0010", "7", "July", "2018", 57.23575D, 3.12312D);
//		balloons[2] = new Balloon("HAB003", "0011", "24", "September", "2019", 23.57465D, 7.56756D);
//		balloons[3] = new Balloon("HAB004", "0011", "18", "January", "2017", 61.23423D, 2.98798D);
//		balloons[4] = new Balloon("HAB005", "0100", "9", "November", "2014", 64.42345D, 10.31212D);

		Balloon[] balloons = new Balloon[Balloon.BALLOONS_NUMBER];
		for (int i = 0; i < Balloon.BALLOONS_NUMBER; ++i) {
			balloons[i] = new Balloon(i + 1);
		}

		List<JSONObject> jsons = new ArrayList<>();
		int i = -1;
		for (Balloon balloon : balloons) {
			jsons.add(balloon.getJson());
			System.out.println(jsons.get(++i));
		}

		return jsons;
	}
}
