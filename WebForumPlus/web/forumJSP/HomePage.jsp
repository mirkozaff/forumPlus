<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:useBean id="user" scope="session" class="utility_package.User"/>
<!DOCTYPE html>
<html lang="en">
    <head>
        <title>home</title>
        <!-- Bootstrap core CSS -->
        <link href="../bootstrapCSS/bootstrap.css" rel="stylesheet">
        <!-- Custom styles for this template -->
        <link href="../forumPlusCSS/homepage.css" rel="stylesheet">
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
            <label class="utente-nav">
                <c:out value="${user.username}"> sconosciuto </c:out>
            </label>
                <div class="nav navbar-nav navbar-right">
                    <!-- qui mettere l'ora di ultimo accesso (probabilmente tirando fuori l'info dal bean)-->
                    <button type="submit" class="btn btn-primary navbar-btn">Logout</button>
                    &nbsp;
                </div>
            </nav>
            <div class="container centraverticale">
                <div class="row">
                    <div class="col-lg-4">
                    </div>
                    <div class="col-lg-4">
                        <h2> lista inviti </h2>
                    </div>
                    <div class=col-lg-4>"
                        <h2> Benvenuto <jsp:getProperty name="user" property="username"/></h2>
                </div>
            </div>
            <div class="row">
                <div class="col-lg-4">
                    <c:if test="${user.moderatore == true}">
                        <div class="row">
                            <div><a href="" class="btn btnmio btn-primary navbar-btn"><big><big>moderatore</big></big></a></div>
                        </div>
                    </c:if>
                    <div class="row">
                        <div><a href="" class="btn btnmio btn-primary navbar-btn"><big><big>gruppi</big></big></a></div>
                    </div>
                    <div class="row">
                        <div> <a href="" class="btn btnmio btn-primary navbar-btn"><big><big>crea gruppo</big></big></a></div>
                    </div>
                    <div class="row">
                        <div><a href="" class="btn btnmio btn-primary navbar-btn"><big><big>Dati utente</big></big></a></div>
                    </div>
                </div>
                <div class="col-lg-4">
                    <table class="table">
                        <!-- qui mettere il for che scorre gli inviti-->
                        <tr class="colore-caselle">
                            <td>
                                nome gruppo
                            </td>
                            <td>
                                <form action="servletRisposteInviti" method=POST>
                                    <button type="submit" class="btn btn-danger" name="bottone" value="rifiuta">Rifiuta</button>&nbsp;
                                    <button type="submit" class="btn btn-success" name="bottone" value="accetta">accetta</button>
                                    <input type="hidden" name="gname" value="listagname.get(i)">
                                    <input type="hidden" name="gadmin" value="listagadmin.get(i)">
                                </form>
                            </td>
                        </tr>
                        <!-- gestire il caso in cui non ci siano inviti con <p>non hai inviti</p> -->
                    </table>
                </div>
                <div class="col-lg-4">
                    <img src="file/Functions.getUserIMG(request)?op=img_profilo" alt="no image." onerror="src='/WebForumPlus/forumIMG/default-no-profile-pic.jpg'" class="img-rounded" style="width: 300px">
                </div>
            </div>
        </div>
        <script src="../bootstrapJS/jquery.js"></script>
        <script src="../bootstrapJS/bootstrap.min.js"></script>
    </body>
</html>