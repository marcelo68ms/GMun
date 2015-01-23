package br.com.sw2.comercial.service.paypal;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import com.paypal.api.payments.Address;
import com.paypal.api.payments.Amount;
import com.paypal.api.payments.Links;
import com.paypal.api.payments.Payer;
import com.paypal.api.payments.Payment;
import com.paypal.api.payments.RedirectUrls;
import com.paypal.api.payments.Transaction;
import com.paypal.base.rest.OAuthTokenCredential;
import com.paypal.base.rest.PayPalRESTException;

public class PaypalRestApiSDK {
	
	private static final String RETURN_URL = "http://localhost:8080/Comercial/executeRestApiSDKPayment";
	
	public String getPaymentApprovaltURL() {
		Payment payment = createPayment();
		String href = null;
		for (Links link : payment.getLinks()) {
			if (link.getRel().equals("approval_url")) {
				href = link.getHref();
				break;
			}
		}
		return href;
	}

	private Payment createPayment() {
		Address billingAddress = new Address();
		billingAddress.setLine1("Av. Brasil, 103");
		billingAddress.setCity("Campinas");
		billingAddress.setState("SP");
		billingAddress.setCountryCode("BR");
		billingAddress.setPostalCode("13100-000");

		/*CreditCard creditCard = new CreditCard();
		creditCard.setNumber("4417119669820331");
		creditCard.setType("visa");
		creditCard.setExpireMonth(11);
		creditCard.setExpireYear(2018);
		creditCard.setCvv2(874);
		creditCard.setFirstName("João");
		creditCard.setLastName("Silva");
		creditCard.setBillingAddress(billingAddress);*/

		Amount amount = new Amount();
		amount.setTotal("78.47");
		amount.setCurrency("BRL");

		Transaction transaction = new Transaction();
		transaction.setAmount(amount);
		transaction.setDescription("Pagamento para OdonTO-DO.");

		List<Transaction> transactions = new ArrayList<Transaction>();
		transactions.add(transaction);

		/*FundingInstrument fundingInstrument = new FundingInstrument();
		fundingInstrument.setCreditCard(creditCard);

		List<FundingInstrument> fundingInstruments = new ArrayList<FundingInstrument>();
		fundingInstruments.add(fundingInstrument);*/

		Payer payer = new Payer();
		payer.setPaymentMethod("paypal");
		/*payer.setFundingInstruments(fundingInstruments);
		payer.setPaymentMethod("credit_card"); */
		
		RedirectUrls redirectUrls = new RedirectUrls();
		redirectUrls.setReturnUrl(RETURN_URL);
		redirectUrls.setCancelUrl(Utils.CANCEL_URL);
		
		Payment payment = new Payment();
		payment.setIntent("sale");
		payment.setPayer(payer);
		payment.setTransactions(transactions);
		payment.setRedirectUrls(redirectUrls);

		Payment createdPayment = null;
		try {
			// Há duas maneiras de recuperar o access_token, uma é esta e a outra é mostrada no método getAccessToken
			OAuthTokenCredential tokenCredential = Payment.initConfig(new File(Utils.PROPERTIES_PATH));
			createdPayment = payment.create(tokenCredential.getAccessToken());
		} catch (PayPalRESTException e) {
			e.printStackTrace();
		}
		return createdPayment;
	}

}
