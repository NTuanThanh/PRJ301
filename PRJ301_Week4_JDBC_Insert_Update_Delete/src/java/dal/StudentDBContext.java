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
import model.Student;

/**
 *
 * @author pv
 */
public class StudentDBContext extends DBContext{
     public ArrayList<Student> getStudents(int did){
         ArrayList<Student> students = new ArrayList<>();  
         try {
             String sql = "select sid,sname, gender, dob, d.did, dname from Student s "
                     + "inner join Department d on s.did = d.did";
             if(did != -1){ 
                 sql += " where d.did = ?";
             }
             PreparedStatement stm = connection.prepareCall(sql);
             if(did != -1){
                stm.setInt(1, did);
             }
             ResultSet rs = stm.executeQuery(); 
             while(rs.next()){
                Student s = new Student(); 
                s.setId(rs.getInt("sid"));
                s.setName(rs.getString("sname"));
                s.setGender(rs.getBoolean("gender"));
                s.setDob(rs.getDate("dob"));
                Department d = new Department(); 
                d.setDid(rs.getInt("did"));
                d.setDname(rs.getString("dname"));
                s.setDepartment(d);
                students.add(s); 
             }
         } catch (SQLException ex) {
             Logger.getLogger(StudentDBContext.class.getName()).log(Level.SEVERE, null, ex);
         }
         return students; 
     }  
     public void Insert(Student s){
        String sql = "INSERT INTO [Student]\n" +
                        "           ([sid]\n" +
                        "           ,[sname]\n" +
                        "           ,[gender]\n" +
                        "           ,[dob]\n" +
                        "           ,[did])\n" +
                        "     VALUES\n" +
                        "           (?\n" +
                        "           ,?\n" +
                        "           ,?\n" +
                        "           ,?\n" +
                        "           ,?)"; 
        PreparedStatement stm = null;
         try { 
            stm = connection.prepareStatement(sql);
            stm.setInt(1, s.getId());
            stm.setString(2, s.getName());
            stm.setBoolean(3, s.isGender());
            stm.setDate(4, s.getDob());
            stm.setInt(5, s.getDepartment().getDid());
            stm.executeUpdate();
         } catch (SQLException ex) {
             Logger.getLogger(StudentDBContext.class.getName()).log(Level.SEVERE, null, ex);
         }
         finally{
             if(stm != null){
                 try {
                     stm.close();
                 } catch (SQLException ex) {
                     Logger.getLogger(StudentDBContext.class.getName()).log(Level.SEVERE, null, ex);
                 }
             }
             if(connection != null){
                 try {
                     connection.close();
                 } catch (SQLException ex) {
                     Logger.getLogger(StudentDBContext.class.getName()).log(Level.SEVERE, null, ex);
                 }
             }
         }
        
     }
     public Student getStudent(int id){
         String sql = "select s.sid, sname, gender, dob, d.did, dname from Student as s inner join Department \n" +
                        "as d on s.did = d.did\n" +
                        "where s.sid = ?";
         Student s = new Student();
         PreparedStatement stm = null; 
         try { 
             stm = connection.prepareStatement(sql);
             stm.setInt(1, id);
             ResultSet rs = stm.executeQuery(); 
             while(rs.next()){ 
                s.setId(rs.getInt("sid"));
                s.setName(rs.getString("sname"));
                s.setDob(rs.getDate("dob"));
                s.setGender(rs.getBoolean("gender"));
                Department d = new Department(); 
                d.setDid(rs.getInt("did"));
                d.setDname(rs.getString("dname"));
                s.setDepartment(d); 
             }
         } catch (SQLException ex) {
             Logger.getLogger(StudentDBContext.class.getName()).log(Level.SEVERE, null, ex);
         }
         finally{
            if(stm != null){
                try {
                    stm.close();
                } catch (SQLException ex) {
                    Logger.getLogger(StudentDBContext.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if(connection != null){
                try {
                    connection.close();
                } catch (SQLException ex) {
                    Logger.getLogger(StudentDBContext.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
         }
        return s;
     }
     public void Update(Student s){
        String sql = "UPDATE [Student]\n" +
                    "   SET [sid] = ?\n" +
                    "      ,[sname] = ?\n" +
                    "      ,[gender] = ?\n" +
                    "      ,[dob] = ?\n" +
                    "      ,[did] = ?\n" +
                    " WHERE sid = ?";
        PreparedStatement stm = null; 
         try { 
             stm = connection.prepareStatement(sql);
             stm.setInt(1, s.getId());
             stm.setString(2, s.getName());
             stm.setBoolean(3, s.isGender());
             stm.setDate(4, s.getDob());
             stm.setInt(5, s.getDepartment().getDid());
             stm.setInt(6, s.getId());
             stm.executeUpdate();
         } catch (SQLException ex) {
             Logger.getLogger(StudentDBContext.class.getName()).log(Level.SEVERE, null, ex);
         }
         finally{
            if(stm != null){
                try {
                    stm.close();
                } catch (SQLException ex) {
                    Logger.getLogger(StudentDBContext.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if(connection != null){
                try {
                    connection.close();
                } catch (SQLException ex) {
                    Logger.getLogger(StudentDBContext.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
         }
     }
}
