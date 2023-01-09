
package config;

import com.mysql.jdbc.Connection;
import java.sql.DriverManager;
import java.sql.*;


public class Conexion {
    
    public static Connection getConnection(){
        Connection con = null;
        try{
            Class.forName("com.mysql.jdbc.Driver");
            con = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/videos","@Dm1n_298495","Tit4np420@");
            //jdbc:mysql://localhost:3306/videos","root","DelT@HQ19980%"
            //codigo que si funciona
        }catch (Exception e) {
            System.err.println("Error" + e);
        }
        return con;
    }
    
    /*public  Connection getConnection(){
        return con;
    }*/
    
    public static void main (String[] args){
        Connection con = null;
        con = Conexion.getConnection();
    }
}
