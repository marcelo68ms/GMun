package br.com.sw2.comercial.service.paypal;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.message.BasicNameValuePair;

import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

public class LoginPayPal {
	
	private static final String AUTH_URL = "https://www.sandbox.paypal.com/webapps/auth/protocol/openidconnect/v1/authorize";
	private static final String ACCESS_URL = "/v1/identity/openidconnect/tokenservice";
	private static final String REDIRECT_URL = "http://localhost:8080/Comercial/loginPaypal";
	private static final String USERINFO_URL = "/v1/identity/openidconnect/userinfo/?schema=openid";
	
	public String getLoginRedirectURL() {
		StringBuffer url = new StringBuffer(AUTH_URL).append("?client_id=").append(Utils.CLIENT_ID)
				.append("&response_type=code&scope=profile+email+address+phone+https%3A%2F%2F")
				.append("uri.paypal.com%2Fservices%2Fpaypalattributes&redirect_uri=").append(REDIRECT_URL);
		return url.toString();
	}

	public String getPaypalUser(String code) {
		String accessToken = exchangeCode(code);
		
		StringBuffer url = new StringBuffer(Utils.SANDBOX_URL).append(USERINFO_URL);
		
		HttpGet get = new HttpGet(url.toString());
		get.addHeader(Utils.CONTENT_TYPE, Utils.APPLICATION_JSON);
		get.addHeader(Utils.AUTHORIZATION, Utils.TOKEN_TYPE + accessToken);
		
		return Utils.doRequest(get);
		
	}

	private String exchangeCode(String code) {
		StringBuffer url = new StringBuffer(Utils.SANDBOX_URL).append(ACCESS_URL);

		HttpPost post = new HttpPost(url.toString());

		// add request header
		String base64encoded = Utils.generateBase64encoded();
		post.addHeader(Utils.AUTHORIZATION, Utils.BASIC +base64encoded);
		
		List<NameValuePair> urlParameters = new ArrayList<NameValuePair>();
		urlParameters.add(new BasicNameValuePair(Utils.GRANT_TYPE, "authorization_code"));
		urlParameters.add(new BasicNameValuePair("code", code));
		urlParameters.add(new BasicNameValuePair("redirect_uri", REDIRECT_URL));
		try {
			post.setEntity(new UrlEncodedFormEntity(urlParameters));
		} catch (UnsupportedEncodingException e1) {
			e1.printStackTrace();
		}
		
		String result = Utils.doRequest(post);
		
		// Recupera o link approval_url
		JsonParser parser = new JsonParser();
		JsonElement jsonElement = parser.parse(result);
		return jsonElement.getAsJsonObject().get("access_token").getAsString();
	}
	
}
