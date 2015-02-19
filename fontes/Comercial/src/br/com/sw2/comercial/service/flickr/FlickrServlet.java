package br.com.sw2.comercial.service.flickr;

import java.io.IOException;
import java.net.URLDecoder;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/loginFlickr")
public class FlickrServlet extends HttpServlet {

	private static final long serialVersionUID = -8944735913393799534L;
	
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String verifier = request.getParameter("oauth_verifier");
		
		if (verifier == null || verifier.equals("")) {
			throw new RuntimeException("Não foi possível recuperar o parâmetro verifier");
		}
		
		LoginFlickr flickr = new LoginFlickr();
		String rawResponse = flickr.obterUsuarioFlickr(verifier);
		
		// rawRespose = fullname=xxxx&oauth_token=xxxx&oauth_token_secret=xxxx&user_nsid=xxx&username=xxx
        String[] splitResponse = rawResponse.split("&");
        
        String[] splitFullname = splitResponse[0].split("=");
        String fullname = splitFullname[1];
        
        String[] splitNSID = splitResponse[3].split("=");
        String nsid = splitNSID[1];
        
        System.out.println("nsid: " + nsid);
		
		request.getSession().setAttribute("username", URLDecoder.decode(fullname, "UTF-8"));
		request.getSession().setAttribute("nsid", URLDecoder.decode(nsid, "UTF-8"));
		response.sendRedirect(request.getContextPath() + "/flickrLogin.jsp");
	}

}
