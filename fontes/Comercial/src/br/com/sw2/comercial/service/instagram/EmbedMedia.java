package br.com.sw2.comercial.service.instagram;

import java.io.IOException;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import br.com.sw2.comercial.service.Utils;

public class EmbedMedia {
	
	public String getHtml() {
		String html = null;
		// Escolhemos um short code qualquer, apenas para efeito de teste
		String url = "http://api.instagram.com/oembed?url=http://instagr.am/p/ycyXs4BQZd/&maxwidth=320";
		try {
			String retorno = Utils.readURL(url);
			
			JsonParser parser = new JsonParser();
			JsonObject json = (JsonObject)parser.parse(retorno);
			html = json.get("html").getAsString();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return html;
		
	}

}
