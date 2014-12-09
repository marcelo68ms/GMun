package br.com.sw2.comercial.service.facebook;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/loginFacebook")
public class FacebookServlet extends HttpServlet {

	private static final long serialVersionUID = 2619337015041744431L;
	
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String code = request.getParameter("code");
		if (code == null || code.equals("")) {
			throw new RuntimeException("Não foi possível recuperar o parâmetro code");
		}
		
		LoginFacebook loginFacebook = new LoginFacebook();
		FacebookUser facebookUser = loginFacebook.obterUsuarioFacebook(code);

		ServletOutputStream out = response.getOutputStream();
		out.println("<h1>Facebook Login</h1>");
		if (facebookUser.getGender().equals("male")) {
			out.println("<div>Bem-vindo "+facebookUser.getFirstName());
		} else {
			out.println("<div>Bem-vinda "+facebookUser.getFirstName());
		}
		out.println("<div>E-mail: "+facebookUser.getEmail());
		out.println("<div>Aniversário: "+facebookUser.getBirthday());
		out.println("<div>Cidade natal: "+facebookUser.getHometown());
		out.println("<div>Cidade atual: "+facebookUser.getLocation());
	}

}
