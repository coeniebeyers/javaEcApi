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

	private final String USER_AGENT = "Mozilla/5.0";

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
}
