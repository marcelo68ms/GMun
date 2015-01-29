package br.com.sw2.comercial.service.pagseguro;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class PaymentService {

	private static final String CHECKOUT_URL = "/v2/checkout";

	public String getPaymentApprovaltURL() {
		String code = getPaymentCode();
		StringBuffer url = new StringBuffer(Constants.SANDBOX_URL).append(CHECKOUT_URL)
				.append("/payment.html?code=").append(code);
		return url.toString();
	}

	private String getPaymentCode() {
		StringBuffer url = new StringBuffer(Constants.WS_SANDBOX_URL)
				.append(CHECKOUT_URL);

		HttpPost post = new HttpPost(url.toString());

		// add request header
		post.addHeader("Content-Type",
				"application/x-www-form-urlencoded; charset=ISO-8859-1");

		List<NameValuePair> urlParameters = new ArrayList<NameValuePair>();
		urlParameters.add(new BasicNameValuePair("email", Constants.EMAIL));
		urlParameters.add(new BasicNameValuePair("token", Constants.TOKEN_SANDBOX));
		urlParameters.add(new BasicNameValuePair("currency", "BRL"));
		urlParameters.add(new BasicNameValuePair("itemId1", "0001"));
		urlParameters.add(new BasicNameValuePair("itemDescription1",
				"Prótese Dentária"));
		urlParameters.add(new BasicNameValuePair("itemAmount1", "210.00"));
		urlParameters.add(new BasicNameValuePair("itemQuantity1", "2"));

		InputStream is = null;
		try {
			post.setEntity(new UrlEncodedFormEntity(urlParameters));

			HttpClient client = new DefaultHttpClient();
			HttpResponse response = client.execute(post);
			System.out.println("Response Code : "
					+ response.getStatusLine().getStatusCode());

			is = response.getEntity().getContent();
		} catch (IllegalStateException | IOException e) {
			e.printStackTrace();
		}

		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		String code = null;
		try {
			DocumentBuilder xmlParser = dbf.newDocumentBuilder();
			Document doc = xmlParser.parse(is);
			NodeList codeList = doc.getElementsByTagName("code");
			code = codeList.item(0).getFirstChild().getNodeValue();
		} catch (ParserConfigurationException | SAXException | IOException e) {
			e.printStackTrace();
		}
		return code;
	}

}
