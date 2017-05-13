/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ned.bcvs.voting;

import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateful;
import javax.ejb.Stateless;
import javax.inject.Named;
import ned.bcvs.dao.Candidates;
import ned.bcvs.dao.Voters;
import ned.bcvs.voter.beans_local_interfaces.VotingBackEndLocal;

/**
 *
 * @author StackHouse
 */
@Named
@Stateful
public class VotingFrontEndBean {
    
    @EJB
    private VotingBackEndLocal backEndBean ;
    
    private String constituencyName ;
    private String voterBioMetrics ;
    private Voters voter ;
    private String transactionId ;
    
    
    public String loginVoter(){
        System.out.println("$$$$$$$$$$$$$$$$$$$$$$ voter login method " + voterBioMetrics);
        voter = backEndBean.getVoter("abc111def");
//        System.out.println("<<<<<<<<<<<<<<<<<<< voter name :" + voter.getFirstName());
        return "candidate";
    } 
    
    public List<Candidates> getCandidateList(){
//        castVote();
        System.out.println("@@@@@@@ executing method candidate list");
//        System.out.println("tXid = " + backEndBean.castVoteBackEnd("1", "1"));
        return backEndBean.getCandidateList(1);
    }
    
    public String castVote(){
        System.out.println("@@@@@@ Calling cast vote method");
        transactionId = backEndBean.castVoteBackEnd("2", "1");
        System.out.println("tXid = " + transactionId);
        return "thanks";
    }
    
    /**
     * @return the constituencyName
     */
    public String getConstituencyName() {
        setConstituencyName();
        return constituencyName;
    }

    /**
     * @param constituencyName the constituencyName to set
     */
    public void setConstituencyName() {
        System.out.println("!!!!! calling set constituency name");
        this.constituencyName = backEndBean.getConstituencyName(1);
    }

    /**
     * @return the voterBioMetrics
     */
    public String getVoterBioMetrics() {
        return voterBioMetrics;
    }

    /**
     * @param voterBioMetrics the voterBioMetrics to set
     */
    public void setVoterBioMetrics(String voterBioMetrics) {
        this.voterBioMetrics = voterBioMetrics;
    }

    /**
     * @return the transactionId
     */
    public String getTransactionId() {
        return transactionId;
    }

    /**
     * @param transactionId the transactionId to set
     */
    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }
}
