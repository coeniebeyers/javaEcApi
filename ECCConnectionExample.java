import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import javax.net.ssl.HttpsURLConnection;
import java.net.ConnectException;
import org.json.simple.JSONObject;
import org.json.simple.JSONArray;
import org.json.simple.parser.ParseException;
import org.json.simple.parser.JSONParser;

public class ECCConnectionExample {

  private final static String BASE_URL = "http://localhost:8083";

	public static void main(String[] args) throws Exception {

		ECCConnectionExample http = new ECCConnectionExample();

		try {
      final JSONObject isAlive = http.isAlive();

			System.out.println(isAlive);
    }
    catch (ConnectException e) {
      System.out.println("ECC API Server not running");
      System.out.println("exiting...");
      System.exit(0);
    }

		final JSONObject curvepoint = http.sendPost();
		System.out.println(curvepoint);
	}

	// HTTP GET request
	private JSONObject isAlive() throws Exception {
		URL obj = new URL(BASE_URL + "/isalive/");
		HttpURLConnection conn = (HttpURLConnection) obj.openConnection();

		conn.setRequestMethod("GET");
		conn.setRequestProperty("Content-Type", "application/json");

		BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
		String inputLine;
		StringBuffer response = new StringBuffer();

		while ((inputLine = in.readLine()) != null) {
			response.append(inputLine);
		}
		in.close();

	  JSONParser parser = new JSONParser();
    JSONObject jsonObject = (JSONObject) parser.parse(response.toString());

		return jsonObject;
	}

	// HTTP POST request
	private JSONObject sendPost() throws Exception {

		URL obj = new URL(BASE_URL + "/ec/add/");
		HttpURLConnection conn = (HttpURLConnection) obj.openConnection();

		conn.setRequestMethod("POST");
		conn.setRequestProperty("Content-Type", "application/json");

		// Create JSON input
		JSONObject a = new JSONObject();
		JSONObject b = new JSONObject();

		a.put("x", "0x030644e72e131a029b85045b68181585d97816a916871ca8d3c208c16d87cfd3");
		a.put("y", "0x1a76dae6d3272396d0cbe61fced2bc532edac647851e3ac53ce1cc9c7e645a83");
		b.put("x", "0x0000000000000000000000000000000000000000000000000000000000000001");
		b.put("y", "0x30644e72e131a029b85045b68181585d97816a916871ca8d3c208c16d87cfd45");

		JSONObject input = new JSONObject();
		input.put("a", a);
		input.put("b", b);

		// Send post request
		conn.setDoOutput(true);
		DataOutputStream wr = new DataOutputStream(conn.getOutputStream());
		wr.writeBytes(input.toString());
		wr.flush();
		wr.close();

		// Create response
		BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
		String inputLine;
		StringBuffer response = new StringBuffer();

		while ((inputLine = in.readLine()) != null) {
			response.append(inputLine);
		}
		in.close();

		// JSONify
		JSONParser parser = new JSONParser();
		JSONObject jsonObject = (JSONObject) parser.parse(response.toString());

		return jsonObject;
	}
}
