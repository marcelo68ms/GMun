package br.com.sw2.comercial.service.googledrive;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.api.client.http.FileContent;
import com.google.api.services.drive.Drive;
import com.google.api.services.drive.model.File;

@WebServlet("/fileupdate")
public class FileUpdateServlet extends HttpServlet{

	private static final long serialVersionUID = 5134312292118210140L;
	
	private static final String FILENAME = "d:\\SW2\\OdonTO-DO\\fontes\\Comercial\\src\\main\\resources\\TesteDriveAlterado.txt";
	
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String fileId = (String) request.getSession().getAttribute("fileId");
		File file = alterarArquivo(fileId);
		if (file != null) {
			request.getSession().setAttribute("updated", true);
		}
		
		response.sendRedirect(request.getContextPath() + "/googledriveLogin.jsp");
	}
	
	private static File alterarArquivo(String fileId) {
		LoginDrive loginDrive = new LoginDrive();
		Drive service = loginDrive.getDriveService();
		try {
		      // First retrieve the file from the API.
		      File file = service.files().get(fileId).execute();
		      
		      // File's new content.
		      java.io.File fileContent = new java.io.File(FILENAME);
		      FileContent mediaContent = new FileContent("text/plain", fileContent);

		      // Send the request to the API.
		      File updatedFile = service.files().update(fileId, file, mediaContent).setConvert(true).execute();

		      return updatedFile;
		    } catch (IOException e) {
		      System.out.println("An error occurred: " + e);
		      return null;
		    }		
	}

}
