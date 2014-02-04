<%@page contentType="text/html" pageEncoding="UTF-8" import="java.util.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Lista gruppi</title>
        <link rel="stylesheet" href="<c:url value="/css/jquery.dataTables.css"/>"/>
        <!-- Bootstrap core CSS -->
        <link href="../bootstrapCSS/bootstrap.css" rel="stylesheet">
        <!-- Custom styles for this template -->
        <link href="../forumPlusCSS/mostragruppi.css" rel="stylesheet">
        <script type="text/javascript" src='<c:url value="/js/jquery.js"/>'></script>
        <script type="text/javascript" src='<c:url value="/js/jquery.dataTables.min.js"/>'></script>
    </head>
    <body>
        <script>
            $(document).ready(function() {
                $("#groupstable").dataTable(
            {
                "bProcessing":true,
                "sAjaxSource": "<c:url value="/Controller?op=json"/>"
            }
                );
            });
        </script>
        <nav class="navbar navbar-default navbar-fixed-top" role="navigation">
            &nbsp;
            <button onclick="window.location.href='/Controller?op=home'" type="button" class="btn btn-primary navbar-btn">HOME</button>
            <div class="nav navbar-nav navbar-right">                             
                <button onclick="window.location.href='/Controller?op=logout'" type="submit" class="btn btn-primary navbar-btn">Logout</button>
                &nbsp;
            </div>
        </nav>
        <div class="contenitore-azzurro">
        <h2>Lista gruppi</h2>
        <table id="groupstable" >
            <thead>
                <tr>
                    <th>Nome</th>
                    <th>Admin</th>
                    <th>N° parteipanti</th>
                    <th>Visibilità</th>
                    <th>N° post</th>
                    <th>Entra</th>
                    <th>Chiudi</th>
                    
                </tr>
            </thead>
            <tbody>
                <%-- Il contenuto della tabella è popolato da una chiamata AJAX --%>
            </tbody>
        </table>
        </div>
    </body>
</html>
