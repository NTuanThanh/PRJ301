/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dal;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Account;

/**
 *
 * @author pv
 */
public class AccountDBContext extends DBContext{
    public Account getAccount(String user, String pass){
        String sql = "select username, [password], fullname from Account\n" +
                    "where username = ? and [password] = ?"; 
        PreparedStatement stm = null; 
        try { 
            stm = connection.prepareStatement(sql);
            stm.setString(1, user);
            stm.setString(2, pass);
            ResultSet rs = stm.executeQuery(); 
            while(rs.next()){
               Account account = new Account(); 
               account.setUsername(rs.getString("username"));
               account.setPassword(rs.getString("password"));
               account.setFullname(rs.getString("fullname"));
               return account;
            }
        } catch (SQLException ex) {
            Logger.getLogger(AccountDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            if(stm != null){
                try {
                    stm.close();
                } catch (SQLException ex) {
                    Logger.getLogger(AccountDBContext.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if(connection != null){
                try {
                    connection.close();
                } catch (SQLException ex) {
                    Logger.getLogger(AccountDBContext.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return null;   
    }
}
