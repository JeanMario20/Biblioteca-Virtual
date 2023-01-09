<%-- 
    Document   : addNewFolder
    Created on : 22/06/2021, 11:32:51 AM
    Author     : dtic
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:useBean id="obj" class="com.servlet.java.ListBean" scope="page"/> 
<link href="Estilos/newcss3.css" rel="stylesheet" type="text/css"/>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <form class="form-register" action="UploadNewFolderServlet" method="post" enctype="multipart/form-data" autocomplete="off">
            <h4>Crear un nuevo archivo</h4>
            <input class="controls"type="text" name="Nombre" placeholder="Ingrese el nombre" required>
            <input class="controls" type="file" name="file" enctype="multipart/form-data" required>
            <input class= "controls" type="text" name="descripcion" placeholder =" ingrese la descripcion del video" required>
            
            <h3>Escriba el nombre de la nueva carpeta</h3>
            <input class="controls" id="hid" name="nuevaCarpeta" required placeholder="Ingrese nombre de la nueva carpeta" required>
            <input class="controls" type="Text" name="Productor" placeholder="Ingrese el productor" required>
            <input class="controls" type="Text" name="Etiqueta" placeholder="Ingrese la etiqueta que pertenece" required>
            <input class="botons" type="submit" name="submit">
            <p>
                <a class="fcc-btn" href="file-list.jsp">ver lista</a>
                <a class ="fcc-btn" href="index.jsp">Nuevo archivo</a>
            </p>
            
            <p>
                <a class ="fcc-btnAlta" href ="DarAlta.jsp">Alta videos almacenados</a>
            </p>
        </form>
        <br><a href="file-list.jsp">ver lista</a>
    <script src="showHideElements.js" type="text/javascript"></script>
</body>
</html>
