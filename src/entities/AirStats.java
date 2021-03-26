package entities;

import tools.Tools;

import java.util.ArrayList;
import java.util.Collections;

public class AirStats {
	public static final double OXYGEN_LOWER_LEVEL = 72.0;
	public static final double OXYGEN_HIGHER_LEVEL = 78.0;
	public static final double MIN_TEMPERATURE = -40.0;
	public static final double MAX_TEMPERATURE = 40.0;
	public static final double MIN_PRESSURE = 500.0;
	public static final double MAX_PRESSURE = 1000.0;
	private static final int GAS_COUNT = 9;
	private double oxygen;
	private double nitrogen;
	private double argon;
	private double carbonDioxide;
	private double neon;
	private double helium;
	private double methan;
	private double krypton;
	private double others;

	// ------------------------------------------------ Constructors -------------------------------------------------
	public AirStats() {
		ArrayList<Double> gasses = generateRandomsToSum(100.0, GAS_COUNT - 1, randomOxygenLevel());
		this.oxygen = gasses.get(0);
		this.nitrogen = gasses.get(1);
		this.argon = gasses.get(2);
		this.carbonDioxide = gasses.get(3);
		this.neon = gasses.get(4);
		this.helium = gasses.get(5);
		this.methan = gasses.get(6);
		this.krypton = gasses.get(7);
		this.others = gasses.get(8);
	}

	// ------------------------------------------------ Static methods -----------------------------------------------
	public static double randomPressure() {
		return Tools.round(Tools.randomDouble(MIN_PRESSURE, MAX_PRESSURE), 1);
	}

	public static double randomTemperature() {
		return Tools.round(Tools.randomDouble(MIN_TEMPERATURE, MAX_TEMPERATURE), 1);
	}

	// --------------------------------------------------- Methods ---------------------------------------------------
	private double randomOxygenLevel() {
		return Tools.randomDouble(OXYGEN_LOWER_LEVEL, OXYGEN_HIGHER_LEVEL);
	}

	private ArrayList<Double> generateRandomsToSum(double targetSum, int numbers, double first) {
		first = Tools.round(first, 2);
		targetSum -= first;
		ArrayList<Double> load = new ArrayList<>();

		// Random numbers
		double sum = 0.0;
		for (int i = 0; i < numbers; ++i) {
			double next = Tools.randomDouble(0.0, targetSum) + 1;
			load.add(next);
			sum += next;
		}

		// Scale to the desired target sum
		double scale = targetSum / sum;
		sum = 0.0;
		for (int i = 0; i < numbers; ++i) {
			load.set(i, (Tools.round(load.get(i) * scale, 2)));
			sum += load.get(i);
		}
		sum = Tools.round(sum, 2);

		load.add(first);
		load.sort(Collections.reverseOrder());

		// Take rounding issues into account
		if (sum != targetSum) {
			load.set(0, Tools.round(load.get(0) + targetSum - sum, 2));
		}

		return load;
	}

	// ---------------------------------------------- Getters & Setters ----------------------------------------------
	public double getOxygen() {
		return oxygen;
	}

	public void setOxygen(double oxygen) {
		this.oxygen = oxygen;
	}

	public double getNitrogen() {
		return nitrogen;
	}

	public void setNitrogen(double nitrogen) {
		this.nitrogen = nitrogen;
	}

	public double getArgon() {
		return argon;
	}

	public void setArgon(double argon) {
		this.argon = argon;
	}

	public double getCarbonDioxide() {
		return carbonDioxide;
	}

	public void setCarbonDioxide(double carbonDioxide) {
		this.carbonDioxide = carbonDioxide;
	}

	public double getNeon() {
		return neon;
	}

	public void setNeon(double neon) {
		this.neon = neon;
	}

	public double getHelium() {
		return helium;
	}

	public void setHelium(double helium) {
		this.helium = helium;
	}

	public double getMethan() {
		return methan;
	}

	public void setMethan(double methan) {
		this.methan = methan;
	}

	public double getKrypton() {
		return krypton;
	}

	public void setKrypton(double krypton) {
		this.krypton = krypton;
	}

	public double getOthers() {
		return others;
	}

	public void setOthers(double others) {
		this.others = others;
	}
}
