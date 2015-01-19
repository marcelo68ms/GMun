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

@WebServlet("/removeCard")
public class RemoveCardServlet extends HttpServlet {

	private static final long serialVersionUID = 4127331937928468624L;

	@Override
	protected void service(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		
		String cardId = (String) request.getSession().getAttribute("cardId");
		OAuthRequest oAuthRequest = new OAuthRequest(Verb.DELETE, "https://trello.com/1/cards/" + cardId);
		
		LoginTrello.getService().signRequest(LoginTrello.getAccessToken(), oAuthRequest);
		Response oAuthResponse = oAuthRequest.send();
		
		if (oAuthResponse.getCode() == 200) request.getSession().setAttribute("removed", true);
		
		response.sendRedirect(request.getContextPath() + "/trelloLogin.jsp");

	}

}
