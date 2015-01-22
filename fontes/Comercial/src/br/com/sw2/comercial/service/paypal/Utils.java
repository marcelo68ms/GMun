package br.com.sw2.comercial.service.paypal;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.impl.client.DefaultHttpClient;

import com.google.api.client.util.Base64;

public class Utils {
	
	private static final String UTF_8 = "UTF-8";
	public static final String APPLICATION_JSON = "application/json";
	public static final String CONTENT_TYPE = "Content-Type";
	public static final String AUTHORIZATION = "Authorization";
	public static final String CLIENT_ID = "AX9KzBBkPjz0a9TSHEIyWsPQHsvS2Fp1gxH9E_RRPHqMKQRRY9qEd9c_LgSY";
	public static final String SECRET = "EI-ynBAMP5A_IpuXYrJrQQtm4ghP2MIN5SJAqkB-NmbbN_jmIqOkW6JwdKTh";
	public static final String SANDBOX_URL = "https://api.sandbox.paypal.com";
	public static final String PAYMENT_URL = "/v1/payments/payment";
	public static final String TOKEN_TYPE = "Bearer ";
	public static final String BASIC = "Basic ";
	public static final String GRANT_TYPE = "grant_type";

	
	public static String generateBase64encoded() {
		String base64ClientID = null;
		String clientCredentials = CLIENT_ID + ":" + SECRET;
		try {
			byte[] encoded = Base64.encodeBase64(clientCredentials.getBytes(UTF_8));
			base64ClientID = new String(encoded, UTF_8);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}

		return base64ClientID;
	}
	
	public static String doRequest(HttpRequestBase request) {
		HttpClient client = new DefaultHttpClient();
		StringBuffer result = new StringBuffer();

		try {
			HttpResponse response = client.execute(request);

			System.out.println("Response Code : "
					+ response.getStatusLine().getStatusCode());

			BufferedReader rd = new BufferedReader(new InputStreamReader(
					response.getEntity().getContent()));

			String line = "";
			while ((line = rd.readLine()) != null) {
				result.append(line);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		System.out.println(result.toString());
		return result.toString();
	}

}
