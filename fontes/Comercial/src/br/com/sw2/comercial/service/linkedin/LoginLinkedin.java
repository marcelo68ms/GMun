package br.com.sw2.comercial.service.linkedin;

import java.io.IOException;
import java.net.URL;

import br.com.sw2.comercial.service.Utils;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonSyntaxException;

public class LoginLinkedin {

	private static final String API_KEY = "75yt9xq172f1hu";
	private static final String REDIRECT_URI = "http://localhost:8080/Comercial/loginLinkedin";
	private static final String API_SECRET = "1Zyj1uE1xhT2R7Oj";

	public String getLoginRedirectURL() {
		return "https://www.linkedin.com/uas/oauth2/authorization?response_type=code" 
					+ "&client_id=" + API_KEY 
					+ "&scope=r_fullprofile%20r_emailaddress&state=S1T2A3T4E5&redirect_uri=" + REDIRECT_URI;
	}

	public String obterTokenAcesso(String code) {
		JsonParser parser = new JsonParser();
		String xml = null;
		try {
			JsonObject jsonToken = (JsonObject)parser.parse(Utils.readURL(new URL(getJsonToken(code))));
			String token = jsonToken.get("access_token").getAsString();
			xml = Utils.readURL(new URL("https://api.linkedin.com/v1/people/~:(id,first-name,last-name,email-address,"
					+ "date-of-birth,industry,summary,specialties,positions,languages,skills,certifications,educations,courses)"
					+ "?oauth2_access_token="+token));
		} catch (JsonSyntaxException | IOException e) {
			e.printStackTrace();
		}
		return xml;
		
	}
	
	private String getJsonToken(String code) {
		return "https://www.linkedin.com/uas/oauth2/accessToken?grant_type=authorization_code"
				+ "&code=" + code + "&redirect_uri=" + REDIRECT_URI
				+ "&client_id=" + API_KEY + "&client_secret=" + API_SECRET;
		
	}
	
}
