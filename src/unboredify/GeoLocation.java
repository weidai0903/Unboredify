package unboredify;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

import org.json.JSONException;
import org.json.JSONObject;





public class GeoLocation {
	JSONObject restfulOutput = null;	
	HttpURLConnection connection = null;
	OutputStreamWriter wr = null;
	BufferedReader rd  = null;
	StringBuilder sb = null;
	String line = null;
	URL address = null;

	public GeoLocation(URL address) {
		this.address = address;
	}
	public JSONObject getLocation() {
		try {
		//set up out communications stuff
		connection = null;

		//Set up the initial connection
		connection = (HttpURLConnection)address.openConnection();
		connection.setRequestMethod("GET");
		connection.setDoOutput(true);
		connection.setReadTimeout(10000);

		connection.connect();

		//get the output stream writer and write the output to the server
		//not needed in this example
		//wr = new OutputStreamWriter(connection.getOutputStream());
		//wr.write("");
		//wr.flush();

		//read the result from the server
		rd  = new BufferedReader(new InputStreamReader(connection.getInputStream()));
		sb = new StringBuilder();

		while ((line = rd.readLine()) != null)
		{
			sb.append(line + '\n');
		}

		//System.out.println(sb.toString());
		restfulOutput = new JSONObject(sb.toString());

	} catch (MalformedURLException e) {
		e.printStackTrace();
	} catch (ProtocolException e) {
		e.printStackTrace();
	} catch (IOException e) {
		e.printStackTrace();
	} catch (JSONException e) {
		e.printStackTrace();
	} finally {
		//close the connection, set all objects to null
		connection.disconnect();
		rd = null;
		sb = null;
		wr = null;
		connection = null;
	}
		
		return restfulOutput;
	}
}
