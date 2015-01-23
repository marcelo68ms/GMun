package br.com.sw2.comercial.service.paypal;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.paypal.api.payments.Payment;
import com.paypal.api.payments.PaymentExecution;
import com.paypal.base.rest.OAuthTokenCredential;
import com.paypal.base.rest.PayPalRESTException;

@WebServlet("/executeRestApiSDKPayment")
public class PaypalRestApiSDKServlet extends HttpServlet {

	private static final long serialVersionUID = -3862675869562559116L;
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String payerId = (String) request.getParameter(Utils.PAYER_ID);
		String paymentId = (String) request.getParameter(Utils.PAYMENT_ID);
		Payment payment = executePayment(paymentId, payerId);
		
		ServletOutputStream out = response.getOutputStream();
		if (payment != null) {
			String name = payment.getPayer().getPayerInfo().getFirstName();
			String date = payment.getUpdateTime();
			
			out.println("<h1>Olá " + name  + ",</h1>");
			out.print("<p>Seu pagamento foi efetuado com sucesso em " + date);
		} else {
			out.println("<h1>Não foi possível efetuar o pagamento...</h1>");
		}
	}
	
	private Payment executePayment(String paymentId, String payerId) {
		String accessToken = getAccessToken();
		Payment newPayment = null;
		try {
			Payment payment = Payment.get(accessToken, paymentId);

			PaymentExecution paymentExecution = new PaymentExecution();
			paymentExecution.setPayerId(payerId);

			newPayment = payment.execute(accessToken, paymentExecution);
		} catch (PayPalRESTException e) {
			e.printStackTrace();
		}
		return newPayment;
	}
	
	private String getAccessToken() {
		OAuthTokenCredential tokenCredential = new OAuthTokenCredential(Utils.CLIENT_ID, Utils.SECRET);
		
		String accessToken = null;
		try {
			accessToken = tokenCredential.getAccessToken();
		} catch (PayPalRESTException e) {
			e.printStackTrace();
		}
		return accessToken;
	}

}
