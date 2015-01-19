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

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

@WebServlet("/createCard")
public class CreateCardServlet extends HttpServlet {

	private static final long serialVersionUID = 756949420442550944L;

	@Override
	protected void service(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		String boardId = (String) request.getSession().getAttribute("boardId");
		
		// Primeiro é preciso recuperar as listas do quadro
		OAuthRequest oAuthRequest = new OAuthRequest(Verb.GET, "https://trello.com/1/boards/" + boardId + "/lists");
		LoginTrello.getService().signRequest(LoginTrello.getAccessToken(), oAuthRequest);
		Response oAuthResponse = oAuthRequest.send();
		
		JsonParser parser = new JsonParser();
		String body = oAuthResponse.getBody();
		JsonArray lists = (JsonArray) parser.parse(body);
		
		// Vamos colocar o cartão na primeira lista do array só para teste
		String idList = lists.get(0).getAsJsonObject().get("id").getAsString();
		
		oAuthRequest = new OAuthRequest(Verb.POST, "https://trello.com/1/cards");
		oAuthRequest.addBodyParameter("idList", idList);
		oAuthRequest.addBodyParameter("name", "Teste CRUD");
		
		LoginTrello.getService().signRequest(LoginTrello.getAccessToken(), oAuthRequest);
		oAuthResponse = oAuthRequest.send();
		
		body = oAuthResponse.getBody();
		JsonObject card = (JsonObject)parser.parse(body);
		String cardId = card.get("id").getAsString();
		
		request.getSession().setAttribute("cardId", cardId);
		response.sendRedirect(request.getContextPath() + "/trelloLogin.jsp");

	}

}
