<%-- 
    Document   : Login
    Created on : 24-gen-2014, 16.50.18
    Author     : giovanni
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="">
    <meta name="author" content="">
    <title>login</title>

    <!-- Bootstrap core CSS -->
    <link href="../bootstrapCSS/bootstrap.css" rel="stylesheet">

    <!-- Custom styles for this template -->
    <link href="../forumPlusCSS/signin.css" rel="stylesheet">

    <!-- Just for debugging purposes. Don't actually copy this line! -->
    <!--[if lt IE 9]><script src="../../docs-assets/js/ie8-responsive-file-warning.js"></script><![endif]-->

    <!-- HTML5 shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!--[if lt IE 9]>
      <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
      <script src="https://oss.maxcdn.com/libs/respond.js/1.3.0/respond.min.js"></script>
    <![endif]-->
  </head>

  <body>

    <div class="container">

      <form class="form-signin" action="/Controller" method="POST" name="dati">
        <input type="hidden" name="op" value="login">
        <h2 class="form-signin-heading centra-titolo">Login</h2>
        <c:if test="${requestScope.LoginFail == true}">
            <label style="color: red">username o password errati</label> 
        </c:if>
        <input type="text" name="username" class="form-control" placeholder="Username" required autofocus>
        <input type="password" name="password" class="form-control" placeholder="Password" required>
        <label>
        <a href="/forumJSP/RecuperoPass.jsp">Hai dimenticato la password?</a>
        </label>
        <button class="btn btn-lg btn-primary btn-block" type="submit">Sign in</button>
        <button onclick="window.location.href='/Controller?op=gruppi_pubblici'" class="btn btn-lg btn-primary btn-block" type="button">Gruppi Pubblici</button>
      </form>

        <form action="/forumJSP/Registrazione.jsp" class="form-signin" method="POST">
            <label>Se non sei gia registrato</label>
            <button class="btn btn-lg btn-success btn-block" type="submit">Registrati</button>
        </form>

    </div> <!-- /container -->


    <!-- Bootstrap core JavaScript
    ================================================== -->
    <!-- Placed at the end of the document so the pages load faster -->
  </body>
</html>

