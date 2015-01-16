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
   * a caixa de diálogo de autorização.
   */
  function signinCallback(authResult) {
    if (authResult) {
      if (authResult['error'] == undefined){
        gapi.auth.setToken(authResult); // Armazenar o token retornado.
        setAccessToken(authResult['access_token']); 
        getEmail();                     // Acionar solicitação para obter o endereço de e-mail.
      } else {
        console.log('An error occurred');
      }
    } else {
      console.log('Empty authResult');  // Algo deu errado
    }
  }

  /*
   * Inicia a solicitação para o ponto final do userinfo para obter o endereço de
   * e-mail do usuário Essa função depende do gapi.auth.setToken que contém um
    * token de acesso OAuth válido.
   *
   * Quando a solicitação é concluída, o getEmailCallback é acionado e recebe
   * o resultado da solicitação.
   */
  function getEmail(){
    // Carregar bibliotecas oauth2 para ativar os métodos userinfo.
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