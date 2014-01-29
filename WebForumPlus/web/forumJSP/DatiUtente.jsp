<%-- 
    Document   : DatiUtente
    Created on : 28-gen-2014, 16.23.05
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

        <title>Dati utente</title>

        <!-- Bootstrap core CSS -->
        <link href="../bootstrapCSS/bootstrap.css" rel="stylesheet">

        <!-- Custom styles for this template -->
        <link href="../forumPlusCSS/datiutente.css" rel="stylesheet">

        <!-- Just for debugging purposes. Don't actually copy this line! -->
        <!--[if lt IE 9]><script src="../../docs-assets/js/ie8-responsive-file-warning.js"></script><![endif]-->

        <!-- HTML5 shim and Respond.js IE8 support of HTML5 elements and media queries -->
        <!--[if lt IE 9]>
          <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
          <script src="https://oss.maxcdn.com/libs/respond.js/1.3.0/respond.min.js"></script>
        <![endif]-->
    </head>

    <body>
        <nav class="navbar navbar-default navbar-fixed-top" role="navigation">
            &nbsp;
            <button onclick="window.location.href = '/forumJSP/HomePage2.jsp'" type="button" class="btn btn-primary navbar-btn">HOME</button>
            <div class="nav navbar-nav navbar-right">                             
                <a href="servletLogout"> <button type="submit" class="btn btn-primary navbar-btn">Logout</button> </a>
                &nbsp;
            </div>
        </nav>   
        <div class="row centraverticale">
            <div class="col-md-4"></div>
            <div class="col-md-4">
                <div class="contenitore-azzurro">
                     <img src="/file/<c:out value="${user.imageURL}"></c:out>?op=img_profilo" alt="no image." onerror="src='/forumIMG/default-no-profile-pic.jpg'" class="img-thumbnail" style="width: 300px">
                         <br>
                    <div class="centra">
                        <form class="form-inline" action="/ServletUpload?op=img_profilo" method=POST enctype="multipart/form-data">
                            <div class="form-group">
                                <input type="file" name="file1">
                            </div>
                            <div class="form-group">
                                <input class="btn btn-lg btn-success" type="submit" value="Upload">
                            </div>
                        </form>
                    </div>
                </div>
                         <br>
                         <div class="contenitore-azzurro">
                             <form class="form-inline" action="/ServletUpload?op=img_profilo" method=POST>
                            <div class="form-group">
                                <input class="btn btn-lg btn-success" type="submit" value="Cambia Password">
                            </div>
                        </form>
                         </div>
            </div>
            <div class="col-md-4"></div>
        <script src="../bootstrapJS/jquery.js"></script>
        <script src="../bootstrapJS/bootstrap.min.js"></script>
    </body>
</html>
