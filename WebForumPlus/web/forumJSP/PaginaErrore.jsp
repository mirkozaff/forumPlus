<%-- 
    Document   : PaginaErrore
    Created on : 5-feb-2014, 10.58.45
    Author     : giovanni
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:useBean id="user" scope="session" class="utility_package.User"/>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <meta name="description" content="">
        <meta name="author" content="">

        <title>Pagina Errore</title>

        <!-- Bootstrap core CSS -->
        <link href="../bootstrapCSS/bootstrap.css" rel="stylesheet">

        <!-- Custom styles for this template -->
        <link href="../forumPlusCSS/paginaerrore.css" rel="stylesheet">

        <!-- Just for debugging purposes. Don't actually copy this line! -->
        <!--[if lt IE 9]><script src="../../docs-assets/js/ie8-responsive-file-warning.js"></script><![endif]-->

        <!-- HTML5 shim and Respond.js IE8 support of HTML5 elements and media queries -->
        <!--[if lt IE 9]>
          <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
          <script src="https://oss.maxcdn.com/libs/respond.js/1.3.0/respond.min.js"></script>
        <![endif]-->
    </head>

    <body>
        <%@page isErrorPage="true" %> 
        <div class="row centraverticale">
            <div class="col-md-3"></div>
            <div class="col-md-6">

                         <br>
                         <div class="contenitore-azzurro centra">
                             <h3>Si è verificato un errore durante l'esecuzione dell'applicazione</h3>
                             <br>
                             <form action="/forumJSP/Login.jsp" method=POST>
                            <div class="form-group">
                                <input class="btn btn-lg btn-success" type="submit" value="Torna indietro">
                            </div>
                        </form>
                         </div>
            </div>
            <div class="col-md-3"></div>
        <script src="../bootstrapJS/jquery.js"></script>
        <script src="../bootstrapJS/bootstrap.min.js"></script>
    </body>
</html>
