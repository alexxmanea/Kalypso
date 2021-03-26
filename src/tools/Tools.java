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

	public static double roundDouble(double value, int places) {
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

		double foundLongitude = roundDouble(new_x + x0, COORDINATES_DECIMALS);
		double foundLatitude = roundDouble(y + y0, COORDINATES_DECIMALS);

		return new Pair<>(foundLatitude, foundLongitude);
	}

	public static List<JSONObject> createBalloonJSONS() {
		Balloon[] balloons = new Balloon[Balloon.BALLOONS_NUMBER];
		for (int i = 0; i < Balloon.BALLOONS_NUMBER; ++i) {
			balloons[i] = new Balloon(i + 1);
		}

		List<JSONObject> jsons = new ArrayList<>();
		int i = -1;
		for (Balloon balloon : balloons) {
			jsons.add(balloon.getJson());
		}

		return jsons;
	}
}
