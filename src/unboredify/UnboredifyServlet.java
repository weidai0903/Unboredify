package unboredify;

import java.io.IOException;
import javax.servlet.http.*;

import org.json.JSONException;
import org.json.JSONObject;


import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

/**
 * 
 * @author  Wei Dai, Ruogu Hu
 * Unboredify yourself with automatic scheduling
 *
 */
@SuppressWarnings("serial")
public class UnboredifyServlet extends HttpServlet {

	private StringBuilder googleQuery;

	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {

		resp.setContentType("text");

		// get userIP address
		req.getHeader("VIA");
		String ipAddress = req.getHeader("X-FORWARDED-FOR");
		if (ipAddress == null) {
			ipAddress = req.getRemoteAddr();
		}

		RestfulCall rc = new RestfulCall(ipAddress);
		//			try {
		//				System.out.println(rc.getResult());
		//			} catch (Exception e) {
		//				e.printStackTrace();
		//			}

		URL	restfulAddress = new URL(rc.getResult());
		GeoLocation gl = new GeoLocation(restfulAddress);
		String location = null;
		try {
			location = gl.getLocation().getJSONObject("ipinfo").getJSONObject("Location").getString("latitude").toString() +
					"," + gl.getLocation().getJSONObject("ipinfo").getJSONObject("Location").getString("longitude").toString();
		}  catch (JSONException j) {
			location = "39.96405,-75.19718";	// set location to philly if IP undetected
		}
		String key, coordinate, radius, sensor, keyword;
		key = "AIzaSyCxXPOv7NwYui0smT8hwRuc-Dd7MXwhL3U";	// google API key
		coordinate = location;
		radius = "1500";
		sensor = "false";

		googleQuery = new StringBuilder("https://maps.googleapis.com/maps/api/place/search/json?"+ "&key=" + key);
		googleQuery.append("&location=" + coordinate).append("&radius=" + radius).append("&sensor=" + sensor);

		RandSchedule rs = new RandSchedule();
		HashMap<String, String> schedule = new HashMap<String, String>();
		schedule.put("morning: ", rs.getDayEvent());
		schedule.put("lunch: ", rs.getEatEvent());
		schedule.put("afternoon: ", rs.getDayEvent());
		schedule.put("dinner: ", rs.getEatEvent());
		schedule.put("night: ", rs.getNightEvent());

		StringBuilder sb = new StringBuilder();
		for (String time : schedule.keySet()) {
			sb.append(produceHtml(schedule.get(time), time));
		}
		
		// output
		resp.getWriter().println(sb.toString());

	}
	
	private String produceHtml(String eventName, String time) {
		StringBuilder query = new StringBuilder();
		query.append(googleQuery).append("&keyword=" + eventName);
		System.out.println(query.toString());
		URL	googlePlacesAddress = null;
		try {
			googlePlacesAddress = new URL(query.toString());
		}catch(Exception e){
			e.printStackTrace();
		}
		GooglePlaces gp = new GooglePlaces(googlePlacesAddress);
		
		Random rand = new Random();
		StringBuilder html = new StringBuilder();
		int i;
		String name, address, rating, icon;

		try {
			i = rand.nextInt(gp.getInfo().getJSONArray("results").length()) + 1;
			System.out.println("# items: " + gp.getInfo().getJSONArray("results").length());
			System.out.println("index: " + i);
			name = gp.getInfo().getJSONArray("results").getJSONObject(i).getString("name");
			address = gp.getInfo().getJSONArray("results").getJSONObject(i).getString("vicinity");
			icon = gp.getInfo().getJSONArray("results").getJSONObject(i).getString("icon");

			//System.out.println(address);
			html.append("<li><div>" + time + "</div><div class=\"morning\" class=\"icon\"><img src=\"" + icon);
			html.append("\" /></div><div class=\"morning\" class=\"name\">What: " + name); 
			html.append("</div><div class=\"morning\" class=\"addr\">where: " + address);
			
			rating = gp.getInfo().getJSONArray("results").getJSONObject(i).getString("rating");
			html.append("</div><div class=\"morning\" class=\"rating\">rating: " + rating + "</div></li>");
		} catch (JSONException j) {
			html.append("");
		} catch (IllegalArgumentException n) {	// nothing found
			html.append("");
		}

		return html.toString();
	}
}
