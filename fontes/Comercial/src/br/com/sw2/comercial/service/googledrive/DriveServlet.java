package br.com.sw2.comercial.service.googledrive;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.api.services.oauth2.model.Userinfoplus;

@WebServlet("/loginDrive")
public class DriveServlet extends HttpServlet {
	
	private static final long serialVersionUID = -1521026415735414594L;

	@Override
	protected void service(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String code = request.getParameter("code");
		if (code == null || code.equals("")) {
			throw new RuntimeException(
					"Não foi possível recuperar o parâmetro code");
		}

		LoginDrive loginDrive = new LoginDrive();
		Userinfoplus userInfo = loginDrive.getUserInfo(code);
		
		request.getSession().setAttribute("user", userInfo);
		response.sendRedirect(request.getContextPath() + "/googledriveLogin.jsp");
		
	}


}
