package tools;

import org.json.JSONObject;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.X509Certificate;

public class ThingworxRestPropertyUpdater {
	static {
		// Disable All SSL Security Testing (Not for production!)
		try {
			disableSSLCertificateChecking();
		} catch (Exception e) {
			e.printStackTrace();
		}
		HttpsURLConnection.setDefaultHostnameVerifier((hostname, session) -> true);
	}

	private static void disableSSLCertificateChecking() throws KeyManagementException, NoSuchAlgorithmException {
		TrustManager[] trustAllCerts = new TrustManager[]{new X509TrustManager() {

			public X509Certificate[] getAcceptedIssuers() {
				return null;
			}

			public void checkClientTrusted(X509Certificate[] arg0, String arg1) {
			}

			public void checkServerTrusted(X509Certificate[] arg0, String arg1) {
			}

		}};

		SSLContext sc = SSLContext.getInstance("TLS");

		sc.init(null, trustAllCerts, new java.security.SecureRandom());

		HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());
	}

	public int restUpdateProperties(String serverUrl, String appKey, String thingName, JSONObject payload)
			throws IOException {
		String httpUrlString = serverUrl + "/Thingworx/Things/" + thingName + "/Properties/*";

		System.out.println("Performing HTTP PUT request to " + httpUrlString);
		System.out.println("Payload is " + payload);

		URL url = new URL(httpUrlString);
		HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
		httpURLConnection.setUseCaches(false);
		httpURLConnection.setDoOutput(true);
		httpURLConnection.setRequestMethod("PUT");
		httpURLConnection.setRequestProperty("Content-Type", "application/json");
		httpURLConnection.setRequestProperty("appKey", appKey);

		OutputStreamWriter out = new OutputStreamWriter(httpURLConnection.getOutputStream());
		out.write(payload.toString());
		out.close();

		httpURLConnection.getInputStream();
		return httpURLConnection.getResponseCode();
	}
}