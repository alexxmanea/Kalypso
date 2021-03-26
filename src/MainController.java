import entities.Balloon;
import org.json.JSONObject;
import tools.ThingworxRestPropertyUpdater;
import tools.Tools;

import java.util.List;

public class MainController {
	private static final String SERVER_URL = "https://pp-2103181001eo.devportal.ptc.io";
	private static final String APP_KEY = "da664595-68db-44ce-9af0-86640a601886";

	public static void main(String[] args) {
		List<JSONObject> thingWorxJsons = Tools.createBalloonJSONS();

		for (int i = 0; i < thingWorxJsons.size(); ++i) {
			ThingworxRestPropertyUpdater rest = new ThingworxRestPropertyUpdater();
			try {
				String thingName = Balloon.NAME_PREFIX + (i + 1);
				int response = rest.restUpdateProperties(SERVER_URL, APP_KEY, thingName, thingWorxJsons.get(i));
				System.out.println("Response Status = " + response + "\n");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}