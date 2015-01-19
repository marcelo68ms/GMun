package br.com.sw2.comercial.service.todoist;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.sw2.comercial.service.Utils;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

@WebServlet("/getItems")
public class GetItemsServlet extends HttpServlet {

	private static final long serialVersionUID = 3374762446452009422L;
	private static final String URL = "https://api.todoist.com/API/getUncompletedItems";
	
	@Override
	protected void service(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String token = (String) request.getSession().getAttribute("token");
		String projectId = (String) request.getSession().getAttribute("projectId");
		
		JsonParser parser = new JsonParser();
		StringBuffer url = new StringBuffer(URL).append("?token=").append(token)
				.append("&project_id=").append(projectId);
		
		JsonArray items = (JsonArray) parser.parse(Utils.readURL(url.toString()));
		
		ServletOutputStream out = response.getOutputStream();
		out.println("<h1>Tarefas na Caixa de entrada</h1>");
		for (JsonElement jsonElement : items) {
			String item = jsonElement.getAsJsonObject().get("content").getAsString();
			out.println("<div>"+item+"<div>");
		}
		out.println("<input type=\"button\" onClick=\"history.go(-1)\" value=\"Voltar\">");
		
	}

}
