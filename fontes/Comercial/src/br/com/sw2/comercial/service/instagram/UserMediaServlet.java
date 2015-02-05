package br.com.sw2.comercial.service.instagram;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import br.com.sw2.comercial.service.Utils;

@WebServlet("/viewMedia")
public class UserMediaServlet extends HttpServlet {

	private static final long serialVersionUID = -935163895225667135L;
	private static final String USERS_URL = "https://api.instagram.com/v1/users/";
	private static final String CLIENT_ID = "b88c7491e82c454b98610567292c7f96";
	
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		// Recuperar perfil do google
		StringBuffer url = new StringBuffer(USERS_URL).append("search?q=google&access_token=")
				.append(LoginInstagram.getAccessToken());
		
		String retorno = Utils.readURL(url.toString());
		JsonParser parser = new JsonParser();
		JsonObject json = (JsonObject)parser.parse(retorno);
		JsonArray jsonArray = json.get("data").getAsJsonArray();
		String profileId = jsonArray.get(0).getAsJsonObject().get("id").getAsString();
		
		// Recuperar as fotos recentes do perfil do google
		url = new StringBuffer(USERS_URL).append(profileId)
				.append("/media/recent/?client_id=").append(CLIENT_ID);
		
		retorno = Utils.readURL(url.toString());

		json = (JsonObject)parser.parse(retorno);
		jsonArray = json.get("data").getAsJsonArray();
		
		ServletOutputStream out = resp.getOutputStream();
		out.println("<h1>Google Images</h1>");
		
		for (JsonElement jsonElement : jsonArray) {
			JsonElement images = jsonElement.getAsJsonObject().get("images");
			String imageUrl = images.getAsJsonObject().get("thumbnail").getAsJsonObject().get("url").getAsString();
			out.println("<img src=\""+ imageUrl +"\">");
		}
		
		out.println("<input type=\"button\" onClick=\"history.go(-1)\" value=\"Voltar\">");
	}
}
