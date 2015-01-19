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
import com.google.gson.JsonParser;

@WebServlet("/moveCard")
public class MoveCardServlet extends HttpServlet {

	private static final long serialVersionUID = 4127331937928468624L;

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
		System.out.println(lists);
		
		// Vamos colocar o cartão na última lista do array só para teste
		String idList = lists.get(2).getAsJsonObject().get("id").getAsString();
		
		String cardId = (String) request.getSession().getAttribute("cardId");
		oAuthRequest = new OAuthRequest(Verb.PUT, "https://trello.com/1/cards/" + cardId + "/idList");
		oAuthRequest.addBodyParameter("value", idList);
		
		LoginTrello.getService().signRequest(LoginTrello.getAccessToken(), oAuthRequest);
		oAuthResponse = oAuthRequest.send();
		
		if (oAuthResponse.getCode() == 200) request.getSession().setAttribute("moved", true);
		
		response.sendRedirect(request.getContextPath() + "/trelloLogin.jsp");

	}

}
