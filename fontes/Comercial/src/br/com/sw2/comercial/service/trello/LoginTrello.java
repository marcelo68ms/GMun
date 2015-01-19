package br.com.sw2.comercial.service.trello;

import java.io.UnsupportedEncodingException;

import org.scribe.builder.ServiceBuilder;
import org.scribe.builder.api.TrelloApi;
import org.scribe.model.OAuthRequest;
import org.scribe.model.Response;
import org.scribe.model.Token;
import org.scribe.model.Verb;
import org.scribe.model.Verifier;
import org.scribe.oauth.OAuthService;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class LoginTrello {

	private static final String API_SECRET = "dfc25f7abb35ae691d7cf85064e38130dd6490ffb502dc5dacb653935245febe";
	private static final String API_KEY = "252102bf7923cf5dcb2d0631fdb82064";
	private static final String redirect_uri = "http://localhost:8080/Comercial/callbackTrello.jsp";
	private static final String CALLBACK_URL = "http://localhost:8080/Comercial/loginTrello";
	
	private static OAuthService service;
	
	private static Token accessToken;

	public String getLoginRedirectURL() {
		return "https://trello.com/1/authorize?key=" + API_KEY
				+ "&name=OdonTO-DO&expiration=1day&response_type=token&scope=read,write"
				+ "&callback_method=fragment&redirect_uri=" + redirect_uri;
	}

	public String getLoginRedirectOAuthURL() throws UnsupportedEncodingException {
		
		service = new ServiceBuilder()
		.provider(TrelloApi.class).apiKey(API_KEY).apiSecret(API_SECRET)
		.callback(CALLBACK_URL).build();
		
		// Obtain the Request Token
		Token requestToken = this.getRequestToken();
		
		String authorizationUrl = service.getAuthorizationUrl(requestToken);
		authorizationUrl+="&scope=read,write";
		return authorizationUrl;
	}

	public JsonObject obterUsuarioTrello(String oauth_verifier) {

		Verifier verifier = new Verifier(oauth_verifier);
		Token requestToken = this.getRequestToken();
		// Trade the Request Token and Verfier for the Access Token
		accessToken = service.getAccessToken(requestToken, verifier);

		OAuthRequest request = new OAuthRequest(Verb.GET, "https://trello.com/1/members/me");
		service.signRequest(accessToken, request);
		Response response = request.send();
		
		JsonParser parser = new JsonParser();
		JsonObject json = (JsonObject)parser.parse(response.getBody());
		
		return json;
	}

	private Token getRequestToken() {
		return service.getRequestToken();
	}
	
	public static String getToken() {
		return accessToken.getToken();
	}
	
	public static OAuthService getService() {
		return service;
	}
	
	public static Token getAccessToken() {
		return accessToken;
	}

}
