package br.com.sw2.comercial.service.todoist;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.sw2.comercial.service.Utils;

@WebServlet("/completeItems")
public class CompleteItemsServlet extends HttpServlet {

	private static final long serialVersionUID = -8026343602826702948L;
	private static final String URL = "https://api.todoist.com/API/completeItems";

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
			request.getSession().setAttribute("completed", true);
		}

		response.sendRedirect(request.getContextPath() + "/todoistActions.jsp");
	}

}
