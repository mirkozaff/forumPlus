<%-- 
    Document   : RegistrazioneJSP
    Created on : 24-gen-2014, 17.33.01
    Author     : giovanni
--%>

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
     <script language="JavaScript">
function controlla() {
var f = document.Mioform;
if (f.pass1.value != f.pass2.value) {
alert("Le due Password non sono uguali.");
return false;
}else{return true;}
}
</script>
    
  </head>

  <body>

    <div class="container">

      <form onSubmit="controlla()" action="Login.jsp" class="form-signin" name="Mioform">
        <h2 class="titolo">Inserisci i tuoi dati</h2>
        <label>Inserisci il tuo indirizzo email</label>
        <input type="text" class="form-control" placeholder="Email address" required autofocus>
        <label>Inserisci la password</label>
        <input name="pass1" type="password" class="form-control" placeholder="Password" required>
        <label>Reinserisci la password</label>
        <input name="pass2" type="password" class="form-control" placeholder="Password" required>        
        <button class="btn btn-lg btn-success btn-block margine-top-alto" type="submit">Registrati</button>
      </form>

    </div> <!-- /container -->


    <!-- Bootstrap core JavaScript
    ================================================== -->
    <!-- Placed at the end of the document so the pages load faster -->
  </body>
</html>
