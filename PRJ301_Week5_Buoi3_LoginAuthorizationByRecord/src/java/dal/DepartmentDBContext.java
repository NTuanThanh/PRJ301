/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dal;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Department;

/**
 *
 * @author pv
 */
public class DepartmentDBContext extends DBContext{
    public ArrayList<Department> getDeparts(){
        ArrayList<Department> departs = new ArrayList<>(); 
        try {
            String sql = "Select did,dname From Department";
            PreparedStatement stm = connection.prepareStatement(sql);
            ResultSet rs = stm.executeQuery(); 
            while(rs.next()){
               Department depart = new Department(); 
               depart.setDid(rs.getInt("did"));
               depart.setDname(rs.getString("dname"));
               departs.add(depart); 
            }
        } catch (SQLException ex) {
            Logger.getLogger(DepartmentDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
       return departs;
    }
}
