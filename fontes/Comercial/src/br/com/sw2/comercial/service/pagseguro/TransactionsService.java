package br.com.sw2.comercial.service.pagseguro;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

public class TransactionsService {

	private static final String TRANSACTION_URL = "/v2/transactions";

	public static void main(String[] args) {
		StringBuffer url = new StringBuffer(Constants.WS_SANDBOX_URL)
				.append(TRANSACTION_URL).append("?initialDate=2015-01-28T00:00")
				.append("&finalDate=2015-01-29T15:41&email=").append(Constants.EMAIL)
				.append("&token=").append(Constants.TOKEN_SANDBOX);

		HttpGet get = new HttpGet(url.toString());

		try {

			HttpClient client = new DefaultHttpClient();
			HttpResponse response = client.execute(get);
			System.out.println("Response Code : "
					+ response.getStatusLine().getStatusCode());
			
			BufferedReader rd = new BufferedReader(new InputStreamReader(
					response.getEntity().getContent()));

			String line = "";
			StringBuffer result = new StringBuffer();
			while ((line = rd.readLine()) != null) {
				result.append(line);
			}

			System.out.println(result);
		} catch (IllegalStateException | IOException e) {
			e.printStackTrace();
		}


	}

}
