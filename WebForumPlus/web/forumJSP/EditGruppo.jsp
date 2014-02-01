<%-- 
    Document   : CreaGruppo
    Created on : 28-gen-2014, 9.13.36
    Author     : giovanni
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>

<html lang="en">
    <head>
        <title>editgruppo</title>
        <!-- Bootstrap core CSS -->
        <link href="../bootstrapCSS/bootstrap.css" rel="stylesheet">
        <!-- Custom styles for this template -->
        <link href="../forumPlusCSS/editgruppo.css" rel="stylesheet">
        <!-- HTML5 shim and Respond.js IE8 support of HTML5 elements and media queries -->
        <!--[if lt IE 9]>
          <script src="../bootstrap-3.0.0/assets/js/html5shiv.js"></script>
          <script src="../bootstrap-3.0.0/assets/js/respond.min.js"></script>
        <![endif]-->
    </head>
    <body>
        
        <SCRIPT LANGUAGE="JavaScript">
            function contr() {
                if (document.forms['modulo'].elements['nomegruppo'].value == "") {
                    alert("il campo \"nome del gruppo\" non puo' essere vuoto");
                    return false;
                }
                return true;
            }
        </SCRIPT>
        
        <nav class="navbar navbar-default navbar-fixed-top" role="navigation">
            &nbsp;
            <button onclick="window.location.href = '/forumJSP/HomePage2.jsp'" type="button" class="btn btn-primary navbar-btn">HOME</button>
            <div class="nav navbar-nav navbar-right">                             
                <button onclick="window.location.href='/Controller?op=logout'" type="submit" class="btn btn-primary navbar-btn">Logout</button>
                &nbsp;
            </div>
        </nav>
        <div class="row centraverticale">
            <div class="col-md-4"></div>
            <div class="col-md-4">
                <div class=contenitore-azzurro>
                    <form action="EditGruppoDB" method="POST" name="modulo" onSubmit="return contr()">
                        <div class=form-group> 
                            <label>Nome del gruppo</label> 
                            <c:choose>
                                <c:when test="${empty requestScope.gname}">         <!-- controllo se esiste la variabile gname-->
                                    <input type="text" class="form-control" name="nomegruppo"/>
                                </c:when>
                                <c:otherwise>
                                    <input type="text" class="form-control" name="nomegruppo" value="${requestScope.gname}"> <!-- gname esiste e la metto nel textfield-->
                                    <input type="hidden" name="gname" value="${requestScope.gname}">
                                </c:otherwise>
                            </c:choose>
                        </div>
                        <div>      
                            <label>Visibilità gruppo:</label>
                            <select name="visibility" >
                                <option value="true" ${requestScope.visibility == 'true' ? 'selected' : ''}>Pubblico</option>
                                <option value="false" ${requestScope.visibility  == 'false' ? 'selected' : ''}>Privato</option>
                            </select>
                        </div>
                        &nbsp;
                        <div class="form-group"> 
                            <label>Invita i tuoi amici</label>
                            <c:choose>
                                <c:when test="${not empty requestScope.listavisualizzata}">     <!-- prendo la lista di utenti da mettere nelle checkbox (l'ho creata nel Controller)-->
                                    <c:forEach items="${requestScope.listavisualizzata}" var="nomeutente"> <!-- aggiungo le checkbox-->
                                        <div class="checkbox">
                                            <label>
                                                <input type="checkbox" name="utente" value="<c:out value="${nomeutente}"/>">
                                                <c:out value="${nomeutente}"/>
                                            </label>
                                        </div>
                                    </c:forEach>
                                </c:when>
                                <c:otherwise>
                                    <p class="testorosso">non ci sono utenti da invitare</p>
                                </c:otherwise>
                            </c:choose>
                            <c:choose>
                                <c:when test="${empty requestScope.gname}">    <!-- se la variabile gname esiste vuol dire che sto modificando un gruppo già esistente quindi creo il bottone appropriato-->
                                    <div class="centra">
                                        <input class="btn btn-lg btn-success" type="submit" name="bottone" value="crea gruppo">
                                    </div>
                            </form> 
                        </c:when>
                        <c:otherwise>
                            <div class="centra">
                                <input class="btn btn-lg btn-success" type="submit" name="bottone" value="modifica gruppo">
                            </div>
                            </form>
                        </c:otherwise>
                    </c:choose>
                </div>
            </div>
            <div class="col-md-4">
            </div>
        </div>
        <script src="../bootstrapJS/jquery.js"></script>
        <script src="../bootstrapJS/bootstrap.min.js"></script>
    </body>
</html>