package br.com.sw2.comercial.service.flickr;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.StringReader;
import java.io.UnsupportedEncodingException;
import java.util.Map.Entry;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.apache.commons.io.FileUtils;
import org.scribe.model.OAuthRequest;
import org.scribe.model.Response;
import org.scribe.model.Verb;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Text;
import org.xml.sax.InputSource;

public class UploadUtils {

	private static final String CONTENT_DISPOSITION = "Content-Disposition: form-data; name=\"";
	private static final String FINAL_BOUNDARY = "\r\n-----------------------------7d273f7a0d3\r\n";
	private static final String UPLOAD_URL = "https://up.flickr.com/services/upload/";
	private static final String CHARSET_NAME = "UTF-8";
	public static final String EDIT_MEDIA_URL = "https://www.flickr.com/photos/upload/edit/?ids=";
	private static final String MULTIPART_HEADER = "multipart/form-data; boundary=---------------------------7d273f7a0d3";
	private static final String CONTENT_TYPE = "Content-Type";

	public String upload(String filename, String mimeType) throws IOException, UnsupportedEncodingException {
		OAuthRequest oauthRequest = new OAuthRequest(Verb.POST, UploadUtils.UPLOAD_URL);
		LoginFlickr.getService().signRequest(LoginFlickr.getAccessToken(), oauthRequest);
		
		oauthRequest.addHeader(CONTENT_TYPE, MULTIPART_HEADER);
		
		oauthRequest.addPayload(createPayload(oauthRequest, filename, mimeType));
		
		Response oauthResponse = oauthRequest.send();
		System.out.println(oauthResponse.getBody());
		
		String strXml = oauthResponse.getBody().trim();
		if (strXml.startsWith("oauth_problem=")) {
            throw new IOException(strXml);
        }
		
		DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newInstance();
        String photoId = null;
		try {
			DocumentBuilder builder = builderFactory.newDocumentBuilder();
			Document document = builder.parse(new InputSource(new StringReader(strXml)));
			photoId = parse(document);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return photoId;
	}


	private byte[] createPayload(OAuthRequest oauthRequest, String filename, String mimeType) throws IOException,
			UnsupportedEncodingException {
		ByteArrayOutputStream buffer = new ByteArrayOutputStream();
		buffer.write(("-----------------------------7d273f7a0d3\r\n").getBytes(CHARSET_NAME));
		File file = new File(filename);
		String name = file.getName();
		byte[] photo = FileUtils.readFileToByteArray(file);
		
		StringBuffer line1 = new StringBuffer(CONTENT_DISPOSITION).append("photo\"; filename=\"")
				.append(name).append("\";\r\n");
		StringBuffer line2 = new StringBuffer(CONTENT_TYPE).append(": ").append(mimeType).append("\r\n\r\n");
				
		buffer.write((line1.toString()).getBytes(CHARSET_NAME));
		buffer.write((line2.toString()).getBytes(CHARSET_NAME));
		buffer.write((byte[]) photo);
		buffer.write(FINAL_BOUNDARY.getBytes(CHARSET_NAME));

		for (Entry<String, String> entry : oauthRequest.getOauthParameters().entrySet()) {
			writeParam(entry.getKey(), entry.getValue(), buffer);
		}

		byte[] payload = buffer.toByteArray();
		return payload;
	}

	private void writeParam(String name, String value,
			ByteArrayOutputStream buffer) throws UnsupportedEncodingException,
			IOException {
		
		StringBuffer line = new StringBuffer(CONTENT_DISPOSITION).append(name).append("\"\r\n\r\n");
		
		buffer.write((line.toString()).getBytes(CHARSET_NAME));
		buffer.write(((String) value).getBytes(CHARSET_NAME));
		buffer.write(FINAL_BOUNDARY.getBytes(CHARSET_NAME));
	}

	private String parse(Document document) throws Exception {
		Element responsePayLoad = document.getDocumentElement();
		String status = responsePayLoad.getAttribute("stat");
		String photoId;
		if ("ok".equals(status)) {
			Element photoIdElement = (Element) responsePayLoad.getElementsByTagName("photoid").item(0);
			if (photoIdElement != null) {
				photoId = ((Text) photoIdElement.getFirstChild()).getData();
			} else {
				photoId = null;
			}
		} else {
			Element errElement = (Element) responsePayLoad.getElementsByTagName("err").item(0);
			String errorMessage = errElement.getAttribute("msg");
			throw new Exception(errorMessage);
		}
		return photoId;
	}
}
