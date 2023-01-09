/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.servlet.java;

import config.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author dtic
 */
public class ListBean {
    
    Conexion cn = new Conexion();
    Connection con = null;
    PreparedStatement ps = null;
    ResultSet rs = null;
    
    
    
    public List<String> getItems() throws SQLException{
        String sql = "select * from carpetas";
        con = Conexion.getConnection();
        ps= con.prepareStatement(sql);
        rs=ps.executeQuery();
        List<String> list = new ArrayList<String>();
        while(rs.next()){
            list.add(rs.getString(2));
        }
        
        
        return list;
    }
    
    
}
