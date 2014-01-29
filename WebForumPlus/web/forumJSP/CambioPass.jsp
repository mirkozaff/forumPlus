<jsp:useBean id="user" scope="session" class="utility_package.User"/>
<!DOCTYPE html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="">
    <meta name="author" content="">
    <title>Cambio Password</title>

    <!-- Bootstrap core CSS -->
    <link href="../bootstrapCSS/bootstrap.css" rel="stylesheet">

    <!-- Custom styles for this template -->
    <link href="../forumPlusCSS/registrazione.css" rel="stylesheet">

  </head>

  <body>

    <div class="container">
      <!-- quando si clicca "registrati" viene chiamata la funzione controlla() che vede se le due password sono uguali-->
      <form onSubmit="controlla()" action="/Controller?op=cambio_pass" class="form-signin" name="Mioform" method="POST">
        <h2 class="titolo">Cambia password</h2>
        <label>Vecchia Password</label>
        <input name="old_password" type="password" class="form-control" placeholder="Vecchia Password" required autofocus>
        <label>Nuova Password</label>
        <input name="password" type="password" class="form-control" placeholder="Nuova Password" required>
        <label>Reinserisci la nuova password</label>
        <input name="pass2" type="password" class="form-control" placeholder="Nuova Password" required>  
        <input type="hidden" name="op" value="cambio_pass">
        <button class="btn btn-lg btn-success btn-block margine-top-alto" type="submit">Cambia</button>
      </form>

    </div> <!-- /container -->


    <!-- Bootstrap core JavaScript
    ================================================== -->
    <!-- Placed at the end of the document so the pages load faster -->
    <script type="text/JavaScript" src="../forumPlusJS/passw_uguali.js"></script>
  </body>
</html>
