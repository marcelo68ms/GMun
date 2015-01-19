package br.com.sw2.comercial.service.todoist;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.sw2.comercial.service.Utils;

@WebServlet("/deleteItems")
public class DeleteItemsServlet extends HttpServlet {

	private static final long serialVersionUID = 5881183978243853757L;
	private static final String URL = "https://api.todoist.com/API/deleteItems";

	@Override
	protected void service(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String token = (String) request.getSession().getAttribute("token");
		String itemId = (String) request.getSession().getAttribute("itemId");
		
		String items = "[" + itemId + "]";

		StringBuffer urlParameters = new StringBuffer("ids=").append(items)
				.append("&token=").append(token);

		String post = Utils.sendPost(URL, urlParameters.toString());

		if (post.equals("\"ok\"")) {
			request.getSession().setAttribute("removed", true);
		}

		response.sendRedirect(request.getContextPath() + "/todoistActions.jsp");	
	}

}
