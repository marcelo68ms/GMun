package br.com.sw2.comercial.service.todoist;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.JsonArray;
import com.google.gson.JsonParser;

import br.com.sw2.comercial.service.Utils;

@WebServlet("/moveItem")
public class MoveItemServlet extends HttpServlet {

	private static final long serialVersionUID = -5016130027041774547L;
	private static final String MOVE_URL = "https://api.todoist.com/API/moveItems";
	private static final String PROJECTS_URL = "https://api.todoist.com/API/getProjects";

	@Override
	protected void service(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// Primeiro é preciso recuperar os projetos existentes
		String token = (String) request.getSession().getAttribute("token");
		StringBuffer url = new StringBuffer(PROJECTS_URL).append("?token=").append(token);
		
		JsonParser parser = new JsonParser();
		JsonArray projects = (JsonArray) parser.parse(Utils.readURL(url.toString()));
		
		// Vamos colocar a tarefa no segundo projeto do array só para teste. 
		// O primeiro projeto é onde as tarefas são criadas
		String newProjectId = projects.get(1).getAsJsonObject().get("id").getAsString();
		
		String itemId = (String) request.getSession().getAttribute("itemId");
		String projectId = (String) request.getSession().getAttribute("projectId");
		
		String project_items =  "{\""+ projectId + "\":[\"" + itemId + "\"]}";
		
		StringBuffer urlParameters = new StringBuffer("project_items=").append(project_items)
				.append("&token=").append(token).append("&to_project=").append(newProjectId);

		Utils.sendPost(MOVE_URL, urlParameters.toString());

		request.getSession().setAttribute("moved", true);

		response.sendRedirect(request.getContextPath() + "/todoistActions.jsp");
	}

}
