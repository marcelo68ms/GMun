package br.com.sw2.comercial.service.paypal;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.message.BasicNameValuePair;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

public class SimplePaymentService {

	private static final String RETURN_URL = "http://localhost:8080/Comercial/executePayment";
	private static String access_token;

	public String getPaymentApprovaltURL() {
		access_token = getAccessToken();
		return createPayment();
	}

	public String getAccessToken() {
		StringBuffer url = new StringBuffer(Utils.SANDBOX_URL).append("/v1/oauth2/token");

		HttpPost post = new HttpPost(url.toString());

		// add request header
		String base64encoded = Utils.generateBase64encoded();
		post.addHeader(Utils.AUTHORIZATION, Utils.BASIC +base64encoded);
		post.addHeader("Accept", Utils.APPLICATION_JSON);
		post.addHeader(Utils.CONTENT_TYPE, "application/x-www-form-urlencoded");

		List<NameValuePair> urlParameters = new ArrayList<NameValuePair>();
		urlParameters.add(new BasicNameValuePair(Utils.GRANT_TYPE, "client_credentials"));
		try {
			post.setEntity(new UrlEncodedFormEntity(urlParameters));
		} catch (UnsupportedEncodingException e1) {
			e1.printStackTrace();
		}

		String result = Utils.doRequest(post);
		
		JsonParser parser = new JsonParser();
		JsonElement jsonElement = parser.parse(result);
		return jsonElement.getAsJsonObject().get("access_token").getAsString();
	}

	public String createPayment() {
		StringBuffer url = new StringBuffer(Utils.SANDBOX_URL).append(Utils.PAYMENT_URL);
		
		HttpPost post = new HttpPost(url.toString());

		// add request header
		post.addHeader(Utils.AUTHORIZATION, Utils.TOKEN_TYPE + access_token);
		post.addHeader(Utils.CONTENT_TYPE, Utils.APPLICATION_JSON);
		
		StringBuffer urlParameters = new StringBuffer("{\"intent\":\"sale\",  \"redirect_urls\":");
		urlParameters.append("{\"return_url\":\"").append(RETURN_URL).append("\",");
		urlParameters.append("\"cancel_url\":\"").append(Utils.CANCEL_URL).append("\"},");
		urlParameters.append("\"payer\":{\"payment_method\":\"paypal\"},");
		urlParameters.append("\"transactions\":[{\"amount\":{");
		urlParameters.append("\"total\":\"67.43\",\"currency\":\"BRL\"}}]}");
			  
		try {
			post.setEntity(new StringEntity(urlParameters.toString()));
		} catch (UnsupportedEncodingException e1) {
			e1.printStackTrace();
		}

		String result = Utils.doRequest(post);
		
		// Recupera o link approval_url
		JsonParser parser = new JsonParser();
		JsonElement jsonElement = parser.parse(result);
		JsonArray links = jsonElement.getAsJsonObject().get("links").getAsJsonArray();
		return links.get(1).getAsJsonObject().get("href").getAsString();
	}
	
	public static String getToken() {
		return access_token;
	}

}
