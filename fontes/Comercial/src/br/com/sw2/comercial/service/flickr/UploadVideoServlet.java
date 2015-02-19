package br.com.sw2.comercial.service.flickr;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/uploadVideo")
public class UploadVideoServlet extends HttpServlet {

	
	private static final long serialVersionUID = 1269128792370663769L;
	private static final String FILENAME = "d:\\SW2\\OdonTO-DO\\fontes\\Comercial\\src\\main\\resources\\Melt.mpg";
	

	@Override
	protected void service(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		UploadUtils utils = new UploadUtils();
		String videoId = utils.upload(FILENAME, "video/mpeg");
        
		response.sendRedirect(UploadUtils.EDIT_MEDIA_URL + videoId);
	}

}
