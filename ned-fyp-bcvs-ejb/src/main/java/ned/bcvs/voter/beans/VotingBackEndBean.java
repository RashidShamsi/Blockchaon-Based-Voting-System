/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ned.bcvs.voter.beans;

import com.neemre.btcdcli4j.core.client.BtcdClient;
import com.neemre.btcdcli4j.core.client.BtcdClientImpl;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Local;
import ned.bcvs.voter.beans_local_interfaces.VotingBackEndLocal;
import javax.ejb.Stateful;
import javax.inject.Inject;
import ned.bcvs.cdi.qualifier.jdbcfypdb;
import ned.bcvs.dao.Candidates;
import ned.bcvs.dao.Voters;
import ned.bcvs.MultiChain_Client.MultiChainClient;

/**
 *
 * @author StackHouse
 */
@Stateful
@Local(VotingBackEndLocal.class)
public class VotingBackEndBean implements VotingBackEndLocal {
    
    @Inject
    @jdbcfypdb
    private Connection con ;
    
    private PreparedStatement ps ;
    private ResultSet rs ;
    private MultiChainClient client = new MultiChainClient();
    
    private String constituencyName ;
    private Voters voter ;
    
    @Override
    public String castVoteBackEnd(String voterId, String candidateId){
        
        System.out.println("@@@@@@@@@@@ entering the cast vote method in backend ");
        Voters voter = new Voters(con, voterId);
        Candidates candidate = new Candidates(con, candidateId);
        
        System.out.println("!!!!!)(@@ voter Hash : " + voter.getVoterhash());
        System.out.println("!!!!!)(@@ Candidate Hash : " + candidate.getCandidateHash());
        
        String tXid = client.sendVoteAssetToCandidate(
                voter.getVoterhash(),
                candidate.getCandidateHash(),
                voter.getFirstName() + voter.getVoterId());
        System.out.println("Transaction Id Of vote Cast : " + tXid);
        
        return tXid ;
    }

    @Override
    public List<Candidates> getCandidateList(int constituencyId) {
     
        List<Candidates> canList = new ArrayList<>();
        
        try {
            System.out.println("#$$#$#$$ enterinh the method getCandidateList in back end bean");
            String querry = "select * from candidate where ConstituencyId = " + constituencyId ;
            ps = con.prepareStatement(querry);
            rs = ps.executeQuery();
            while (rs.next()) {              
                System.out.println("!!!@@@@ candidate name : " + rs.getString("firstName") + rs.getString("lastName"));
                canList.add(new Candidates(
                        rs.getString("candidateHash"),
                        rs.getString("candidateId"),
                        rs.getString("firstName"),
                        rs.getString("lastName"),
                        rs.getString("VoterId"),
                        rs.getInt("ConstituencyId"),
                        rs.getInt("ElectionSymbolId"),
                        rs.getString("candidateType")
                ));
            }
        } catch (SQLException ex) {
            Logger.getLogger(VotingBackEndBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        return canList ;
    }

    /**
     * @return the constituencyName
     */
    @Override
    public String getConstituencyName(int constId) {
        try {
            String querry = "select * from constituency where ConstituencyId = " + constId ;
            ps = con.prepareStatement(querry);
            rs = ps.executeQuery();
            rs.next();
            setConstituencyName(rs.getString("ConstituencyName"));
        } catch (SQLException ex) {
            Logger.getLogger(VotingBackEndBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println("####### constituency name : " + constituencyName);
        return constituencyName;
    }

    /**
     * @param constituencyName the constituencyName to set
     */
    private void setConstituencyName(String constituencyName) {
        this.constituencyName = constituencyName;
    }

    /**
     * @return the voter
     */
    @Override
    public Voters getVoter(String bioMetrics) {
        try {
            System.out.println("^^^^^^^^^^^^ entering getVoter() back end method");
            String querry = "select * from voter where 	VoterBiometrics = " + bioMetrics;
            ps = con.prepareStatement(querry);
            rs = ps.executeQuery();
            rs.next();
            setVoter(new Voters(
                                   rs.getString("VoterHash"),
                                   rs.getString("VoterId"),
                                   rs.getString("VoterFirstName"), 
                                   rs.getString("VoterLastName"),
                                   rs.getInt("VoterContactNumber"), 
                                   rs.getInt("ConstituencyID"),
                                   rs.getString("VoterBiometrics")));
        } catch (SQLException ex) {
            Logger.getLogger(VotingBackEndBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        return voter;
    }

    /**
     * @param voter the voter to set
     */
    public void setVoter(Voters voter) {
        this.voter = voter;
    }
}
