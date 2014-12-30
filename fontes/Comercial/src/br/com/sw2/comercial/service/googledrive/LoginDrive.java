package br.com.sw2.comercial.service.googledrive;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
import com.google.api.client.googleapis.auth.oauth2.GoogleTokenResponse;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.services.drive.Drive;
import com.google.api.services.oauth2.Oauth2;
import com.google.api.services.oauth2.model.Userinfoplus;

public class LoginDrive {

	private static final String CLIENT_ID = "776101340018-ha404og3njrqev5dmv3stltnbn1q5ag0.apps.googleusercontent.com";
	private static final String CLIENT_SECRET = "1pDVjLp3ZQY7sXChPt_qqRJr";
	private static final String REDIRECT_URI = "http://localhost:8080/Comercial/loginDrive";
	private static final List<String> SCOPES = Arrays.asList(
			"https://www.googleapis.com/auth/drive",
			"https://www.googleapis.com/auth/drive.appdata",
			"https://www.googleapis.com/auth/drive.apps.readonly",
			"https://www.googleapis.com/auth/drive.file", "email", "profile");
	private static HttpTransport httpTransport = new NetHttpTransport();
	private static JacksonFactory jsonFactory = new JacksonFactory();
	private static GoogleAuthorizationCodeFlow flow = null;
	private static Credential credentials = null;
	
	
	
	/**
	 * Send a request to the UserInfo API to retrieve the user's information.
	 *
	 * @param credentials
	 *            OAuth 2.0 credentials to authorize the request.
	 * @return User's information.
	 */
	public Userinfoplus getUserInfo(String authorizationCode) {
		credentials = this.exchangeCode(authorizationCode);
		Oauth2 userInfoService = new Oauth2.Builder(httpTransport,
				jsonFactory, credentials).build();
		Userinfoplus userInfo = null;
		try {
			userInfo = userInfoService.userinfo().get().execute();
		} catch (IOException e) {
			System.err.println("An error occurred: " + e);
		}
		return userInfo;
	}

	/**
	 * Retrieve the authorization URL.
	 *
	 * @return Authorization URL to redirect the user to.
	 */
	public String getAuthorizationUrl() {
		return getFlow().newAuthorizationUrl().setRedirectUri(REDIRECT_URI).build();
	}
	
	public Drive getDriveService() {
		return new Drive.Builder(httpTransport, jsonFactory, credentials).build();
	}

	/**
	 * Build an authorization flow and store it as a static class attribute.
	 *
	 * @return GoogleAuthorizationCodeFlow instance.
	 * @throws IOException
	 *             Unable to load client_secrets.json.
	 */
	private static GoogleAuthorizationCodeFlow getFlow() {
		if (flow == null) {
			flow = new GoogleAuthorizationCodeFlow.Builder(httpTransport,
					jsonFactory, CLIENT_ID, CLIENT_SECRET, SCOPES)
					.setAccessType("offline").setApprovalPrompt("force")
					.build();
		}
		return flow;
	}

	/**
	 * Exchange an authorization code for OAuth 2.0 credentials.
	 *
	 * @param authorizationCode
	 *            Authorization code to exchange for OAuth 2.0 credentials.
	 * @return OAuth 2.0 credentials.
	 */
	private Credential exchangeCode(String authorizationCode) {
		try {
			GoogleAuthorizationCodeFlow flow = getFlow();
			GoogleTokenResponse response = flow
					.newTokenRequest(authorizationCode)
					.setRedirectUri(REDIRECT_URI).execute();
			return flow.createAndStoreCredential(response, null);
		} catch (IOException e) {
			System.err.println("An error occurred: " + e);
			return null;
		}
	}

}
