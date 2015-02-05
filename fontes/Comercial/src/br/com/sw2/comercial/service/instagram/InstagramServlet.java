package br.com.sw2.comercial.service.instagram;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/loginInstagram")
public class InstagramServlet extends HttpServlet {

	private static final long serialVersionUID = 6117340243851313406L;
	
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String code = request.getParameter("code");
		
		if (code == null || code.equals("")) {
			throw new RuntimeException("Não foi possível recuperar o parâmetro code");
		}
		
		LoginInstagram loginInsta = new LoginInstagram();
		String username = loginInsta.obterUsuarioInstagram(code);
		
		request.getSession().setAttribute("username", username);
		
		response.sendRedirect(request.getContextPath() + "/instagramLogin.jsp");
		
	}

}