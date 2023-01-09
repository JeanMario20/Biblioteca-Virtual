<%-- 
    Document   : index
    Created on : 4/06/2021, 11:10:20 AM
    Author     : dtic
--%>

<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.Connection"%>
<%@page import="config.Conexion"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<link href="Estilos/newcss3.css" rel="stylesheet" type="text/css"/>
<jsp:useBean id="obj" class="com.servlet.java.ListBean" scope="page"/>


<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <form class="form-register" action="UploadServlet" method="post" enctype="multipart/form-data" autocomplete="off">
            <h4>Subir video</h4>
            <input class= "controls" type="text" name="Nombre" placeholder =" ingrese el nombre" required>
            <input class = "controls" type="file" name="file" required>
            <input class= "controls" type="text" name="descripcion" placeholder =" ingrese la descripcion del video" required>
            <h3>Seleccione la carpeta</h3>
            <select class = "controls"name = "carpeta" id = "list" placeholder = "selecciona carpeta" required>
                <c:forEach var="item" items="${obj.items}">
                    <option>${item}</option>
                </c:forEach>
            </select>
            <input class="controls" type="Text" name="Productor" placeholder="Ingrese el productor" required>
            <input class="controls" type="Text" name="Etiqueta" placeholder="ingrese la etiqueta que pertenece" required>
            <input class = "botons" type="submit" name="submit">
            <p>
                <a class="fcc-btn" href="file-list.jsp">ver lista</a>
                <a class ="fcc-btn" href="addNewFolder.jsp">Nueva carpeta</a>
            </p>
            <p>
                <a class ="fcc-btnAlta" href ="DarAlta.jsp">Alta videos almacenados</a>
            </p>
        </form>
    
</body>
</html>
