package br.com.sw2.comercial.service.flickr;

import java.io.UnsupportedEncodingException;

import org.scribe.builder.ServiceBuilder;
import org.scribe.builder.api.FlickrApi;
import org.scribe.model.Token;
import org.scribe.model.Verifier;
import org.scribe.oauth.OAuthService;

public class LoginFlickr {
	
	
	
	private static final String API_SECRET = "dcbf1a00eb94c955";
	private static final String API_KEY = "059deea1920acce2ac4be626612bd946";
	private static final String CALLBACK_URL = "http://localhost:8080/Comercial/loginFlickr";
	//private static final String FLICKR_REST_URL = "https://api.flickr.com/services/rest/";
	
	private static OAuthService service;
	private static Token requestToken;
	private static Token accessToken;

	public String getLoginRedirectOAuthURL() {
		service = new ServiceBuilder().provider(FlickrApi.class)
				.apiKey(API_KEY).apiSecret(API_SECRET).callback(CALLBACK_URL).build();
		
		requestToken = service.getRequestToken();
		
		String authorizationUrl = service.getAuthorizationUrl(requestToken);
		
		return authorizationUrl + "&perms=write";
	}

	public String obterUsuarioFlickr(String oauth_verifier) throws UnsupportedEncodingException {
		Verifier verifier = new Verifier(oauth_verifier);
		accessToken = service.getAccessToken(requestToken, verifier);
        String rawResponse = accessToken.getRawResponse();
        
		/*OAuthRequest request = new OAuthRequest(Verb.GET, FLICKR_REST_URL);
		request.addQuerystringParameter("method", "flickr.test.login");
		request.addQuerystringParameter("format", "json");
		request.addQuerystringParameter("api_key", API_KEY);
		service.signRequest(accessToken, request);
		Response response = request.send();
		
		JsonParser parser = new JsonParser();
		JsonObject json = (JsonObject)parser.parse(response.getBody());*/
		return rawResponse;
	}

	/*private Token getAccessToken(String oauth_verifier) {
		Verifier verifier = new Verifier(oauth_verifier);
		
		// Flickr seems to return invalid token sometimes so retry a few times.
        // See http://www.flickr.com/groups/api/discuss/72157628028927244/
        boolean success = false;
        Token accessToken = null;
        for (int i = 0; i < 10 && !success; i++) {
            try {
            	accessToken = service.getAccessToken(requestToken, verifier);
                System.out.println(accessToken);
                success = true;
            } catch (OAuthException e) {
                if (i == 10) {
                	System.out.println("Erro ao tentar trocar o requestToken por accessToken, após 10 tentativas!!!");
                	e.printStackTrace();
                } else {
                	System.out.println("Erro ao tentar trocar o requestToken por accessToken. Tentando outra vez em 5s....");
                        try {
							Thread.sleep(5000);
						} catch (InterruptedException e1) {
							e1.printStackTrace();
						}
                }
            }
        }
		return accessToken;
	}
*/

	public static String getApiKey() {
		return API_KEY;
	}
	
	public static OAuthService getService() {
		return service;
	}
	
	public static Token getAccessToken() {
		return accessToken;
	}
}
