/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ned.bcvs.admin.beans;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Local;
import javax.ejb.Stateful;
import javax.inject.Inject;
import ned.bcvs.MultiChain_Client.MultiChainClient;
import ned.bcvs.admin.beans_local_interfaces.AdminCandidateOperationsLocal;
import ned.bcvs.cdi.qualifier.jdbcfypdb;
import ned.bcvs.dao.Candidates;
import ned.bcvs.dao.Voters;

/**
 *
 * @author StackHouse
 */
@Stateful
@Local(AdminCandidateOperationsLocal.class)
public class AdminCandidateOperationsBean implements AdminCandidateOperationsLocal{

    @Inject
    @jdbcfypdb
    private Connection con;
    
    PreparedStatement ps ;
    private ResultSet rs ;
    
    @Override
    public List<Candidates> getCandidateList() {
        
        List<Candidates> cList = new ArrayList<>();
    
        try {
            ps = con.prepareStatement("select * from candidate");
            rs = ps.executeQuery();
            while (rs.next()) {                
                cList.add(new Candidates(rs.getString("CandidateHash"), rs.getString("firstName"),
                        rs.getString("lastName"), rs.getString("VoterId"), rs.getInt("ConstituencyId"),
                        rs.getInt("ElectionSymbolId")));
            }
        } catch (SQLException ex) {
            Logger.getLogger(AdminCandidateOperationsBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        return cList ;
    }

    @Override
    public void generateCandidateHash() {
        
        try {
            
            MultiChainClient mcClient = new MultiChainClient();
            
            for (Candidates candidate : getCandidateList()) {
                if(candidate.getCandidateHash() == null){
                    String hash = mcClient.getVoterAddress();
                    String sql = "UPDATE candidate SET candidateHash = '"
                            + hash + "' WHERE VoterId = '" + candidate.getVoterId() + "'";
                    ps = con.prepareStatement(sql);
                    ps.execute();
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(AdminVoterOperationsBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
 
    
    
}
