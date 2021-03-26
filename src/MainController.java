import org.json.JSONObject;
import tools.Tools;

import java.util.List;

public class MainController {
	public static void main(String[] args) {
		List<JSONObject> thingWorxJsons = Tools.createBalloonJSONS();

//		like http://localhost:8080 or https://localhost:443
		String serverUrl = "";
//      Generate one of these from the composer under Application Keys
		String appKey = "";
		String thingName = "";

//		ThingworxRestPropertyUpdater rest = new ThingworxRestPropertyUpdater();
//		try {
//			int response = rest.restUpdateProperties(serverUrl, appKey, thingName, thingWorxJsons.get(0));
//			System.out.println("Response Status=" + response);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
	}
}