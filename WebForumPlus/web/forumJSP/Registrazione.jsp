<%-- 
    Document   : RegistrazioneJSP
    Created on : 24-gen-2014, 17.33.01
    Author     : giovanni
--%>
<%@ page errorPage = "/forumJSP/PaginaErrore.jsp" %>
<jsp:useBean id="user" scope="session" class="utility_package.User"/>
<!DOCTYPE html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="">
    <meta name="author" content="">
    <title>Registrazione</title>

    <!-- Bootstrap core CSS -->
    <link href="../bootstrapCSS/bootstrap.css" rel="stylesheet">

    <!-- Custom styles for this template -->
    <link href="../forumPlusCSS/registrazione.css" rel="stylesheet">

  </head>

  <body>

    <div class="container">
      <!-- quando si clicca "registrati" viene chiamata la funzione controlla() che vede se le due password sono uguali-->
      <form onSubmit="controlla()" action="/Controller" class="form-signin" name="Mioform" method="post">
        <h2 class="titolo">Inserisci i tuoi dati</h2>
        <label>Inserisci il nome utente che desideri</label>
        <input name="nickname" type="text" class="form-control" placeholder="nickname" required autofocus>
        <label>Inserisci il tuo indirizzo email</label>
        <input name="email" type="text" class="form-control" placeholder="Email address" required>
        <label>Inserisci la password</label>
        <input name="password" type="password" class="form-control" placeholder="Password" required>
        <label>Reinserisci la password</label>
        <input name="pass2" type="password" class="form-control" placeholder="Password" required>  
        <input type="hidden" name="op" value="registrazione">
        <button class="btn btn-lg btn-success btn-block margine-top-alto" type="submit">Registrati</button>
        <button onclick="window.location.href = '/forumJSP/Login.jsp'" class="btn btn-lg btn-success btn-block" type="button">Torna al Login</button>
      </form>

    </div> <!-- /container -->


    <!-- Bootstrap core JavaScript
    ================================================== -->
    <!-- Placed at the end of the document so the pages load faster -->
    <script type="text/JavaScript" src="../forumPlusJS/passw_uguali.js"></script>
  </body>
</html>
