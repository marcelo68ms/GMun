package br.com.sw2.comercial.service.linkedin;

import java.io.IOException;

import br.com.sw2.comercial.service.Utils;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonSyntaxException;

public class LoginLinkedin {

	private static final String API_KEY = "&client_id=75yt9xq172f1hu";
	private static final String REDIRECT_URI = "&redirect_uri=http://localhost:8080/Comercial/loginLinkedin";
	private static final String API_SECRET = "&client_secret=1Zyj1uE1xhT2R7Oj";

	public String getLoginRedirectURL() {
		StringBuffer url = new StringBuffer(
				"https://www.linkedin.com/uas/oauth2/authorization?response_type=code")
				.append(API_KEY).append(REDIRECT_URI)
				.append("&scope=r_fullprofile%20r_emailaddress&state=S1T2A3T4E5");
		return url.toString();
	}

	public String obterTokenAcesso(String code) {
		JsonParser parser = new JsonParser();
		String xml = null;
		try {
			JsonObject jsonToken = (JsonObject) parser.parse(Utils.readURL(getJsonToken(code)));
			String token = jsonToken.get("access_token").getAsString();
			StringBuffer url = new StringBuffer("https://api.linkedin.com/v1/people/~:(id,first-name,last-name,")
					.append("email-address,date-of-birth,industry,summary,specialties,positions,languages,skills,")
					.append("certifications,educations,courses)?oauth2_access_token=").append(token);
			
			xml = Utils.readURL(url.toString());
		} catch (JsonSyntaxException | IOException e) {
			e.printStackTrace();
		}
		return xml;

	}

	private String getJsonToken(String code) {
		StringBuffer url = new StringBuffer(
				"https://www.linkedin.com/uas/oauth2/accessToken?grant_type=authorization_code&code=")
				.append(code).append(REDIRECT_URI).append(API_KEY).append(API_SECRET);
		return url.toString();

	}

}
