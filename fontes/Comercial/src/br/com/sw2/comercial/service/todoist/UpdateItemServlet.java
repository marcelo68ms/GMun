package br.com.sw2.comercial.service.todoist;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.sw2.comercial.service.Utils;

@WebServlet("/updateItem")
public class UpdateItemServlet extends HttpServlet {

	private static final long serialVersionUID = -5268724148242609139L;
	private static final String URL = "https://api.todoist.com/API/updateItem";

	@Override
	protected void service(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String token = (String) request.getSession().getAttribute("token");
		String itemId = (String) request.getSession().getAttribute("itemId");

		StringBuffer urlParameters = new StringBuffer("id=").append(itemId)
				.append("&token=").append(token).append("&date_string=today");

		String post = Utils.sendPost(URL, urlParameters.toString());

		if (!post.equals("ERROR_ITEM_NOT_FOUND")) {
			request.getSession().setAttribute("updated", true);
		}

		response.sendRedirect(request.getContextPath() + "/todoistActions.jsp");
	}

}
