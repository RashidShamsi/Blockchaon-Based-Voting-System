/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ned.bcvs.admin.beans_local_interfaces;

import java.util.List;
import javax.ejb.Local;
import ned.bcvs.dao.Voters;

/**
 *
 * @author StackHouse
 */
@Local
public interface AdminVoterOperationsLocal {

    List<Voters> getVotersList();
    void assignVotersHash();
}
