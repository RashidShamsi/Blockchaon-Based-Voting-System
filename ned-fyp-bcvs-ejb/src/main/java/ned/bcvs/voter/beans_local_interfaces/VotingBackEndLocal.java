/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ned.bcvs.voter.beans_local_interfaces;

import java.util.List;
import javax.ejb.Local;
import ned.bcvs.dao.Candidates;
import ned.bcvs.dao.Voters;

/**
 *
 * @author StackHouse
 */
@Local
public interface VotingBackEndLocal {

    String castVoteBackEnd(String voterId, String candidateId);
    
    List<Candidates> getCandidateList(int constituencyId);
    Voters getVoter(String bioMetrics);
    String getConstituencyName(int constId);
}
