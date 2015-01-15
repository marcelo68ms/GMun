package br.com.sw2.comercial.service.googleplus;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.ArrayList;
import java.util.List;

import br.com.sw2.comercial.service.Utils;

import com.google.api.client.googleapis.auth.oauth2.GoogleCredential;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.services.plus.Plus;
import com.google.api.services.plus.model.Activity;
import com.google.api.services.plus.model.ActivityFeed;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class LoginGoogleplus {

	private static final String CLIENT_ID = "776101340018-ha404og3njrqev5dmv3stltnbn1q5ag0.apps.googleusercontent.com";
	private static final String CLIENT_SECRET = "1pDVjLp3ZQY7sXChPt_qqRJr";
	private static final String REDIRECT_URI = "&redirect_uri=http://localhost:8080/Comercial/loginGoogleplus";
	private static String accessToken= null;

	public String getLoginRedirectURL() {
		StringBuffer url = new StringBuffer("https://accounts.google.com/o/oauth2/auth?client_id=")
				.append(CLIENT_ID).append("&response_type=code").append(REDIRECT_URI)
				.append("&state=25B46O868II&scope=https://www.googleapis.com/auth/plus.login");
		return url.toString();
	}

	public JsonObject obterUsuarioGoogle(String code) {
		JsonObject json = null;
		JsonParser parser = new JsonParser();
		String url = "https://www.googleapis.com/oauth2/v3/token";
		StringBuffer urlParameters = new StringBuffer("client_id=").append(CLIENT_ID)
				.append("&grant_type=authorization_code&code=").append(code)
				.append("&client_secret=").append(CLIENT_SECRET)
				.append(REDIRECT_URI);
		try {
			String post = Utils.sendPost(url, urlParameters.toString());
			JsonObject retorno = (JsonObject) parser.parse(post);

			accessToken = retorno.get("access_token").getAsString();

			StringBuffer accessUrl = new StringBuffer("https://www.googleapis.com/plus/v1/people/me?access_token=")
					.append(accessToken);
			
			json = (JsonObject) parser.parse(Utils.readURL(accessUrl.toString()));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return json;

	}

	public List<String> obterAtividadesGoogle() {
		Plus plus = setUp();
		Plus.Activities.List listActivities = null;
		ActivityFeed activityFeed = null;
		try {
			listActivities = plus.activities().list("me", "public");

			// Execute a solicitação da primeira página 
			activityFeed = listActivities.execute();
		} catch (IOException e) {
			e.printStackTrace();
		}

		// Abra a solicitação e extraia as partes que desejamos
		List<Activity> activities = activityFeed.getItems();
		List<String> listaAtividades = new ArrayList<String>();
		// Faça um loop até que apareçamos em uma página vazia 
		while (activities != null) {
		  for (Activity activity : activities) {
			  listaAtividades.add(activity.getObject().getContent() + " - Publicado em: " + activity.getPublished());
		  }

		  // Saberemos que estamos na última página quando o token da próxima página for nulo.
		  // Se esse for o caso, pause.
		  if (activityFeed.getNextPageToken() == null) {
		    break;
		  }

		  // Prepare-se para solicitar a próxima página de atividades
		  listActivities.setPageToken(activityFeed.getNextPageToken());

		  // Execute e processe a solicitação da próxima página
		  try {
			activityFeed = listActivities.execute();
		} catch (IOException e) {
			e.printStackTrace();
		}
		  activities = activityFeed.getItems();
		}
		
		return listaAtividades;
	}
	
	private static Plus setUp() {
		GoogleCredential credential = null;

		JsonFactory jsonFactory = JacksonFactory.getDefaultInstance();
		HttpTransport httpTransport = null;

		try {
			httpTransport = GoogleNetHttpTransport.newTrustedTransport();
			credential = new GoogleCredential.Builder()
					.setTransport(httpTransport)
					.setJsonFactory(jsonFactory)
					.setClientSecrets(CLIENT_ID, CLIENT_SECRET).build().setAccessToken(accessToken);
			
		} catch (IOException | GeneralSecurityException e) {
			e.printStackTrace();
		}

		// Plus client
		return new com.google.api.services.plus.Plus.Builder(
				httpTransport, jsonFactory, credential).build();

	}
}
