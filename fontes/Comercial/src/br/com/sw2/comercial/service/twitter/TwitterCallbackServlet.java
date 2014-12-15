package br.com.sw2.comercial.service.twitter;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.auth.AccessToken;
import twitter4j.auth.RequestToken;

@WebServlet("/twittercallback")
public class TwitterCallbackServlet extends HttpServlet {
    private static final long serialVersionUID = 2132731135996613711L;
    
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Twitter twitter = (Twitter) request.getSession().getAttribute("twitter");
        RequestToken requestToken = (RequestToken) request.getSession().getAttribute("requestToken");
        String verifier = request.getParameter("oauth_verifier");
        try {
            AccessToken accessToken = twitter.getOAuthAccessToken(requestToken, verifier);
            System.out.println(accessToken);
            request.getSession().removeAttribute("requestToken");
        } catch (TwitterException e) {
            throw new ServletException(e);
        }
        
        // Recuperar timeline
        List<Status> statuses;
		try {
			statuses = twitter.getHomeTimeline();
				System.out.println("Mostrando timeline...");
	        for (Status status : statuses) {
	            System.out.println(status.getUser().getName() + ":" +
	                               status.getText());
	       		}
	        } catch (TwitterException e) {
			e.printStackTrace();
		}
        
        response.sendRedirect(request.getContextPath() + "/twitterLogin.jsp");
    }

}
