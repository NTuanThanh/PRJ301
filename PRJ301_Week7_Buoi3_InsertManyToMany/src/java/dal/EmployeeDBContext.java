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
import model.Certificate;
import model.Employee;

public class EmployeeDBContext extends DBContext {
    public void insert(Employee e)
    {
        try {
            String sql_insert_emp = "INSERT INTO [Employee]\n" +
                    "           ([eid]\n" +
                    "           ,[ename]\n" +
                    "           ,[gender]\n" +
                    "           ,[dob]\n" +
                    "           ,[username])\n" +
                    "     VALUES\n" +
                    "           (?\n" +
                    "           ,?\n" +
                    "           ,?\n" +
                    "           ,?\n" +
                    "           ,?)";
            String sql_insert_cert = "INSERT INTO [Employee_Certificate]\n" +
                    "           ([eid]\n" +
                    "           ,[cid])\n" +
                    "     VALUES\n" +
                    "           (?\n" +
                    "           ,?)";
            connection.setAutoCommit(false);
            PreparedStatement stm_insert_emp = connection.prepareStatement(sql_insert_emp);
            stm_insert_emp.setInt(1, e.getId());
            stm_insert_emp.setString(2, e.getName());
            stm_insert_emp.setBoolean(3, e.isGender());
            stm_insert_emp.setDate(4, e.getDob());
            stm_insert_emp.setString(5, e.getAccount().getUsername());
            stm_insert_emp.executeUpdate();
            
            for (Certificate cert : e.getCerts()) {
                PreparedStatement stm_insert_cert = connection.prepareStatement(sql_insert_cert);
                stm_insert_cert.setInt(1, e.getId());
                stm_insert_cert.setInt(2, cert.getId());
                stm_insert_cert.executeUpdate();
            }
            connection.commit();
        } catch (SQLException ex) {
            Logger.getLogger(EmployeeDBContext.class.getName()).log(Level.SEVERE, null, ex);
            try {
                connection.rollback();
            } catch (SQLException ex1) {
                Logger.getLogger(EmployeeDBContext.class.getName()).log(Level.SEVERE, null, ex1);
            }
        }
        finally
        {
            try {
                connection.setAutoCommit(true);
            } catch (SQLException ex) {
                Logger.getLogger(EmployeeDBContext.class.getName()).log(Level.SEVERE, null, ex);
            }
            //close connection
            //close connection
        }
    }
    public ArrayList<Employee> getEmployees(){
        ArrayList<Employee> employees = new ArrayList<>();
        try {
            String sql_employee = "select eid, ename from Employee";
            connection.setAutoCommit(false);
            PreparedStatement stm = connection.prepareStatement(sql_employee);
            ResultSet rs = stm.executeQuery();
            while(rs.next()){
               Employee e = new Employee(); 
               e.setId(rs.getInt("eid"));
               e.setName(rs.getString("ename"));
               String sql_employee_certificate = "select c.cid, c.cname from Employee as e "
                    + "Inner Join Employee_Certificate as ec on e.eid = ec.eid\n" +
                    " Inner Join Certificate as c on c.cid = ec.cid where e.eid = ?";
               stm = connection.prepareStatement(sql_employee_certificate);
               stm.setInt(1, e.getId());
               ResultSet rs_employee_certificate= stm.executeQuery();
               while(rs_employee_certificate.next()){
                   Certificate c = new Certificate();
                   c.setId(rs_employee_certificate.getInt("cid"));
                   c.setName(rs_employee_certificate.getString("cname"));
                   e.getCerts().add(c); 
               }
               employees.add(e); 
            }
            String sql_employee_certificate = "select e.eid, e.ename, c.cid, c.cname from Employee as e "
                    + "Inner Join Employee_Certificate as ec on e.eid = ec.eid\n" +
                    " Inner Join Certificate as c on c.cid = ec.cid";
        } catch (SQLException ex) {
            Logger.getLogger(EmployeeDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
       return employees;
    }
    public ArrayList<Employee> getAllIdEmployee(){
        ArrayList<Employee> idEmployees = new ArrayList<>(); 
        try {
            String sql = "select eid from Employee"; 
            PreparedStatement stm = connection.prepareStatement(sql);
            ResultSet rs = stm.executeQuery();
            while(rs.next()){
                Employee e = new Employee();
                e.setId(rs.getInt("eid"));
                idEmployees.add(e); 
            }
        } catch (SQLException ex) {
            Logger.getLogger(EmployeeDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return idEmployees; 
    }
    public void DeleteAll(){
        try {
            String sql = "delete from Employee_Certificate"; 
            connection.setAutoCommit(false);
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.executeUpdate(); 
            connection.commit();
        } catch (SQLException ex) {
            Logger.getLogger(EmployeeDBContext.class.getName()).log(Level.SEVERE, null, ex);
            try {
                connection.rollback();
            } catch (SQLException ex1) {
                Logger.getLogger(EmployeeDBContext.class.getName()).log(Level.SEVERE, null, ex1);
            }
        }finally{
            try {
                connection.setAutoCommit(true);
            } catch (SQLException ex) {
                Logger.getLogger(EmployeeDBContext.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    public void Update_Employee_Certificate(Employee e)
    {
        try {
            String sql_insert_cert = "INSERT INTO [Employee_Certificate]\n" +
                    "           ([eid]\n" +
                    "           ,[cid])\n" +
                    "     VALUES\n" +
                    "           (?\n" +
                    "           ,?)";
            connection.setAutoCommit(false);
            PreparedStatement stm_insert_cert = connection.prepareStatement(sql_insert_cert);
            for (Certificate cert : e.getCerts()) {
                stm_insert_cert.setInt(1, e.getId());
                stm_insert_cert.setInt(2, cert.getId());
                stm_insert_cert.executeUpdate();
            }
            connection.commit();
        } catch (SQLException ex) {
            Logger.getLogger(EmployeeDBContext.class.getName()).log(Level.SEVERE, null, ex);
            try {
                connection.rollback();
            } catch (SQLException ex1) {
                Logger.getLogger(EmployeeDBContext.class.getName()).log(Level.SEVERE, null, ex1);
            }
        }
        finally
        {
            try {
                connection.setAutoCommit(true);
            } catch (SQLException ex) {
                Logger.getLogger(EmployeeDBContext.class.getName()).log(Level.SEVERE, null, ex);
            }
            //close connection
            //close connection
        }
    }
}
