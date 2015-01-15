package br.com.sw2.comercial.service.facebook;

import java.io.IOException;
import java.net.MalformedURLException;

import br.com.sw2.comercial.service.Utils;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class LoginFacebook {

	private static final String client_secret = "&client_secret=cba88efa28c5eac0e4b92c1825d987b6";
	private static final String client_id = "?client_id=877634312255548";
	private static final String redirect_uri = "&redirect_uri=http://localhost:8080/Comercial/loginFacebook";

	public FacebookUser obterUsuarioFacebook(String code) throws MalformedURLException,
			IOException {

		String retorno = Utils.readURL(this.getAuthURL(code));

		String accessToken = null;
		String[] pairs = retorno.split("&");
		for (String pair : pairs) {
			String[] kv = pair.split("=");
			if (kv.length != 2) {
				throw new RuntimeException("Resposta auth inesperada.");
			} else {
				if (kv[0].equals("access_token")) {
					accessToken = kv[1];
				}
			}
		}

		JsonParser parser = new JsonParser();
		StringBuffer url = new StringBuffer("https://graph.facebook.com/me?access_token=")
				.append(accessToken);
		
		JsonObject obj = (JsonObject)parser.parse(Utils.readURL(url.toString()));
		
		FacebookUser usuarioFacebook = new FacebookUser(obj);
		System.out.println(usuarioFacebook.toString());
		return usuarioFacebook;

	}

	public String getLoginRedirectURL() {
		StringBuffer url = new StringBuffer("https://graph.facebook.com/oauth/authorize")
				.append(client_id).append("&display=page").append(redirect_uri)
				.append("&scope=email,user_birthday,user_hometown,user_location");
		return url.toString();
	}

	public String getAuthURL(String authCode) {
		StringBuffer url = new StringBuffer("https://graph.facebook.com/oauth/access_token")
				.append(client_id).append(redirect_uri).append(client_secret)
				.append("&code=").append(authCode);
		return url.toString();
	}

}