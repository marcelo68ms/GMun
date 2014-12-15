/*package br.com.sw2.comercial.service.twitter;

import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.auth.AccessToken;
import twitter4j.auth.RequestToken;

public class LoginTwitter {
	
	private static final String CONSUMER_KEY = "XccmMTfnJx7u1iDn33MzzT13t";
	private static final String CONSUMER_SECRET = "uxOstUFk1BKdz6Fj3YYPThPqwBG1biFsUj5wQxc2xjEKfaIL2s";
	private static final String TWITTER_URL = "https://api.twitter.com/oauth/authenticate?oauth_token=";
	
	public static void main(String args[]) throws Exception{
	    Twitter twitter = TwitterFactory.getSingleton();
        twitter.setOAuthConsumer(CONSUMER_KEY, CONSUMER_SECRET);
	    RequestToken requestToken = twitter.getOAuthRequestToken();
	    AccessToken accessToken = null;
	    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	    while (null == accessToken) {
	      System.out.println("Open the following URL and grant access to your account:");
	      System.out.println(requestToken.getAuthorizationURL());
	      System.out.print("Enter the PIN(if aviailable) or just hit enter.[PIN]:");
	      String pin = br.readLine();
	      try{
	         if(pin.length() > 0){
	           accessToken = twitter.getOAuthAccessToken(requestToken, pin);
	         }else{
	           accessToken = twitter.getOAuthAccessToken();
	         }
	      } catch (TwitterException te) {
	        if(401 == te.getStatusCode()){
	          System.out.println("Unable to get the access token.");
	        }else{
	          te.printStackTrace();
	        }
	      }
	    }
	    System.exit(0);
	  }
	
}
*/