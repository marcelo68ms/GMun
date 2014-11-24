package br.com.sw2.comercial.service.sms;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.StringWriter;
import java.net.URL;
import java.net.URLEncoder;

import javax.net.ssl.HttpsURLConnection;
import javax.swing.JOptionPane;
import javax.xml.soap.MessageFactory;
import javax.xml.soap.MimeHeaders;
import javax.xml.soap.SOAPConnection;
import javax.xml.soap.SOAPConnectionFactory;
import javax.xml.soap.SOAPException;
import javax.xml.soap.SOAPMessage;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.hsqldb.lib.StringUtil;
import org.w3c.dom.Document;

import br.com.sw2.comercial.service.email.EmailService;

/**
 * @author Djane
 * 
 * Para rodar os métodos desta classe é preciso incluir o argumento 
 * -Djsse.enableSNIExtension=false na VM
 */
public class FacilitaMovelService {
	
	private final static String URL = "https://www.facilitamovel.com.br/";
	
	public static void main(String[] args) {
		
		//enviarSmsViaHttp();
		
		//enviarSmsViaWebService();
		
		eviarSmsViaEmail();
        
	}


	private static void eviarSmsViaEmail() {
		String celular = popUpCelular();
		EmailService.sendSimpleMail("Teste Email2SMS OdonTO-DO", celular, "smsmultiplo@facilitamovel.com");
		
	}


	/**
	 * Teste usando HTTP
	 * 
	 * POST https://www.facilitamovel.com.br/api/simpleSend.ft?
	 * user=xxx&password=xxx&destinatario=0000000000&msg=xxxxxxxxx
	 * 
	 */
	public static void enviarSmsViaHttp() {
		
		String celular = popUpCelular();
		
		String httpsURL = URL + "api/simpleSend.ft?";

		try {
			String query = "user=odontodo&password=fapesp2014&destinatario=";
			query += celular + "&";
			query += "msg=" + URLEncoder.encode("Teste Https OdonTO-DO","UTF-8");

			URL url = new URL(httpsURL);
			HttpsURLConnection con = (HttpsURLConnection)url.openConnection();
			con.setRequestMethod("POST");

			con.setRequestProperty("Content-length", String.valueOf(query.length())); 
			con.setRequestProperty("Content-Type","application/x-www-form-urlencoded"); 
			con.setDoOutput(true); 
			con.setDoInput(true); 

			DataOutputStream output = new DataOutputStream(con.getOutputStream());  

			output.writeBytes(query);
			output.close();

			DataInputStream input = new DataInputStream(con.getInputStream() ); 

			for( int c = input.read(); c != -1; c = input.read() ) 
			System.out.println((char)c); 
			input.close(); 

			System.out.println("Resp Code: "+con.getResponseCode()); 
			System.out.println("Resp Message: "+ con.getResponseMessage()); 
		} catch (IOException e)	{
			e.printStackTrace();
		}
		
	}
	
	
	/**
	 * Teste usando WebService
	 */
	public static void enviarSmsViaWebService() {
		
		String celular = popUpCelular();
			
		String requestSoap = "<soap:Envelope xmlns:soap=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:ws=\"http://ws.action.fadmin.com.br/\">\n"
		    		+ "<soap:Body>\n" 
		    		+ "	<ws:sendSimpleMessage>\n"
		    		+ "		<user>odontodo</user>\n" 
		    		+ "		<password>fapesp2014</password>\n" 
		    		+ "		<phone>"+celular+"</phone>\n" 
		    		+ "		<message>Teste WebService OdonTO-DO</message>\n" 
		    		+ "	</ws:sendSimpleMessage>\n" 
		    		+ "</soap:Body>\n" 
		    		+ "</soap:Envelope>";
		try {		
		    SOAPConnectionFactory soapConnectionFactory = SOAPConnectionFactory.newInstance();
	        SOAPConnection soapConnection = soapConnectionFactory.createConnection();
	        MimeHeaders headers = new MimeHeaders();
	        headers.addHeader("Content-Type", "text/xml");
	 
	        MessageFactory messageFactory = MessageFactory.newInstance();
	 
	        SOAPMessage msg = messageFactory.createMessage(headers, (new ByteArrayInputStream(requestSoap.getBytes())));
	 
	        String webServiceURL = URL + "SendMessage";
	        SOAPMessage soapResponse = soapConnection.call(msg, webServiceURL);
	        Document xml = soapResponse.getSOAPBody().getOwnerDocument();
	        System.out.println(passarXMLParaString(xml,4));
		} catch (SOAPException | IOException e) {
			e.printStackTrace();
		}
	}
	
	   private static String passarXMLParaString(Document xml, int espacosIdentacao){
	        try {
	            //set up a transformer
	            TransformerFactory transfac = TransformerFactory.newInstance();
	            transfac.setAttribute("indent-number", new Integer(espacosIdentacao));
	            Transformer trans = transfac.newTransformer();
	            trans.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "yes");
	            trans.setOutputProperty(OutputKeys.INDENT, "yes");
	 
	            //create string from xml tree
	            StringWriter sw = new StringWriter();
	            StreamResult result = new StreamResult(sw);
	            DOMSource source = new DOMSource(xml);
	            trans.transform(source, result);
	            String xmlString = sw.toString();
	            return xmlString;
	        }
	        catch (TransformerException e) {
	            e.printStackTrace();
	            System.exit(0);
	        }
	        return null;
	    }
	
	
	private static String popUpCelular() {
		String celular = null;
        while (StringUtil.isEmpty(celular)) {
        	celular = JOptionPane.showInputDialog("Informe um número de celular com DDD");
        	if (celular == null) {
        		break;
        	}
        	if (celular.isEmpty()) {
        		JOptionPane.showMessageDialog(null, "Você não informou o número.");
        	}
        }
        
        return celular;
	}
	
}
