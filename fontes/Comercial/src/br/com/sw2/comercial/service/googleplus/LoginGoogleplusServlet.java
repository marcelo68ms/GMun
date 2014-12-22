package br.com.sw2.comercial.service.googleplus;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.JsonObject;

@WebServlet("/loginGoogleplus")
public class LoginGoogleplusServlet extends HttpServlet {

	private static final long serialVersionUID = -1669969568924936565L;

	@Override
	protected void service(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String code = request.getParameter("code");
		if (code == null || code.equals("")) {
			throw new RuntimeException(
					"Não foi possível recuperar o parâmetro code");
		}

		LoginGoogleplus loginGoogle = new LoginGoogleplus();
		JsonObject googleUser = loginGoogle.obterUsuarioGoogle(code);
		
		PrintWriter out = response.getWriter();
		out.println("<h1>Google+ Login</h1>");
		if (googleUser.get("gender").getAsString().equals("male")) {
			out.println("<div>Bem-vindo "+googleUser.get("displayName").getAsString());
		} else {
			out.println("<div>Bem-vinda "+googleUser.get("displayName").getAsString());
		}
		out.println("<div>Frase pessoal: "+googleUser.get("tagline").getAsString());
		out.println("<div>Sobre mim: "+googleUser.get("aboutMe").getAsString());
		
		out.println("<br><h2>Mensagens</h2>");
		
		List<String> atividades = loginGoogle.obterAtividadesGoogle();
		for (String atividade : atividades) {
			out.println("<div>" + atividade); 
		}
		
	}

}
