package br.com.sw2.comercial.service.paypal;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

@WebServlet("/loginPaypal")
public class PayPalServlet extends HttpServlet {

	private static final long serialVersionUID = -6352487147945900573L;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String code = req.getParameter("code");
		ServletOutputStream out = resp.getOutputStream();
		if(code != null) {
			out.println("<h1>Login efetuado com sucesso!</h1>");
			
			LoginPayPal login = new LoginPayPal();
			String paypalUser = login.getPaypalUser(code);
			
			JsonParser parser = new JsonParser();
			JsonElement jsonElement = parser.parse(paypalUser);
			String name = jsonElement.getAsJsonObject().get("name").getAsString();
			String email = jsonElement.getAsJsonObject().get("email").getAsString();
			
			out.println("<div>Olá " + name + "</div>");
			out.println("<div>E-mail: " + email + "</div>");
			
		} else {
			out.println("<h1>Não foi possível efetuar o login...</h1>");
		}
	}

}
