/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ned.bcvs.web;

import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateful;
import javax.faces.bean.SessionScoped;
import javax.inject.Named;
import ned.bcvs.admin.beans_local_interfaces.AdminVoterOperationsLocal;
import ned.bcvs.dao.Voters;

/**
 *
 * @author StackHouse
 */
@Named
@Stateful
@SessionScoped
public class VoterBean {
    
    
    @EJB
    private AdminVoterOperationsLocal voterOps ;
    
    public List<Voters> getVoters(){
        System.out.println("%%%%%%%%%%%%%%%%%%%%%%%%%Calling getVoters() method %%%%%%%%%%%%%%%");
         return voterOps.getVotersList();
    }
    
    public void registerVoters(){
        System.out.println("%%%%%%%%%%%%%%%%%%%%%%%%%Calling registerVoters() method %%%%%%%%%%%%%%%");
        voterOps.assignVotersHash();
    }
}
