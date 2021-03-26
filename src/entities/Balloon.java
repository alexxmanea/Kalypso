package entities;

import org.json.JSONObject;
import tools.Pair;
import tools.Tools;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Balloon {
	public static final int BALLOONS_NUMBER = 5;
	private static final int MAX_SPEED = 150;
	private static final int MAX_WIND_SPEED = 150;
	private static final int ALTITUDE = 10000;
	private static final String NAME_PREFIX = "HAB";
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
		return getLaunchLat() + ", " + getLaunchLong();
	}

	public String getCurrentLocation() {
		return Tools.round(getLaunchLat() + 5.5, Tools.COORDINATES_DECIMALS) + ", " +
				Tools.round(getLaunchLong() + 6.5, Tools.COORDINATES_DECIMALS);
	}

	public JSONObject getJson() {
		AirStats airStats = new AirStats();

		JSONObject jsonObject = new JSONObject();
		jsonObject.put("Name", getName());
		jsonObject.put("Serial Number", getSerialNumber());
		jsonObject.put("Launch Date", getLaunchDate());
		jsonObject.put("Launch Location", getLaunchLocation());
		jsonObject.put("Current Location", getCurrentLocation());
		jsonObject.put("Current Speed", generateSpeed());
		jsonObject.put("Wind Speed", generateWindSpeed());
		jsonObject.put("Altitude", generateAltitude());
		jsonObject.put("Air Pressure", AirStats.randomPressure());
		jsonObject.put("Air Temperature", AirStats.randomTemperature());
		jsonObject.put("Oxygen", airStats.getOxygen());
		jsonObject.put("Nitrogen", airStats.getNitrogen());
		jsonObject.put("Argon", airStats.getArgon());
		jsonObject.put("Carbon Dioxide", airStats.getCarbonDioxide());
		jsonObject.put("Neon", airStats.getNeon());
		jsonObject.put("Helium", airStats.getHelium());
		jsonObject.put("Methan", airStats.getMethan());
		jsonObject.put("Krypton", airStats.getKrypton());
		jsonObject.put("Others", airStats.getOthers());

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
