// IP Intelligence REST Web Service  
// @author www.quova.com 
//Copyright 2010 Quova, Inc. 
//This example illustrates how to execute a web service request via HTTP GET. 08 
package unboredify;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class RestfulCall {

	private String service = "http://api.quova.com/";
	private String version = "v1/";
	private String method = "ipinfo/";
	private String ip;
	private String apikey = "100.pgusmkag5st245g6d3bx";
	private String secret = "MTvkMTx5";

	RestfulCall(String ip) {
		this.ip = ip;
	}
	public String getResult() {
		MessageDigest md = null;
		try {
			md = MessageDigest.getInstance("MD5");
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		long timeInSeconds = (long)(System.currentTimeMillis()/1000);
		String input = apikey + secret + timeInSeconds;
		md.update(input.getBytes());
		String sig = String.format("%032x", new BigInteger(1, md.digest()));

		String url = service + version + method + ip + "?apikey=" + apikey
				+ "&sig=" + sig + "&format=json";
		return url;
	}

}