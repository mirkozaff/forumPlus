<%-- 
    Document   : VisualizzaPost
    Created on : 29-gen-2014, 13.51.12
    Author     : giovanni
--%>
<%@ page errorPage = "/forumJSP/PaginaErrore.jsp" %>
<%@page import="utility_package.Post"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.Scanner"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:useBean id="user" scope="session" class="utility_package.User"/>
<jsp:useBean id="manager" scope="session" class="db_package.DBmanager"/>
<jsp:useBean id="gruppo" scope="request" class="utility_package.Gruppo"/>


<!DOCTYPE html>
<html lang="en">
    <head>
        <title>home</title>
        <!-- Bootstrap core CSS -->
        <link href="../bootstrapCSS/bootstrap.css" rel="stylesheet">
        <!-- Custom styles for this template -->
        <link href="../forumPlusCSS/visualizzapost.css" rel="stylesheet">
        <!-- HTML5 shim and Respond.js IE8 support of HTML5 elements and media queries -->
        <!--[if lt IE 9]>
          <script src="../bootstrap-3.0.0/assets/js/html5shiv.js"></script>
          <script src="../bootstrap-3.0.0/assets/js/respond.min.js"></script>
        <![endif]-->
    </head>
    <body>
        <c:choose>
            <c:when test="${(user.username != null) && (bottone == false)}">
        <nav class="navbar navbar-default navbar-fixed-top" role="navigation">
            <div class="nav navbar-nav">
                &nbsp;
                <button onclick="window.location.href = '/Controller?op=home'" type="button" class="btn btn-primary navbar-btn">HOME</button>

                <button onclick="window.location.href = '/Controller?op=mostragruppi_loggato'" type="button" class="btn btn-primary navbar-btn">Torna ai gruppi</button>

            </div>    
            <div class="nav navbar-nav navbar-right">
                <button onclick="window.location.href = '/Controller?op=logout'" type="button" class="btn btn-primary navbar-btn">Logout</button>
                &nbsp;
            </div>
        </nav>
            </c:when>
            <c:when test="${(user.username != null) && (bottone == true)}">
                <nav class="navbar navbar-default navbar-fixed-top" role="navigation">
            <div class="nav navbar-nav">
                &nbsp;
                <button onclick="window.location.href = '/Controller?op=home'" type="button" class="btn btn-primary navbar-btn">HOME</button>

                <button onclick="window.location.href = '/Controller?op=mostragruppi_moderatore'" type="button" class="btn btn-primary navbar-btn">Torna ai gruppi del moderatore</button>

            </div>    
            <div class="nav navbar-nav navbar-right">
                <button onclick="window.location.href = '/Controller?op=logout'" type="button" class="btn btn-primary navbar-btn">Logout</button>
                &nbsp;
            </div>
        </nav>
            </c:when>
            <c:otherwise>
                <%-- caso non loggato--%>
                <nav class="navbar navbar-default navbar-fixed-top" role="navigation">
            <div class="nav navbar-nav">
                &nbsp;
                <button onclick="window.location.href = '/forumJSP/Login.jsp'" type="button" class="btn btn-primary navbar-btn">Login</button>

                <button onclick="window.location.href = '/Controller?op=gruppi_pubblici'" type="button" class="btn btn-primary navbar-btn">Torna ai gruppi pubblici</button>

            </div>    
        </nav>
            </c:otherwise>
        </c:choose>
        <c:if test="${(user.username != null) && (gruppo.closed == false) && (gruppo.inscritto == true)}">
                        <nav class="navbar navbar-default navbar-fixed-bottom" role="navigation" >
            <div style="text-align: center">
                <a data-toggle="modal" href="#example"><button type="button" class="btn btn-primary navbar-btn">Add POST</button></a>
            </div>
        </nav>
        </c:if>

        <%-- modal--%>           
        <div id="example" class="modal fade">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                        <h4 class="modal-title">Post</h4>
                    </div>
                    <div class="modal-body">
                        <textarea name="post" placeholder="scrivi qui il tuo post..." cols="85" rows="8" maxlength="10000" style="resize: none" form="form" required></textarea>
                        <div>
                            <form id="form" action="/ServletUpload?op=testo&gname=<c:out value="${requestScope.gname}&gadmin=${requestScope.gadmin}"></c:out>" method=POST enctype="multipart/form-data">
                                    <br>
                                    <input type="file" name="filepost">
                                    <br>
                                    <button type="submit" class="btn btn-success" name="bottone" value="upload_gruppi">Save changes</button>
                                    <input type="hidden" name="op" value="testo"/>
                                    <input type="hidden" name="gname" value="<c:out value="${requestScope.gname}"></c:out>"/>
                                <input type="hidden" name="gadmin" value="<c:out value="${requestScope.gadmin}"></c:out>"/>
                                </form>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        <%-- modal--%>
        <div class="navbar-default divcentrato">
            <p align="center" style="font-size: 250%"><c:out value="${requestScope.gname}"></c:out></p>
            <c:if test="${gruppo.closed}">
                <br>
                <p align="center" class="chiuso"> il gruppo è chiuso!!</p>
            </c:if>
                <div style="text-align: center">
                <c:if test="${(requestScope.gadmin == user.username) && (gruppo.closed == false)}">
                    <form action="/Controller?op=modificagruppo" method=POST>
                        <button type="submit" class="btn btn-success navbar-btn">edita gruppo</button>&nbsp;
                        <input type="hidden" name="gname" value="<c:out value="${requestScope.gname}"></c:out>"/>
                        <input type="hidden" name="gadmin" value="<c:out value="${requestScope.gadmin}"></c:out>"/>
                            <button type="submin" class="btn btn-success navbar-btn" formaction="ServletPDF">pdf del gruppo</button>
                        </form>
                </c:if>
                <c:if test="${(requestScope.gadmin == user.username) && (gruppo.closed)}">
                     <form action="ServletPDF" method=POST>
                        <input type="hidden" name="gname" value="<c:out value="${requestScope.gname}"></c:out>"/>
                        <input type="hidden" name="gadmin" value="<c:out value="${requestScope.gadmin}"></c:out>"/>
                            <button type="submin" class="btn btn-success navbar-btn">pdf del gruppo</button>
                        </form>
                </c:if>    
            </div>
            <c:choose>
                <c:when test="${empty requestScope.listapost}">         <!-- controllo se listapost è vuota-->
                    <p align="center">non ci sono post in questo gruppo</p>
                </c:when>
                <c:otherwise>
                    <c:forEach items="${requestScope.listapost}" var="post" varStatus="indice">
                        <div class="container">
                            <div class="row">
                                <div class="col-md-2">
                                    <div class="row">
                                        <div>
                                            <h2>
                                                <c:out value="${post.getUtente_postante()}"></c:out>
                                                </h2>
                                            </div>
                                        </div>
                                        <div class="row">
                                            <div>
                                                <img src="/file/<c:out value="${manager.getAvatar(post.getUtente_postante())}"/>?op=img_avatar&avatar=<c:out value="${post.getUtente_postante()}"/>" alt="no image" onerror="src='/WebForum/forumIMG/default-no-profile-pic.jpg'" class="img-rounded" style="width: 100px">
                                        </div>
                                    </div>
                                    <div class="row">
                                        <div><c:out value="${post.getData()}"></c:out></div>
                                        </div>
                                    </div>
                                    <div style="margin-top: 20px">
                                        <div class="box">                                     
                                        <c:out value="${post.getTesto()}" escapeXml="false"></c:out> 
                                        </div>
                                    </div>
                                </div>
                            </div>

                    </c:forEach>
                </c:otherwise>
            </c:choose>
        </div>
        <script src="../bootstrapJS/modal.js"></script>
        <script src="../bootstrapJS/jquery.js"></script>
        <script src="../bootstrapJS/bootstrap.min.js"></script>
    </body>
</html>