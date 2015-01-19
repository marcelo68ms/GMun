package br.com.sw2.comercial.service.todoist;

import java.io.IOException;
import java.util.Calendar;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import br.com.sw2.comercial.service.Utils;

@WebServlet("/addItem")
public class AddItemServlet extends HttpServlet {

	private static final long serialVersionUID = 139747408441481318L;
	private static final String URL = "https://api.todoist.com/API/addItem";
	
	@Override
	protected void service(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String token = (String) request.getSession().getAttribute("token");
		String content = "Tarefa criada em " + Calendar.getInstance().getTime();
		
		StringBuffer urlParameters = new StringBuffer("content=").append(content).append("&token=").append(token);
		
		JsonParser parser = new JsonParser();
		String post = Utils.sendPost(URL, urlParameters.toString());
		JsonObject retorno = (JsonObject) parser.parse(post);
		
		String itemId = retorno.get("id").getAsString();
		String projectId = retorno.get("project_id").getAsString();
		request.getSession().setAttribute("itemId", itemId);
		request.getSession().setAttribute("projectId", projectId);
		
		response.sendRedirect(request.getContextPath() + "/todoistActions.jsp");
		
	}

}
