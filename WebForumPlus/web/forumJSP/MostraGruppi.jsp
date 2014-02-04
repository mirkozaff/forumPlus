<%-- 
    Document   : MostraGruppi
    Created on : 27-gen-2014, 22.02.16
    Author     : giovanni
--%>

<%@page import="java.util.ArrayList"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:useBean id="user" scope="session" class="utility_package.User"/>
<jsp:useBean id="manager" scope="session" class="db_package.DBmanager"/>

<!DOCTYPE html>

<%-- prendo i nomi e gli admin dei gruppi che interessano all'utente--%>

<html lang="en">
    <head>
        <title>gruppi</title>
        <!-- Bootstrap core CSS -->
        <link href="../bootstrapCSS/bootstrap.css" rel="stylesheet">
        <!-- Custom styles for this template -->
        <link href="../forumPlusCSS/mostragruppi.css" rel="stylesheet">
        <!-- HTML5 shim and Respond.js IE8 support of HTML5 elements and media queries -->
        <!--[if lt IE 9]>
          <script src="../bootstrap-3.0.0/assets/js/html5shiv.js"></script>
          <script src="../bootstrap-3.0.0/assets/js/respond.min.js"></script>
        <![endif]-->
    </head>
    <body>
        <nav class="navbar navbar-default navbar-fixed-top" role="navigation">
            &nbsp;
            <button onclick="window.location.href='/forumJSP/HomePage2.jsp'" type="button" class="btn btn-primary navbar-btn">HOME</button>
            <div class="nav navbar-nav navbar-right">                             
                <button onclick="window.location.href='/Controller?op=logout'" type="submit" class="btn btn-primary navbar-btn">Logout</button>
                &nbsp;
            </div>
        </nav>
        <div class="row">
            <div class="col-md-4"></div>
            <div class="col-md-4">
                <div class="contenitore-azzurro centraverticale">
                    <table class="table">
                        <tr class="sfondotr text-center">
                            <td colspan="3">
                                <h2>Gruppi a cui sei iscritto</h2>
                            </td>
                        </tr>
                        <tr>
                            <td>
                                <h4>Nome:</h4>
                            </td>
                            <td class="cell-center">
                                <h4>Admin:</h4>
                        </tr>
                        <br>
                        <c:forEach items="${requestScope.listagruppi}" var="gruppo" varStatus="indice">   <!-- ciclo sui nomi dei gruppi-->
                            <tr>
                                <td>
                                    <c:out value="${gruppo}"/>
                                </td>
                                <td class="cell-center">
                                    <c:out value="${requestScope.listaadmin[indice.index]}"/>  <!-- uso l'indice dell'array dei nomi dei gruppi per ciclare sull'array degli admin-->
                                </td>
                                <td class="cell-left">
                                    <form action="/Controller?op=visualizzapost" method=POST>
                                        <input class="btn btn-lg btn-success" type="submit" value="entra">
                                        <input type="hidden" name="gname" value="<c:out value="${gruppo}"/>">
                                        <input type="hidden" name="gadmin" value="<c:out value="${requestScope.listaadmin[indice.index]}"/>">
                                    </form>
                                </td>
                            </tr>
                        </c:forEach>
                    </table>
                </div>
            </div>
            <div class="col-md-4">
            </div>
        </div>
        <script src="../bootstrapJS/jquery.js"></script>
        <script src="../bootstrapJS/bootstrap.min.js"></script>
    </body>
</html>