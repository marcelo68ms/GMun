package br.com.sw2.comercial.service.googledrive;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.api.services.drive.Drive;
import com.google.api.services.drive.model.File;
import com.google.api.services.drive.model.ParentList;
import com.google.api.services.drive.model.ParentReference;

@WebServlet("/changefolder")
public class ChangeFolderServlet extends HttpServlet {

	private static final long serialVersionUID = -1381218849227184942L;
	
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		LoginDrive loginDrive = new LoginDrive();
		Drive service = loginDrive.getDriveService();
		
		String folderId = createFolder(service);
		
		if (folderId != null) {
			String fileId = (String) request.getSession().getAttribute("fileId");
			ParentReference moveFile = moveFile(service, folderId, fileId);
			if (moveFile != null) {
				request.getSession().setAttribute("moved", true);
			}
		}

		response.sendRedirect(request.getContextPath() + "/googledriveLogin.jsp");
		
	}
	
	private static String createFolder(Drive service) {
		// File's metadata.
		File folder = new File();
		folder.setTitle("Teste");
		folder.setMimeType("application/vnd.google-apps.folder");

		try {
			folder = service.files().insert(folder).execute();
			return folder.getId();
		} catch (IOException e) {
			System.out.println("An error occured: " + e);
			return null;
		}
	}

	private static ParentReference moveFile(Drive service, String folderId, String fileId) {
		ParentReference newParent = new ParentReference();
	    newParent.setId(folderId);
	    try {
	    	ParentList parents = service.parents().list(fileId).execute();

	        for (ParentReference parent : parents.getItems()) {
	        	service.parents().delete(fileId, parent.getId()).execute();
	        }
	    	
	    	return service.parents().insert(fileId, newParent).execute();
	    } catch (IOException e) {
	      System.out.println("An error occurred: " + e);
	      return null;		
	    }
	}
	
}
