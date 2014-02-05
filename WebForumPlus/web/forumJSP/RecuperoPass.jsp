<%@page errorPage = "/forumJSP/PaginaErrore.jsp" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="">
    <meta name="author" content="">
    <title>Recupero Password</title>

    <!-- Bootstrap core CSS -->
    <link href="../bootstrapCSS/bootstrap.css" rel="stylesheet">

    <!-- Custom styles for this template -->
    <link href="../forumPlusCSS/registrazione.css" rel="stylesheet">

  </head>

  <body>

    <div class="container">
      <form action="/Controller" class="form-signin" name="Mioform" method="POST">
        <h2 class="titolo">Recupero Password</h2>
         <c:if test="${requestScope.errorenelrecupero == true}">
            <label style="color: red">Nome utente o Email errati</label> 
        </c:if>
            <br>    
        <label>Nome utente</label>
        <input name="username" type="text" class="form-control" placeholder="Username" required autofocus>
        <label>Email</label>
        <input name="mailto" type="text" class="form-control" placeholder="Email" required>
        <input type="hidden" name="op" value="recupero">
        <button class="btn btn-lg btn-success btn-block margine-top-alto" type="submit">Invia</button>
        <button onclick="window.location.href = '/forumJSP/Login.jsp'" class="btn btn-lg btn-success btn-block" type="submit">Torna al Login</button>
      </form>

    </div> <!-- /container -->


    <!-- Bootstrap core JavaScript
    ================================================== -->
    <!-- Placed at the end of the document so the pages load faster -->
    <script type="text/JavaScript" src="../forumPlusJS/passw_uguali.js"></script>
  </body>
</html>
