/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.servlet.java;

import static com.servlet.java.UploadServlet.Nombre;

import config.Conexion;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;

import java.sql.ResultSet;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.sql.Connection;
import java.sql.PreparedStatement;

@MultipartConfig
@WebServlet(name = "DownloadServlet", urlPatterns = {"DownloadServlet"})
public class DownloadServlet extends HttpServlet {

    public static int BUFFER_SIZE = 1024 * 100;
    public static final String UPLOAD_DIR = "Videoteca";
    public static String fileName = null;
    public static String Carpeta;
    String Carperta;
    Connection con;
    PreparedStatement ps;
    HttpSession session;

    ResultSet rs;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet DownloadServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet DownloadServlet at " + "</h1>");
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
        //processRequest(request, response);
        fileName = request.getParameter("fileName");
        Carpeta = request.getParameter("carpeta");
        
        //esto sirve.
        try {
            String sql = "select path from videos where NombreArchivo LIKE" + "'%" + fileName + "'" ;
            con = Conexion.getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();

            while (rs.next()) {
                if (fileName.equals(rs.getString(1))) {
                    System.out.println("se encontro un dato similar"+rs.getString(1));
                }else{
                    Carpeta = rs.getString(1);
                    System.out.println("se encuentran almacenados "+ Carpeta);
                    
                }
                
            }
            //con = Conexion.getConnection();
            //java.sql.Statement stmt = con.createStatement();
            /*try (ResultSet r = stmt.executeQuery("select * from videos")) {
                String i = r.getString("path");
                while (r.next()) {
                    System.out.println(r);
                    System.out.println(r.getString("path"));
                    System.out.println(i);
                    
                    if (fileName.equals(i)) {
                        System.out.println(i);
                        System.out.println("pruebaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa" + fileName);  
                    } else {
                        System.out.println("no se encuentra la url");
                    }
                }
            }catch(Exception e){
                System.out.println("error en el select");
            }*/
        } catch (Exception e) {
            System.out.println(e);
        }

        if (fileName == null || fileName.equals("")) {
            response.setContentType("text/html");
            response.getWriter().print("<h3>File " + fileName + "<- aqui deberia ir el nombre del archivo, El archivo no se encuentra almacenado......</h3>");
        } else {
            String applicationPath = File.separator + "volume1";
            String downloadPath = applicationPath + File.separator + UPLOAD_DIR;
            //applicationPath + File.separator + UPLOAD_DIR
            String filePath = downloadPath + File.separator + Carpeta;
            //downloadPath + File.separator + Carpeta;
            System.out.println(fileName);
            System.out.println(filePath);
            System.out.println("fileName:" + fileName);
            System.out.println("filePath:" + filePath);
            File file = new File(filePath);
            OutputStream outStream = null;
            FileInputStream inputStream = null;

            if (file.exists()) {
                String mimeType = "application/octet-stream";
                response.setContentType(mimeType);

                String headerKey = "Content-Disposition";
                //String headerValue = String.format("attachment; fileName=\"%s", file.getName());
                String headerValue = String.format("attachment; filename=\"%s\"", file.getName());
                response.setHeader(headerKey, headerValue);

                try {
                    outStream = response.getOutputStream();
                    inputStream = new FileInputStream(file);
                    byte[] buffer = new byte[BUFFER_SIZE];
                    int bytesRead = -1;

                    while ((bytesRead = inputStream.read(buffer)) != -1) {
                        outStream.write(buffer, 0, bytesRead);
                    }
                } catch (IOException ioExObj) {
                    System.out.println("Exception while perfoming the I/O Operation?= " + ioExObj.getMessage());
                } finally {
                    if (inputStream != null) {
                        inputStream.close();
                    }
                    outStream.flush();
                    if (outStream != null) {
                        outStream.close();
                    }
                }
            } else {
                response.setContentType("text/html");
                response.getWriter().print("<h3>File" + fileName + "Is no present......... </h3>");

            }
        }
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
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
    
    

}
