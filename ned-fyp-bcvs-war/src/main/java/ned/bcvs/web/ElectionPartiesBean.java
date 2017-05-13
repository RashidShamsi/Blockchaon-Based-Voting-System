/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ned.bcvs.web;

import java.util.List;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import ned.bcvs.admin.beans_local_interfaces.AdminElectionPartiesLocal;
import ned.bcvs.dao.ElectionParty;

/**
 *
 * @author StackHouse
 */
@Named
@RequestScoped
public class ElectionPartiesBean {
    
    @EJB
    private AdminElectionPartiesLocal adminPartyBean ;
    
    public List<ElectionParty> getElectionPartiesList(){
        System.out.println("&&&&&&&& executing getElectionPartiesList() in ElectionPartiesBean");
        return adminPartyBean.electionPartiesListGenerator();
    }
}
