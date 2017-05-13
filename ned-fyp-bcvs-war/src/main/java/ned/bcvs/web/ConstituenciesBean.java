/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ned.bcvs.web;

import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import ned.bcvs.admin.beans_local_interfaces.AdminConstituenciesLocal;
import ned.bcvs.dao.Constituencies;
import ned.bcvs.dao.ConstituencyType;

/**
 *
 * @author StackHouse
 */
@Named
@RequestScoped
public class ConstituenciesBean {
    
    @EJB
    private AdminConstituenciesLocal adminConstBean ;  

    /**
     * @return the constLitst
     */
    public List<ConstituenciesModifiedDao> getConstituencies() {
        System.out.println("&&&&&&&&& executing getConstLitst() in ConstituenciesBean &&&&&&&&&&&&&&");
        List<ConstituenciesModifiedDao> constList = new ArrayList<>();
        for (Constituencies constituency : adminConstBean.constituenciesListProducer()) {
            for (ConstituencyType consType : adminConstBean.getConsType()) {
                if(constituency.getConstTypeId() == consType.getConstId()){
                    constList.add(new ConstituenciesModifiedDao(
                            constituency.getConstId(),
                            constituency.getConstName(),
                            consType.getConstName(),
                            consType.getConstId(),
                            constituency.getNoOfCandidates(),
                            constituency.getNoOfVoters()));
                }
            }
        }
        System.out.println("&&&&&&&&& ending execution of getConstLitst() in ConstituenciesBean &&&&&&&&&&&&&&");
        return constList;
    }


    /**
     * @return the constTypeList
     */
    public List<ConstituencyType> getConstituencyTypes() {
        return adminConstBean.getConsType();
    }

}
