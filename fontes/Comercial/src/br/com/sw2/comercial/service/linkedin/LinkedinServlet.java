package br.com.sw2.comercial.service.linkedin;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/loginLinkedin")
public class LinkedinServlet extends HttpServlet {

	private static final long serialVersionUID = -6305096879966810658L;
	
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String code = request.getParameter("code");
		if (code == null || code.equals("")) {
			throw new RuntimeException("Não foi possível recuperar o parâmetro code");
		}
		
		LoginLinkedin loginlinkedin = new LoginLinkedin();
		String xml = loginlinkedin.obterTokenAcesso(code);
		
		ServletOutputStream out = response.getOutputStream();
		out.println(xml);

	}


}
