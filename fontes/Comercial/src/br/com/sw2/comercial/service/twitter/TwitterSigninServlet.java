package br.com.sw2.comercial.service.twitter;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.auth.RequestToken;
import twitter4j.conf.ConfigurationBuilder;

@WebServlet("/twittersignin")
public class TwitterSigninServlet extends HttpServlet {

	private static final long serialVersionUID = -8628835480274398630L;
	private static final String CONSUMER_KEY = "XccmMTfnJx7u1iDn33MzzT13t";
	private static final String CONSUMER_SECRET = "uxOstUFk1BKdz6Fj3YYPThPqwBG1biFsUj5wQxc2xjEKfaIL2s";
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ConfigurationBuilder builder = new ConfigurationBuilder();
		builder.setDebugEnabled(true).setOAuthConsumerKey(CONSUMER_KEY).setOAuthConsumerSecret(CONSUMER_SECRET);
        Twitter twitter = new TwitterFactory(builder.build()).getInstance();
        request.getSession().setAttribute("twitter", twitter);
        try {
            StringBuffer callbackURL = request.getRequestURL();
            int index = callbackURL.lastIndexOf("/");
            callbackURL.replace(index, callbackURL.length(), "").append("/twittercallback");
            
            RequestToken requestToken = twitter.getOAuthRequestToken(callbackURL.toString());
            request.getSession().setAttribute("requestToken", requestToken);
            response.sendRedirect(requestToken.getAuthenticationURL());

        } catch (TwitterException e) {
            throw new ServletException(e);
        }
	}

}
