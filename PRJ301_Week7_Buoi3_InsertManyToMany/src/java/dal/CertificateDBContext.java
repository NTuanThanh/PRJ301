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

public class CertificateDBContext extends DBContext {
    public ArrayList<Certificate> getCerts()
    {
        ArrayList<Certificate> certs = new ArrayList<>();
        try {
            String sql = "SELECT cid,cname FROM Certificate";
            PreparedStatement stm = connection.prepareStatement(sql);
            ResultSet rs = stm.executeQuery();
            while(rs.next())
            {
                Certificate c = new Certificate();
                c.setId(rs.getInt("cid"));
                c.setName(rs.getString("cname"));
                certs.add(c);
            }
        } catch (SQLException ex) {
            Logger.getLogger(CertificateDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return certs;
    }
}
