package com.servlet.java;


import config.Conexion;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;


@WebServlet(name = "UploadServlet", urlPatterns = ("/UploadServlet"))

public class UploadServlet extends HttpServlet {

    PrintWriter out = null;
    Connection con = null;
    PreparedStatement ps = null;
    HttpSession session = null;

    ResultSet rs = null;
    public static String folderNameDestino;
    public static String Nombre;
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            out = response.getWriter();
            session = request.getSession(false);
            //String nuevaCarpeta = request.getParameter("nuevaCarpeta");
            String Carpeta = request.getParameter("carpeta");
            String folderName = Carpeta;
            
            String uploadPath = File.separator + "volume1" + File.separator + "Videoteca" + File.separator + folderName;
            System.out.println("el nuevo link de prueba es...................... " + uploadPath);
            //original = "C:/Almacenamiento" + File.separator + folderName se encuentra en el cmd = "Videotecasy" + File.separator + "prueba" + File.separator + folderName;;
            File dir = new File(uploadPath);
        
            if (!dir.exists()) {
                
                dir.mkdirs();
            }

            Part filePart = request.getPart("file");
            String Nombre = request.getParameter("Nombre");
            //String NombreArchivo = request.getParameter("NombreArchivo");
            //String Categoria = request.getParameter("Categoria");
            String Descripcion = request.getParameter("descripcion");
            String fileName = filePart.getSubmittedFileName();
            String productor = request.getParameter("Productor");
            String Etiqueta = request.getParameter("Etiqueta");
            
            String path = folderName + File.separator + fileName;
            System.out.println("filename:" + fileName);
            System.out.println("Path:" + uploadPath);
            System.out.println("path2:" + path);
            System.out.println("name:" + fileName);
            System.out.println("la carpeta en donde se encuentra es en: " + Carpeta);
            
            System.out.println("name:" + fileName);
            //System.out.println("nueva dirrecion: " + ArchivoDestino);
            InputStream is = filePart.getInputStream();
            Files.copy(is, Paths.get(uploadPath + File.separator + fileName), StandardCopyOption.REPLACE_EXISTING);
            //codigo que busca si hay un archivo que se repite.
            try {
                int codigo = 1;
                con = Conexion.getConnection();
                java.sql.Statement stmt = con.createStatement();
                ResultSet r = stmt.executeQuery("SELECT NombreArchivo, path FROM videos");
                while (r.next()) {
                    // print the values for the current row.
                    String i = r.getString("NombreArchivo");
                    String p = r.getString("path");
                    System.out.println(r.getString("NombreArchivo"));
                    if (fileName.equals(i)) {
                        codigo = 0;
                    }
                }
                r.close();
                switch (codigo) {
                    case 0:
                        System.out.println("El video ya esta en la base de datos");
                        System.out.println(codigo);
                        try {
                            if (ps != null) {
                                ps.close();
                            }
                            if (con != null) {
                                con.close();
                            }
                        } catch (SQLException e) {
                            out.print(e);
                        }
                        RequestDispatcher errorRepetido = request.getRequestDispatcher("/vistasErrores/errorArchivoRepetido.html");
                        errorRepetido.forward(request, response);
                        break;
                    case 1:
                        System.out.println("nuevo registro en la base de datos");
                        System.out.println(codigo);
                        //con =  (Connection) Conexion.getConnection();
                        //con=cn.getConnection();
                        System.out.println("Conexion exitosa");
                        String sql = "insert into videos (Nombre,NombreArchivo,Descripcion,path,productor,etiquetas)values(?,?,?,?,?,?)";
                        ps = con.prepareStatement(sql);
                        ps.setString(1, Nombre);
                        ps.setString(2, fileName);
                        ps.setString(3, Descripcion);
                        ps.setString(4, path);
                        ps.setString(5, productor);
                        ps.setString(6, Etiqueta);
                        
                        int status = ps.executeUpdate();
                        if (status > 0) {
                            session.setAttribute("fileName", fileName);
                            String msg = "" + fileName + "archivo subido con exito";
                            request.setAttribute("msg", msg);
                            RequestDispatcher rd = request.getRequestDispatcher("/success.jsp");
                            rd.forward(request, response);
                            System.out.println("archivo subido con exito");
                            System.out.println("el archivo se encuentra en la direccion: " + uploadPath);
                            codigo = 1;
                        }
                        break;
                }
            } catch (SQLException e) {
                System.out.println("Exception" + e);
                System.out.println("Exception: " + e);
            } finally {
                try {
                    if (ps != null) {
                        ps.close();
                    }
                    if (con != null) {
                        con.close();
                    }
                } catch (SQLException e) {
                    out.print(e);
                }
            }
        } catch (IOException | ServletException e) {
            out.print("Exception: " + e);
            System.out.println("Exception2" + 2);
            /*RequestDispatcher errorDescargaVacia = request.getRequestDispatcher("/vistasErrores/errorArchivoVacio.html");
            errorDescargaVacia.forward(request, response);*/
        }
    }
}
