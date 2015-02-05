package br.com.sw2.comercial.service.instagram;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.sw2.comercial.service.Utils;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

@WebServlet("/listFollows")
public class FollowsServlet extends HttpServlet {

	private static final String INSTA_URL = "https://api.instagram.com";
	private static final long serialVersionUID = 4718007271978082071L;
	
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String accessToken = LoginInstagram.getAccessToken();
		String userId = LoginInstagram.getUserId();
		
		StringBuffer url = new StringBuffer(INSTA_URL).append("/v1/users/").append(userId)
				.append("/follows?access_token=").append(accessToken);
		
		String retorno = Utils.readURL(url.toString());
		
		JsonParser parser = new JsonParser();
		JsonObject json = (JsonObject)parser.parse(retorno);
		JsonArray jsonFollows = json.get("data").getAsJsonArray();
		
		List<String> names = new ArrayList<String>();
		for (JsonElement jsonElement : jsonFollows) {
			names.add(jsonElement.getAsJsonObject().get("username").getAsString());
		}
		
		request.getSession().setAttribute("follows", names.toString());
		
		response.sendRedirect(request.getContextPath() + "/instagramLogin.jsp");
		
	}

}
