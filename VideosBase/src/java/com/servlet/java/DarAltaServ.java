/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;


public class DarAltaServ extends HttpServlet {

    PrintWriter out = null;
    Connection con = null;
    PreparedStatement ps = null;
    HttpSession session = null;
    ResultSet rs = null;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet DarAltaServ</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet DarAltaServ at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //String uploadPath = "C:/Almacenamiento";
        String Carpeta = request.getParameter("carpeta");
        String fileName = request.getParameter("file");
        
        String folderName = Carpeta;

        //checa si el archivo existe en la raiz.
        //uploadPath + File.separator + folderName + File.separator + fileName
        File f = new File(File.separator + "volume1" + File.separator + "Videoteca" + File.separator + folderName + File.separator + fileName);
        System.out.println("el archivo a buscar se encuentra en el: " + f);
        
        if (f.exists()) {
            try {
                out = response.getWriter();
                session = request.getSession(false);
                String Nombre = request.getParameter("Nombre");
                //String Categoria = request.getParameter("Categoria");
                String Descripcion = request.getParameter("descripcion");
                String Productor = request.getParameter("Productor");
                String Etiqueta = request.getParameter("Etiqueta");

                String path = folderName + File.separator + fileName;
                //busca si ya hay un registro en la base de datos.
                //el codigo por alguna razon dejó de funcionar ya no busca si exite o no.
                try {
                    int codigo = 1;
                    con = Conexion.getConnection();
                    java.sql.Statement stmt = con.createStatement();
                    ResultSet r = stmt.executeQuery("SELECT NombreArchivo, path FROM videos");
                    while (r.next()) {
                        // print the values for the current row.
                        String i = r.getString("NombreArchivo");
                        if (fileName.equals(i)) {
                            codigo = 0;
                        }
                    }
                    r.close();
                    switch (codigo) {
                        case 0:
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
                            //con =  (Connection) Conexion.getConnection();
                            //con=cn.getConnection();
                            System.out.println("Conexion exitosa");
                            String sql = "insert into videos (Nombre,NombreArchivo,Descripcion,path,productor,etiquetas)values(?,?,?,?,?,?)";
                            ps = con.prepareStatement(sql);
                            ps.setString(1, Nombre);
                            ps.setString(2, fileName);
                            ps.setString(3, Descripcion);
                            ps.setString(4, path);
                            ps.setString(5, Productor);
                            ps.setString(6, Etiqueta);
                            int status = ps.executeUpdate();
                            if (status > 0) {
                                session.setAttribute("fileName", fileName);
                                String msg = "" + fileName + "archivo subido con exito";
                                request.setAttribute("msg", msg);
                                RequestDispatcher rd = request.getRequestDispatcher("/success.jsp");
                                rd.forward(request, response);
                                System.out.println("archivo subido con exito");
                                
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
                RequestDispatcher errorDescargaVacia = request.getRequestDispatcher("/vistasErrores/errorArchivoVacio.html");
                errorDescargaVacia.forward(request, response);
            }
        } else {
            RequestDispatcher errorDescargaVacia = request.getRequestDispatcher("/vistasErrores/errorArchivoNoEncontrado.html");
            errorDescargaVacia.forward(request, response);
        }
    }
}
