<%-- 
    Document   : HomePage2
    Created on : 27-gen-2014, 10.02.49
    Author     : giovanni
--%>
<%@ page errorPage = "/forumJSP/PaginaErrore.jsp" %>
<%@page import="utility_package.Functions"%>
<%@page import="java.util.ArrayList"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:useBean id="user" scope="session" class="utility_package.User"/>
<jsp:useBean id="manager" scope="session" class="db_package.DBmanager"/>

<!DOCTYPE html>
<html lang="en">
    <head>
        <title>home</title>
        <!-- Bootstrap core CSS -->
        <link href="../bootstrapCSS/bootstrap.css" rel="stylesheet">
        <!-- Custom styles for this template -->
        <link href="../forumPlusCSS/homepage2.css" rel="stylesheet">
        <!-- HTML5 shim and Respond.js IE8 support of HTML5 elements and media queries -->
        <!--[if lt IE 9]>
          <script src="../bootstrap-3.0.0/assets/js/html5shiv.js"></script>
          <script src="../bootstrap-3.0.0/assets/js/respond.min.js"></script>
        <![endif]-->
    </head>
    <body>
        <nav class="navbar navbar-default navbar-fixed-top" role="navigation">
            &nbsp;
            <button type="button" class="btn btn-primary navbar-btn">HOME</button>
            <div class="nav navbar-nav navbar-right">
                <!-- qui mettere l'ora di ultimo accesso (probabilmente tirando fuori l'info dal bean)-->
                <button onclick="window.location.href = '/Controller?op=logout'" type="submit" class="btn btn-primary navbar-btn">Logout</button>
                &nbsp;
            </div>
        </nav>
        <div class="col-md-3">   
        </div>
        <div class="col-md-6"> 
            <div class="contenitore-azzurro">
                <div class="centra">
                    <div class="inlinea">
                        <div class="table-cell-middle">
                            <img src="/file/<c:out value="${user.imageURL}"></c:out>?op=img_profilo" alt="no image." onerror="src='/forumIMG/default-no-profile-pic.jpg'" class="img-thumbnail" style="width: 300px">
                            </div>
                        </div>
                        <div class="inlinea">
                            <div class=" div_testo table-cell-middle">
                                <label class="centra">ciao</label>
                                <label class="centra-utente"><c:out value="${user.username}"> sconosciuto </c:out>!</label>
                                <br>
                                <label class="centra">l'ultima volta sei entrato era:</label>
                                <br>
                                <p>
                                <fmt:formatDate type="both" value="${user.ultimo_accesso}"/>
                            </p>
                        </div>
                    </div>
                </div>
                <div class="masthead padding">
                    <ul class="nav nav-justified" style="background-color: white">
                        <li><a href="/Controller?op=mostragruppi_loggato"><label>Gruppi</label></a></li>
                        <li><a href="/Controller?op=creagruppo"><label>Crea Gruppo</label></a></li>
                            <c:if test="${user.moderatore == true}">
                            <li><a href="/Controller?op=mostragruppi_moderatore"><label>Moderatore</label></a></li>
                            </c:if>
                        <li><a href="/forumJSP/DatiUtente.jsp"><label>Dati Utente</label></a></li>
                    </ul>
                </div>
                <c:if test="${not empty requestScope.listagname}">
                    <ul class="list-group padding-bottom">
                        <li class="list-group-item noborder"><div class="titolo-inviti"><label>Nuovi Inviti ai Gruppi</label></div></li>
                            <c:forEach items="${requestScope.listagname}" var="nome" varStatus="indice"> 
                            <li class="list-group-item noborder">
                                <table class="table">
                                    <tr>
                                        <td><label><c:out value="${nome}"/> di <c:out value="${requestScope.listagadmin[indice.index]}"/></label></td>
                                        <td class="table-cell-left">
                                            <form action="/Controller?op=rispostainvito" method=POST>
                                                <button type="submit" class="btn btn-success" name="bottone" value="accetta">accetta</button>&nbsp;
                                                <button type="submit" class="btn btn-danger" name="bottone" value="rifiuta">Rifiuta</button>                                   
                                                <input type="hidden" name="gname" value="<c:out value="${nome}"/>">
                                                <input type="hidden" name="gadmin" value="<c:out value="${requestScope.listagadmin[indice.index]}"/>">
                                            </form>
                                        <td>
                                    </tr>
                                </table>
                            </li>
                        </c:forEach>
                    </ul>
                </c:if>
            </div>
        </div>         
        <div class="col-md-3">   
        </div>
        <script src="../bootstrapJS/jquery.js"></script>
        <script src="../bootstrapJS/bootstrap.min.js"></script>
    </body>
</html>