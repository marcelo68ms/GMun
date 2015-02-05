package br.com.sw2.comercial.service.instagram;

import java.io.IOException;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import br.com.sw2.comercial.service.Utils;

public class LoginInstagram {
	
	private static final String CLIENT_ID = "b88c7491e82c454b98610567292c7f96";
	private static final String REDIRECT_URI = "http://localhost:8080/Comercial/loginInstagram";
	private static final String CLIENT_SECRET = "80db00f15e434700826a163b5e64f9be";
	private static String accessToken;
	private static String userId;

	public String getLoginRedirectURL() {
		StringBuffer url = new StringBuffer("https://api.instagram.com/oauth/authorize/?client_id=")
			.append(CLIENT_ID).append("&redirect_uri=").append(REDIRECT_URI).append("&response_type=code");
		
		return url.toString();
		
	}
	
	public String obterUsuarioInstagram(String code) {
		String name = null;
		try {
			StringBuffer urlParameters = new StringBuffer("client_id=").append(CLIENT_ID)
					.append("&grant_type=authorization_code&code=").append(code)
					.append("&client_secret=").append(CLIENT_SECRET)
					.append("&redirect_uri=").append(REDIRECT_URI)
					.append("&scope=relationships");
			String retorno = Utils.sendPost("https://api.instagram.com/oauth/access_token", urlParameters.toString());
			
			JsonParser parser = new JsonParser();
			JsonObject json = (JsonObject)parser.parse(retorno);
			
			accessToken = json.get("access_token").getAsString();
			
			JsonObject user = json.get("user").getAsJsonObject();
			name = user.get("full_name").getAsString();	
			userId = user.get("id").getAsString();	
		} catch (IOException e) {
			e.printStackTrace();
		}
		return name;
	}
	
	public static String getAccessToken() {
		return accessToken;
	}
	
	public static String getUserId() {
		return userId;
	}

}
