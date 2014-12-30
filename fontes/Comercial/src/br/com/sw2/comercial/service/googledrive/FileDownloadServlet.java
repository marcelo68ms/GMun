package br.com.sw2.comercial.service.googledrive;

import java.io.IOException;
import java.io.InputStream;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.api.client.http.GenericUrl;
import com.google.api.client.http.HttpResponse;
import com.google.api.services.drive.Drive;
import com.google.api.services.drive.model.File;

@WebServlet("/filedownload")
public class FileDownloadServlet extends HttpServlet {

	private static final long serialVersionUID = 5134312292118210140L;

	@Override
	protected void service(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String fileId = (String) request.getSession().getAttribute("fileId");
		InputStream is = downloadFile(fileId);
	
        response.setContentType("text/plain");
        response.setHeader("Content-Disposition", "attachment; filename=\"TesteDrive.txt\"");
         
        ServletOutputStream os = response.getOutputStream();
        byte[] bufferData = new byte[1024];
        int read=0;
        while((read = is.read(bufferData))!= -1){
            os.write(bufferData, 0, read);
        }
        os.flush();
        os.close();
        is.close();
	}

	/**
	 * Download a file's content.
	 *
	 * @param service
	 *            Drive API service instance.
	 * @param file
	 *            Drive File instance.
	 * @return InputStream containing the file's content if successful,
	 *         {@code null} otherwise.
	 */
	private static InputStream downloadFile(String fileId) {
		LoginDrive loginDrive = new LoginDrive();
		Drive service = loginDrive.getDriveService();
		try {
			File file = service.files().get(fileId).execute();
			if (file.getDownloadUrl() != null && file.getDownloadUrl().length() > 0) {
				HttpResponse resp = service.getRequestFactory()
						.buildGetRequest(new GenericUrl(file.getDownloadUrl()))
						.execute();
				return resp.getContent();
			} else {
				// The file doesn't have any content stored on Drive.
				return null;
			}
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}

	}
}
