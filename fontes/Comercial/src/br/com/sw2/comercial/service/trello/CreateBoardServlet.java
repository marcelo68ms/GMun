package br.com.sw2.comercial.service.trello;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.scribe.model.OAuthRequest;
import org.scribe.model.Response;
import org.scribe.model.Verb;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

@WebServlet("/createBoard")
public class CreateBoardServlet extends HttpServlet {

	private static final long serialVersionUID = 6539472647131332452L;

	@Override
	protected void service(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		OAuthRequest oAuthRequest = new OAuthRequest(Verb.POST, "https://trello.com/1/boards");
		oAuthRequest.addBodyParameter("name", "Projeto Fapesp");
		
		LoginTrello.getService().signRequest(LoginTrello.getAccessToken(), oAuthRequest);
		Response oAuthResponse = oAuthRequest.send();
		
		JsonParser parser = new JsonParser();
		JsonObject board = (JsonObject)parser.parse(oAuthResponse.getBody());
		
		request.getSession().setAttribute("boardId", board.get("id").getAsString());
		response.sendRedirect(request.getContextPath() + "/trelloLogin.jsp");

	}

}
