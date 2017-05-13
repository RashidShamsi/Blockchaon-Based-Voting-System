/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ned.bcvs.admin.beans;

import ned.bcvs.admin.beans_local_interfaces.AdminVoterOperationsLocal;
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
import ned.bcvs.cdi.qualifier.jdbcfypdb;
import ned.bcvs.dao.Voters;

/**
 *
 * @author StackHouse
 */
@Stateful
@Local(AdminVoterOperationsLocal.class)
public class AdminVoterOperationsBean implements AdminVoterOperationsLocal {
    
    @Inject
    @jdbcfypdb
    private Connection con;
    
    private ResultSet rs ;
    private PreparedStatement ps ;
    
    
    @Override
    public List<Voters> getVotersList(){
        List<Voters> vList = new ArrayList<>();
        try {
            ps = con.prepareStatement("select * from voter");
            rs = ps.executeQuery();
            while (rs.next()) {                
               
                vList.add(new Voters(
                       rs.getString("VoterHash"),
                       rs.getString("VoterId"),
                       rs.getString("VoterFirstName"),
                       rs.getString("VoterLastName"),
                       rs.getInt("VoterContactNumber"),
                       rs.getInt("ConstituencyID"),
                       rs.getString("VoterBiometrics")));
                
            }
        } catch (SQLException ex) {
            Logger.getLogger(AdminVoterOperationsBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        return vList;
    }

    @Override
    public void assignVotersHash() {
        
        try {
            
            MultiChainClient mcClient = new MultiChainClient();
            
            for (Voters voter : getVotersList()) {
                if(voter.getVoterhash() == null){
                    String hash = mcClient.getVoterAddress();
                    mcClient.issueVoteAsset(hash, voter.getFirstName() + voter.getVoterId());
                    String sql = "UPDATE voter SET VoterHash = '"
                            + hash + "' WHERE VoterId = '" + voter.getVoterId() + "'";
                    ps = con.prepareStatement(sql);
                    ps.execute();
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(AdminVoterOperationsBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
}
