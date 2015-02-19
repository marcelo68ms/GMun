package br.com.sw2.comercial.service.flickr;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.scribe.model.OAuthRequest;
import org.scribe.model.Response;
import org.scribe.model.Verb;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

@WebServlet("/viewPhotos")
public class UserPhotosServlet extends HttpServlet {

	private static final long serialVersionUID = 1848444214276331344L;
	private static final String METHOD = "flickr.people.getPhotos";
	private static final String FLICKR_REST_URL = "https://api.flickr.com/services/rest/";

	@Override
	protected void service(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		String nsid = (String) request.getSession().getAttribute("nsid");

		System.out.println("nsid decode: " + nsid);

		OAuthRequest oauthRequest = new OAuthRequest(Verb.GET, FLICKR_REST_URL);
		oauthRequest.addQuerystringParameter("method", METHOD);
		oauthRequest.addQuerystringParameter("format", "json");
		oauthRequest.addQuerystringParameter("nojsoncallback", "1");
		oauthRequest.addQuerystringParameter("user_id", nsid);
		oauthRequest.addQuerystringParameter("api_key", LoginFlickr.getApiKey());
		LoginFlickr.getService().signRequest(LoginFlickr.getAccessToken(), oauthRequest);
		Response oauthResponse = oauthRequest.send();

		System.out.println(oauthResponse.getBody());

		JsonParser parser = new JsonParser();
		JsonObject json = (JsonObject) parser.parse(oauthResponse.getBody());
		JsonObject photos = json.get("photos").getAsJsonObject();
		JsonArray photoArray = photos.get("photo").getAsJsonArray();

		ServletOutputStream out = response.getOutputStream();
		out.println("<h1>Flickr Images</h1>");

		for (JsonElement jsonElement : photoArray) {

			String id = jsonElement.getAsJsonObject().get("id").getAsString();
			String secret = jsonElement.getAsJsonObject().get("secret").getAsString();
			String server = jsonElement.getAsJsonObject().get("server").getAsString();
			String farm = jsonElement.getAsJsonObject().get("farm").getAsString();

			StringBuffer imageUrl = new StringBuffer("https://farm")
					.append(farm).append(".staticflickr.com/")
					.append(server).append("/").append(id).append("_")
					.append(secret).append(".jpg");

			System.out.println(imageUrl.toString());

			out.println("<img src=\"" + imageUrl.toString() + "\">");
		}

		out.println("<input type=\"button\" onClick=\"history.go(-1)\" value=\"Voltar\">");
	}

}
