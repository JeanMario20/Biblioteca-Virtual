
<%@page import="java.sql.Statement"%>
<%@page import="java.sql.Timestamp"%>
<%@page import="config.Conexion"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.Connection"%>
<link href="Estilos/BarraBusqueda.css" rel="stylesheet" type="text/css"/>
<link href="Estilos/Tabla3.css" rel="stylesheet" type="text/css"/>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
    <center>
        <%!
            Conexion cn = new Conexion();
            Connection con = null;
            Statement ps;
            ResultSet rs;
            
            String sql = "select * from videos";
        %>

        <div class="box">
                <form class="search-box" autocomplete="off">
                    <input class="inputText" type="text" name="txtBuscar" placeholder="ingrese la etiqueta a buscar">
                    <input class ="inputBtn" type="submit" value="Buscar">
                </form>
            </div>
            <div class = "formatButtons">
                <a href="index.jsp" class="otherButtons">volver a inicio</a>
                <a href="file-list.jsp" class="otherButtons">ver todos los videos</a>
            </div>
            <%
                ps = Conexion.getConnection().createStatement();
                rs = ps.executeQuery(sql);
                String nomBuscar = request.getParameter("txtBuscar");
                if (nomBuscar != null) {
                    ps = Conexion.getConnection().createStatement();
                    rs = ps.executeQuery("select * from videos where etiquetas LIKE" + "'%" + nomBuscar + "%'");
                }
            %>

            <table class="content-table">
                <thead>
                    <tr>
                        <th>Nombre</th>
                        <th>Nombre del archivo</th>
                        <th>Descripción</th>
                        <th>Dirección de almacenamiento</th>
                        <th>Productor</th>
                        <th>Etiquetas</th>
                        <th>Descarga</th>
                    </tr>
                </thead>
                <tbody>
                    <%
                        while (rs.next()) {
                    %>
                    <tr>
                        
                        <td><%=rs.getString(2)%></td>
                        <td><%=rs.getString(3)%></td>
                        <td><%=rs.getString(4)%></td>
                        <td><%=rs.getString(5)%></td>
                        <td><%=rs.getString(6)%></td>
                        <td><%=rs.getString(7)%></td>
                        
                        <td><a class="button" href="DownloadServlet?fileName=<%=rs.getString(3)%>">descargar</a></td>
                    </tr>
                    <%
                        }
                    %>
                </tbody>
            </table> <br>
        
    </center>
</body>
</html>
