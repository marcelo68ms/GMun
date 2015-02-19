package br.com.sw2.comercial.service.flickr;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/uploadPhoto")
public class UploadPhotoServlet extends HttpServlet {

	
	private static final long serialVersionUID = 1269128792370663769L;
	private static final String FILENAME = "d:\\SW2\\OdonTO-DO\\fontes\\Comercial\\src\\main\\resources\\Boca.jpg";
	

	@Override
	protected void service(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		UploadUtils utils = new UploadUtils();
		String photoId = utils.upload(FILENAME, "image/jpeg");
        
		response.sendRedirect(UploadUtils.EDIT_MEDIA_URL + photoId);
	}

}
