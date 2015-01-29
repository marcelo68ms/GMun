package br.com.sw2.comercial.service.pagseguro;

import java.math.BigDecimal;

import br.com.uol.pagseguro.domain.checkout.Checkout;
import br.com.uol.pagseguro.enums.Currency;
import br.com.uol.pagseguro.enums.ShippingType;
import br.com.uol.pagseguro.exception.PagSeguroServiceException;
import br.com.uol.pagseguro.properties.PagSeguroConfig;

public class PaymentLibService {
	
	public String getPaymentApprovaltURL() {
		
		Checkout checkout = new Checkout();    
		  
		checkout.addItem(  
		  "000-1", // Identificação em seu sistema  
		  "Resina", // Descrição  
		  Integer.valueOf(10), // Quantidade  
		  new BigDecimal("100.00"), // Valor unitário  
		  new Long(50), // Peso unitário, em gramas  
		  new BigDecimal("0.00") // Valor unitário do frete  
		);  
		  
		checkout.addItem("AB01", "Amalgama", Integer.valueOf(20), new BigDecimal("20.00"), new Long(70), new BigDecimal("0.00"));  
		checkout.setShippingAddress("BRA", "SP", "Sao Paulo", "Jd Paulistano", "01452002", "Av. Brig. Faria Lima", "1384", "Sl 1");  
		checkout.setShippingType(ShippingType.SEDEX);  
		checkout.setShippingCost(new BigDecimal("23.30"));  
		checkout.setCurrency(Currency.BRL);  
		
		String response = null;
		try {  
			
			response = checkout.register(PagSeguroConfig.getAccountCredentials(), false);  
			  
			} catch (PagSeguroServiceException e) {  
			    System.err.println(e.getMessage());  
			}  
		return response;
	}


}
