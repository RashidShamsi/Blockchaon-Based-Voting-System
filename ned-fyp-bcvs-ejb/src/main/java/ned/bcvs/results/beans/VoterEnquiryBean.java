/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ned.bcvs.results.beans;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Local;
import javax.ejb.Stateful;
import javax.inject.Inject;
import ned.bcvs.results.beans_local_interfaces.VoterEnquiryLocal;
import ned.bcvs.cdi.qualifier.jdbcfypdb;
import ned.bcvs.dao.Voters;

/**
 *
 * @author StackHouse
 */
@Stateful
@Local( VoterEnquiryLocal.class)
public class VoterEnquiryBean implements VoterEnquiryLocal {
    
    @Inject
    @jdbcfypdb
    private Connection con;
    
    private PreparedStatement ps ;
    private ResultSet rs ; 
    
    @Override
    public Voters voterEnquiry(String id){
        
        Voters vDao = null ;
        try {
            String querry = "select * from voter where voterId = " + id ;
            ps = con.prepareStatement(querry);
            rs = ps.executeQuery();
            while (rs.next()) {                
                vDao = new Voters(
                        rs.getString("VoterHash"),
                        rs.getString("VoterId"),
                        rs.getString("VoterFirstName"),
                        rs.getString("VoterLastName"),
                        rs.getInt("VoterContactNumber"),
                        rs.getInt("ConstituencyID"),
                        rs.getString("VoterBiometrics"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(VoterEnquiryBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        return vDao ;
    }
}
