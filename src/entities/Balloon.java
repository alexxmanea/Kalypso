package entities;

import org.json.JSONObject;
import tools.Pair;
import tools.Tools;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Balloon {
	public static final int BALLOONS_NUMBER = 5;
	public static final String NAME_PREFIX = "HAB";
	private static final int MAX_SPEED = 150;
	private static final int MAX_WIND_SPEED = 150;
	private static final int ALTITUDE = 10000;
	private String name;
	private String serialNumber;
	private String launchDay;
	private String launchMonth;
	private String launchYear;
	private Double launchLat;
	private Double launchLong;

	// ------------------------------------------------ Constructors -------------------------------------------------
	public Balloon(String name, String serialNumber, String launchDay, String launchMonth, String launchYear,
	               Double launchLat, Double launchLong) {
		this.name = name;
		this.serialNumber = serialNumber;
		this.launchDay = launchDay;
		this.launchMonth = launchMonth;
		this.launchYear = launchYear;
		this.launchLat = launchLat;
		this.launchLong = launchLong;
	}

	public Balloon(int nameIndex) {
		this.name = NAME_PREFIX + String.format("%03d", nameIndex);
		this.serialNumber = Tools.generateUniqueID("balloon");

		LocalDate randomDay = Tools.randomDate();
		this.launchDay = randomDay.format(DateTimeFormatter.ofPattern("dd"));
		this.launchMonth = randomDay.format(DateTimeFormatter.ofPattern("MMMM"));
		this.launchYear = randomDay.format(DateTimeFormatter.ofPattern("yyyy"));

		Pair<Double, Double> randomLocation =
				Tools.randomLocationAroundPoint(Tools.ROMANIA_LAT, Tools.ROMANIA_LONG, Tools.ROMANIA_RADIUS);
		this.launchLat = randomLocation.getFirst();
		this.launchLong = randomLocation.getSecond();
	}

	// --------------------------------------------------- Methods ---------------------------------------------------
	public String generateSpeed() {
		return Integer.toString(Tools.randomInt(0, MAX_SPEED));
	}

	public String generateWindSpeed() {
		return Integer.toString(Tools.randomInt(0, MAX_WIND_SPEED));
	}

	public String generateAltitude() {
		return Integer.toString(Tools.randomInt(0, ALTITUDE));
	}

	public String getLaunchDate() {
		return getLaunchDay() + " " + getLaunchMonth() + " " + getLaunchYear();
	}

	public String getLaunchLocation() {
		return getLaunchLat() + "," + getLaunchLong();
	}

	public String getCurrentLocation() {
		return Tools.roundDouble(getLaunchLat() + 5.5, Tools.COORDINATES_DECIMALS) + "," +
				Tools.roundDouble(getLaunchLong() + 6.5, Tools.COORDINATES_DECIMALS);
	}

	public JSONObject getJson() {
		AirStats airStats = new AirStats();

		JSONObject jsonObject = new JSONObject();
		jsonObject.put("Name", getName());
		jsonObject.put("serialNumber", getSerialNumber());
		jsonObject.put("launchDate", getLaunchDate());
		jsonObject.put("launchLocation", getLaunchLocation());
		jsonObject.put("currentLocation", getCurrentLocation());
		jsonObject.put("currentSpeed", generateSpeed());
		jsonObject.put("windSpeed", generateWindSpeed());
		jsonObject.put("altitude", generateAltitude());
		jsonObject.put("airPressure", AirStats.randomPressure());
		jsonObject.put("airTemperature", AirStats.randomTemperature());
		jsonObject.put("oxygen", airStats.getOxygen());
		jsonObject.put("nitrogen", airStats.getNitrogen());
		jsonObject.put("argon", airStats.getArgon());
		jsonObject.put("carbonDioxide", airStats.getCarbonDioxide());
		jsonObject.put("neon", airStats.getNeon());
		jsonObject.put("helium", airStats.getHelium());
		jsonObject.put("methan", airStats.getMethan());
		jsonObject.put("krypton", airStats.getKrypton());
		jsonObject.put("others", airStats.getOthers());

		return jsonObject;
	}

	// ---------------------------------------------- Getters & Setters ----------------------------------------------

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSerialNumber() {
		return serialNumber;
	}

	public void setSerialNumber(String serialNumber) {
		this.serialNumber = serialNumber;
	}

	public String getLaunchDay() {
		return launchDay;
	}

	public void setLaunchDay(String launchDay) {
		this.launchDay = launchDay;
	}

	public String getLaunchMonth() {
		return launchMonth;
	}

	public void setLaunchMonth(String launchMonth) {
		this.launchMonth = launchMonth;
	}

	public String getLaunchYear() {
		return launchYear;
	}

	public void setLaunchYear(String launchYear) {
		this.launchYear = launchYear;
	}

	public Double getLaunchLat() {
		return launchLat;
	}

	public void setLaunchLat(Double launchLat) {
		this.launchLat = launchLat;
	}

	public Double getLaunchLong() {
		return launchLong;
	}

	public void setLaunchLong(Double launchLong) {
		this.launchLong = launchLong;
	}
}
