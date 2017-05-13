/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ned.bcvs.admin.beans;

import ned.bcvs.admin.beans_local_interfaces.AdminElectionPartiesLocal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.inject.Inject;
import ned.bcvs.cdi.qualifier.jdbcfypdb;
import ned.bcvs.dao.ElectionParty;

/**
 *
 * @author StackHouse
 */
@Stateless
@Local(AdminElectionPartiesLocal.class)
public class AdminElectionPartiesBean implements AdminElectionPartiesLocal {

    @Inject
    @jdbcfypdb
    private Connection con ;
    
    private PreparedStatement ps ;
    private ResultSet rs ;
    
    @Override
    public List<ElectionParty> electionPartiesListGenerator(){
        List<ElectionParty> list = new ArrayList<>();
        String sql = "select * from electionparty" ;
        try {
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {                
                list.add(new ElectionParty(
                        rs.getInt("ElectionPartyId"),
                        rs.getString("ElectionPartyName"),
                        rs.getInt("ElectionSymbolId")));
            }
        } catch (SQLException ex) {
            Logger.getLogger(AdminElectionPartiesBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list ;
    }
}
