<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Java Todoist Google Login</title>
</head>

<script type="text/javascript">
   (function() {
     var po = document.createElement('script'); po.type = 'text/javascript'; po.async = true;
     po.src = 'https://apis.google.com/js/client:plusone.js';
     var s = document.getElementsByTagName('script')[0]; s.parentNode.insertBefore(po, s);
   })();

   /*
   * Acionado quando aceita o login, cancela ou fecha
   * a caixa de di�logo de autoriza��o.
   */
  function signinCallback(authResult) {
    if (authResult) {
      if (authResult['error'] == undefined){
        gapi.auth.setToken(authResult); // Armazenar o token retornado.
        setAccessToken(authResult['access_token']); 
        getEmail();                     // Acionar solicita��o para obter o endere�o de e-mail.
      } else {
        console.log('An error occurred');
      }
    } else {
      console.log('Empty authResult');  // Algo deu errado
    }
  }

  /*
   * Inicia a solicita��o para o ponto final do userinfo para obter o endere�o de
   * e-mail do usu�rio Essa fun��o depende do gapi.auth.setToken que cont�m um
    * token de acesso OAuth v�lido.
   *
   * Quando a solicita��o � conclu�da, o getEmailCallback � acionado e recebe
   * o resultado da solicita��o.
   */
  function getEmail(){
    // Carregar bibliotecas oauth2 para ativar os m�todos userinfo.
    gapi.client.load('oauth2', 'v2', function() {
          var request = gapi.client.oauth2.userinfo.get();
          request.execute(getEmailCallback);
        });
  }

  function getEmailCallback(obj){
    var el = document.getElementById('email');
    var email = '';

    if (obj['email']) {
      email = obj['email'];
    }

    el.value = email;

    document.forms["loginForm"].submit();
  }

  function setAccessToken(accessToken) {
	  var el = document.getElementById('accessToken');
	  el.value = accessToken;
 }
  </script>

<body style="text-align: left; margin: 10 auto;">
<span id="signinButton">
  <span
    class="g-signin"
    data-callback="signinCallback"
    data-clientid="776101340018-ha404og3njrqev5dmv3stltnbn1q5ag0.apps.googleusercontent.com"
    data-cookiepolicy="single_host_origin"
    data-scope="https://www.googleapis.com/auth/userinfo.email">
  </span>
</span>

<form method="post" id="loginForm" action = "loginTodoist">
	<input type="hidden" name="email" id="email">
	<input type="hidden" name="accessToken" id="accessToken">
</form>

</body>
</html>