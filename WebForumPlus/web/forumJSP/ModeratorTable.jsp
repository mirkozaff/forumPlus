<%@page contentType="text/html" pageEncoding="UTF-8" import="java.util.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Lista prodotti</title>
        <link rel="stylesheet" href="<c:url value="/css/jquery.dataTables.css"/>"/>
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
        

        <h2>Lista prodotti</h2>
        <table id="groupstable" >
            <thead>
                <tr>
                    <th>Nome</th>
                    <th>Admin</th>
                    <th>N° parteipanti</th>
                    <th>Visibilità</th>
                    <th>N° post</th>
                </tr>
            </thead>
            <tbody>
                <%-- Il contenuto della tabella è popolato da una chiamata AJAX --%>
            </tbody>
        </table>
    </body>
</html>
