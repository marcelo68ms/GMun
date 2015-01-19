package br.com.sw2.comercial.service.trello;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.JsonObject;

@WebServlet("/loginTrello")
public class TrelloServlet extends HttpServlet {
	
	private static final long serialVersionUID = 4186972851567033192L;

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String verifier = request.getParameter("oauth_verifier");
		if (verifier == null || verifier.equals("")) {
			throw new RuntimeException("Não foi possível recuperar o parâmetro verifier");
		}
		
		LoginTrello loginTrello = new LoginTrello();
		JsonObject usuario = loginTrello.obterUsuarioTrello(verifier);
		
		request.getSession().setAttribute("user", usuario.get("fullName").getAsString());
		response.sendRedirect(request.getContextPath() + "/trelloLogin.jsp");
		
	}
}
