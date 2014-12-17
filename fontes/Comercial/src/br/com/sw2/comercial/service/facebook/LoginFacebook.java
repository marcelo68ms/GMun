package br.com.sw2.comercial.service.facebook;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import br.com.sw2.comercial.service.Utils;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class LoginFacebook {

	private static final String client_secret = "cba88efa28c5eac0e4b92c1825d987b6";
	private static final String client_id = "877634312255548";
	private static final String redirect_uri = "http://localhost:8080/Comercial/loginFacebook";

	public FacebookUser obterUsuarioFacebook(String code) throws MalformedURLException,
			IOException {

		String retorno = Utils.readURL(new URL(this.getAuthURL(code)));

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
		JsonObject obj = (JsonObject)parser.parse(Utils.readURL(new URL(
				 "https://graph.facebook.com/me?access_token=" + accessToken)));
		
		FacebookUser usuarioFacebook = new FacebookUser(obj);
		System.out.println(usuarioFacebook.toString());
		return usuarioFacebook;

	}

	public String getLoginRedirectURL() {
		return "https://graph.facebook.com/oauth/authorize?client_id="
				+ client_id + "&display=page&redirect_uri=" + redirect_uri
				+ "&scope=email,user_birthday,user_hometown,user_location";
	}

	public String getAuthURL(String authCode) {
		return "https://graph.facebook.com/oauth/access_token?client_id="
				+ client_id + "&redirect_uri=" + redirect_uri
				+ "&client_secret=" + client_secret + "&code=" + authCode;
	}

}