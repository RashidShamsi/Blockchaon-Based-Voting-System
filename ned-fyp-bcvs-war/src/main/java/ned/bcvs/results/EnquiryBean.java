/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ned.bcvs.results;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.inject.Named;
import ned.bcvs.results.beans_local_interfaces.VoterEnquiryLocal;
import ned.bcvs.dao.Voters;

/**
 *
 * @author StackHouse
 */
@Named
@Stateless
public class EnquiryBean {
    
    @EJB
    private VoterEnquiryLocal vEnq ;
    private String voterId ;
    private Voters voter ;
    
    public void voterDetails(){
        setVoter(vEnq.voterEnquiry(voterId));
    }

    
    
    /**
     * @return the voterId
     */
    public String getVoterId() {
        return voterId;
    }

    /**
     * @param voterId the voterId to set
     */
    public void setVoterId(String voterId) {
        this.voterId = voterId;
    }

    /**
     * @return the vDAo
     */
    public Voters getVoter() {
        return voter;
    }

    /**
     * @param vDAo the vDAo to set
     */
    public void setVoter(Voters vDAo) {
        this.voter = vDAo;
    }
}
