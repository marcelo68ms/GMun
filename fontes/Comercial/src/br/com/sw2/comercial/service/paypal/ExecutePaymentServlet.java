package br.com.sw2.comercial.service.paypal;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;

import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

@WebServlet("/executePayment")
public class ExecutePaymentServlet extends HttpServlet {

	private static final long serialVersionUID = -8952495928278458902L;
	
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String payerId = (String) request.getParameter("PayerID");
		String paymentId = (String) request.getParameter("paymentId");
		String result = postRequest(paymentId, payerId);
		
		JsonParser parser = new JsonParser();
		JsonElement json = (JsonElement) parser.parse(result);
		String nome = json.getAsJsonObject().get("payer").getAsJsonObject()
				.get("payer_info").getAsJsonObject()
				.get("first_name").getAsString();
		
		ServletOutputStream out = response.getOutputStream();
		out.println("<h1>Olá " + nome  + ",</h1>");
		out.print("<p>Seu pagamento foi efetuado com sucesso!");
	}
	
	private String postRequest(String paymentId, String payerId) {
		StringBuffer url = new StringBuffer(Utils.SANDBOX_URL).append(Utils.PAYMENT_URL)
				.append("/").append(paymentId).append("/execute");

		HttpPost post = new HttpPost(url.toString());

		// add request header
		post.addHeader(Utils.AUTHORIZATION, Utils.TOKEN_TYPE + SimplePaymentService.getToken());
		post.addHeader(Utils.CONTENT_TYPE, Utils.APPLICATION_JSON);

		StringBuffer urlParameters = new StringBuffer("{\"payer_id\":\"").append(payerId).append("\"}");
		
		try {
			post.setEntity(new StringEntity(urlParameters.toString()));
		} catch (UnsupportedEncodingException e1) {
			e1.printStackTrace();
		}

		HttpClient client = new DefaultHttpClient();
		StringBuffer result = new StringBuffer();

		try {
			HttpResponse response = client.execute(post);

			System.out.println("Response Code : "
					+ response.getStatusLine().getStatusCode());

			BufferedReader rd = new BufferedReader(new InputStreamReader(
					response.getEntity().getContent()));

			String line = "";
			while ((line = rd.readLine()) != null) {
				result.append(line);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		System.out.println(result.toString());
		return result.toString();
	}

}
