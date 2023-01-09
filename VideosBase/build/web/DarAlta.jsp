<%-- 
    Document   : DarAlta
    Created on : 19/07/2021, 03:18:25 PM
    Author     : dtic
--%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.Connection"%>
<%@page import="config.Conexion"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:useBean id="obj" class="com.servlet.java.ListBean" scope="page"/>
<link href="Estilos/newcss3.css" rel="stylesheet" type="text/css"/>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        
            
        <form <form class="form-register" action="DarAltaServ" method="post" enctype="multipart/form-data" autocomplete="off">
            <h4>Dar de alta un video</h4>
            <input class="controls" type="text" name="Nombre" required placeholder="Ingrese el nombre" required>
            <h3>Escriba el nombre completo del archivo</h3>
            <input class="controls" type="text" name="file" enctype="multipart/form-data" placeholder ="Ejemplo = ´video.mp4´" required>
            <input class= "controls" type="text" name="descripcion" placeholder =" ingrese la descripcion del video" required>
            
            <h3>Eliga la carpeta donde lo almacenó</h3>
            <select class="controls" name="carpeta" id="list" required>
                <c:forEach var="item" items="${obj.items}">
                    <option>${item}</option>
                </c:forEach>
            </select>
            <input class="controls" type="Text" name="Productor" placeholder="Ingrese el productor" required>
            <input class="controls" type="Text" name="Etiqueta" placeholder="Ingrese la etiqueta que pertenece" required>
            <input class="botons" type="submit" name="submit">
            <p>
                <a class="fcc-btn" href="file-list.jsp">ver lista</a>
                <a class ="fcc-btn" href="addNewFolder.jsp">Nueva carpeta</a>
            </p>
            <p>
                <a class ="fcc-btnIndex" href ="index.jsp">Nuevo archivo</a>
            </p>
        </form>
    </body>
</html>
