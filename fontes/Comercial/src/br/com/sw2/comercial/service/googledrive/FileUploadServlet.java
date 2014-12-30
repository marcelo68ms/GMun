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

@WebServlet("/fileupload")
public class FileUploadServlet extends HttpServlet {

	private static final long serialVersionUID = -8491680173324176286L;

	private static final String PATH = "d:\\SW2\\OdonTO-DO\\fontes\\Comercial\\src\\main\\resources\\";
	private static final String FILENAME = "TesteDrive.txt";
	private static final String MIMETYPE = "text/plain";

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		File file = this.insertFile();
		if (file != null) {
			request.getSession().setAttribute("fileId", file.getId());
		}

		response.sendRedirect(request.getContextPath() + "/googledriveLogin.jsp");
		
	}
	
	/**
	 * Insert new file.
	 */
	private File insertFile() {
		// File's metadata.
		File file = new File();
		file.setTitle(FILENAME);
		file.setDescription("Arquivo de teste");
		file.setMimeType(MIMETYPE);

		// File's content.
		java.io.File fileContent = new java.io.File(PATH + FILENAME);
		FileContent mediaContent = new FileContent(MIMETYPE, fileContent);
		LoginDrive loginDrive = new LoginDrive();
		Drive service = loginDrive.getDriveService();
		try {
			file = service.files().insert(file, mediaContent).execute();
			return file;
		} catch (IOException e) {
			System.out.println("An error occured: " + e);
			return null;
		}
	}

}
