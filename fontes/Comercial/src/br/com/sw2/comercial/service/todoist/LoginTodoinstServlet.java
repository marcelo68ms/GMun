package br.com.sw2.comercial.service.todoist;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.sw2.comercial.service.Utils;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonSyntaxException;

@WebServlet("/loginTodoist")
public class LoginTodoinstServlet extends HttpServlet {

	private static final long serialVersionUID = 1715405206262892096L;
	private static final String LOGIN_URL = "https://api.todoist.com/API/loginWithGoogle";
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String email = req.getParameter("email");
		String accessToken = req.getParameter("accessToken");
		
		JsonParser parser = new JsonParser();
		StringBuffer url = new StringBuffer(LOGIN_URL).append("?email=")
				.append(email).append("&oauth2_token=").append(accessToken);
		try {
			JsonObject json = (JsonObject) parser.parse(Utils.readURL(url.toString()));
			
			String user = json.get("full_name").getAsString();
			String token = json.get("token").getAsString();
			req.getSession().setAttribute("user", user);
			req.getSession().setAttribute("token", token);
		} catch (JsonSyntaxException | IOException e) {
			e.printStackTrace();
		}
		
		resp.sendRedirect(req.getContextPath() + "/todoistActions.jsp");
	}

}
