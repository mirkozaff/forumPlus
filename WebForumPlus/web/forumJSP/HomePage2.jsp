<%-- 
    Document   : HomePage2
    Created on : 27-gen-2014, 10.02.49
    Author     : giovanni
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:useBean id="user" scope="session" class="utility_package.User"/>
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
                <button onclick="window.location.href='/Controller?op=logout'" type="submit" class="btn btn-primary navbar-btn">Logout</button>
                &nbsp;
            </div>
        </nav>
        <div class="row">
            <div class="col-md-3">   
            </div>
            <div class="col-md-6"> 
                <div class="contenitore-azzurro">
                    <div class="centra">
                    <div class="inlinea">
                        <div class="table-cell-middle">
                            <img src="/forumIMG/22-sfondo-multicolori.jpg" alt="no image." onerror="src='/WebForumPlus/forumIMG/default-no-profile-pic.jpg'" class="img-thumbnail" style="width: 300px">
                            <br>
                            <button type="button" class="btn btn-primary margine-top">dati utente</button>
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
                </div>
            </div>         
            <div class="col-md-3">   
            </div>
        </div>
        <div class="row">
            <div class="col-md-6">
                <div class="div_bottoni">
                    <c:if test="${user.moderatore == true}">
                      
                            <button type="button" class="btn btn-primary bei-bottoni"><label>Moderatore</label></button>
                        
                    <br>
                    </c:if>
                    
                        <button onclick="window.location.href='/forumJSP/MostraGruppi.jsp'" name="op" value="listagruppi" type="button" class="btn btn-primary bei-bottoni"><label>Gruppi</label></button>
                    
                    <br>
                    
                    <button onclick="window.location.href='/Controller?op=creagruppo'" type="button" class="btn btn-primary bei-bottoni"><label>Crea Gruppo</label></button>
                   
                </div>
            </div>
            <div class="col-md-6">
                    <div class="centra-tabella">
                        <div class="panel panel-default">
                            <!-- Default panel contents -->
                            <div class="panel-heading titolo-inviti"><label class="white">Nuovi Inviti ai Gruppi</label></div>

                            <!-- Table -->
                            <table class="table">
                                          <!-- qui mettere il for che scorre gli inviti-->
                        <tr class="colore-caselle">
                            <td>
                                <label> nome gruppo </label>
                            </td>
                            <td class="table-cell-left">
                                <form action="servletRisposteInviti" method=POST>
                                    <button type="submit" class="btn btn-success" name="bottone" value="accetta">accetta</button>&nbsp;
                                    <button type="submit" class="btn btn-danger" name="bottone" value="rifiuta">Rifiuta</button>
                                    
                                    <input type="hidden" name="gname" value="listagname.get(i)">
                                    <input type="hidden" name="gadmin" value="listagadmin.get(i)">
                                </form>
                            </td>
                            
                        </tr>
                        <!-- gestire il caso in cui non ci siano inviti con <p>non hai inviti</p> -->
                            </table>
                        </div>
                    </div>  
            </div>
        </div>
        <script src="../bootstrapJS/jquery.js"></script>
        <script src="../bootstrapJS/bootstrap.min.js"></script>
    </body>
</html>