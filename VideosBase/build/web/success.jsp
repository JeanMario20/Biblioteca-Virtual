<%-- 
    Document   : success
    Created on : 7/06/2021, 12:40:53 PM
    Author     : dtic

<%@taglib uri="http//java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"  %>
--%>
<link href="Estilos/newcss3.css" rel="stylesheet" type="text/css"/>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
    <center>
        <div class="form-register">
        <c:if test="${requestScope.msg!=null}">
            <h3><c:out value="${requestScope.msg}"></c:out></h3>
        </c:if><br><br>
        <c:if test = "${sessionScope.fileName!=null}">
            <c:set var ="file" scope="session" value = "${sessionScope.fileName}"/>
        </c:if>
        <a class="fcc-btn" href = "<c:url value = "DownloadServlet?fileName=${file}"/>">Descargar</a>
        <a class="fcc-btn" href = "<c:url value="file-list.jsp"/>">Ver lista</a>
        </div>
    </center>
    </body>
</html>
