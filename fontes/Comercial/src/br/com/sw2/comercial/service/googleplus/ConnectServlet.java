package br.com.sw2.comercial.service.googleplus;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.api.client.auth.oauth2.TokenResponseException;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeTokenRequest;
import com.google.api.client.googleapis.auth.oauth2.GoogleTokenResponse;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.gson.Gson;

@WebServlet("/connect")
public class ConnectServlet extends HttpServlet {

	private static final long serialVersionUID = -4707706294912383331L;
	private static final String CLIENT_ID = "776101340018-ha404og3njrqev5dmv3stltnbn1q5ag0.apps.googleusercontent.com";
	private static final String CLIENT_SECRET = "1pDVjLp3ZQY7sXChPt_qqRJr";
	
	 // Default HTTP transport to use to make HTTP requests.
	private static final HttpTransport TRANSPORT = new NetHttpTransport();

	// Default JSON factory to use to deserialize JSON.
	private static final JacksonFactory JSON_FACTORY = new JacksonFactory();

	// Gson object to serialize JSON responses to requests to this servlet.
	private static final Gson GSON = new Gson();


	@Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
      response.setContentType("application/json");

      // Only connect a user that is not already connected.
      String tokenData = (String) request.getSession().getAttribute("token");
      if (tokenData != null) {
        response.setStatus(HttpServletResponse.SC_OK);
        response.getWriter().print(GSON.toJson("Usuário já está logado."));
        return;
      }
      // Ensure that this is no request forgery going on, and that the user
      // sending us this connect request is the user that was supposed to.
      if (!request.getParameter("state").equals("514257454|0.1793525742")) {
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        response.getWriter().print(GSON.toJson("Parâmetro de validação incorreto."));
        return;
      }
      
      request.getSession().removeAttribute("state");

      ByteArrayOutputStream resultStream = new ByteArrayOutputStream();
      getContent(request.getInputStream(), resultStream);
      String code = new String(resultStream.toByteArray(), "UTF-8");

      try {
        // Upgrade the authorization code into an access and refresh token.
        GoogleTokenResponse tokenResponse =
            new GoogleAuthorizationCodeTokenRequest(TRANSPORT, JSON_FACTORY,
                CLIENT_ID, CLIENT_SECRET, code, "postmessage").execute();

        // Store the token in the session for later use.
        request.getSession().setAttribute("token", tokenResponse.toString());
        response.setStatus(HttpServletResponse.SC_OK);
        response.getWriter().print(GSON.toJson("Usuário logado com sucesso."));
      } catch (TokenResponseException e) {
        response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        response.getWriter().print(GSON.toJson("Falha ao atualizar o código de autorização."));
      } catch (IOException e) {
        response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        response.getWriter().print(GSON.toJson("Falha na leitura do token. " + e.getMessage()));
      }
    }

    /*
     * Read the content of an InputStream.
     *
     * @param inputStream the InputStream to be read.
     * @return the content of the InputStream as a ByteArrayOutputStream.
     * @throws IOException 
     */
    static void getContent(InputStream inputStream, ByteArrayOutputStream outputStream)
        throws IOException {
      // Read the response into a buffered stream
      BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
      int readChar;
      while ((readChar = reader.read()) != -1) {
        outputStream.write(readChar);
      }
      reader.close();
    }
  }
