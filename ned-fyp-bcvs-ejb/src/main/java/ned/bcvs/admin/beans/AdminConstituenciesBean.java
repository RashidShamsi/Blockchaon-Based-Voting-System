/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ned.bcvs.admin.beans;

import ned.bcvs.admin.beans_local_interfaces.AdminConstituenciesLocal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.inject.Inject;
import ned.bcvs.cdi.qualifier.jdbcfypdb;
import ned.bcvs.dao.Constituencies;
import ned.bcvs.dao.ConstituencyType;

/**
 *
 * @author StackHouse
 */
@Stateless
@Local(AdminConstituenciesLocal.class)
public class AdminConstituenciesBean implements AdminConstituenciesLocal {
    
    
    @Inject
    @jdbcfypdb
    private Connection con ;
    
    private PreparedStatement ps ;
    private ResultSet rs ;
    
    
    @Override
    public List<Constituencies> constituenciesListProducer(){
        List<Constituencies> list = new ArrayList<>();
        String sql = "select * from constituency";
        try {
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {                
                list.add(new Constituencies(
                        rs.getInt("ConstituencyId"),
                        rs.getString("ConstituencyName"),
                        rs.getInt("ConstituencyTypeId"),
                        rs.getInt("NoOfCandidates"),
                        rs.getInt("NoOfVoters")));
            }
        } catch (SQLException ex) {
            Logger.getLogger(AdminConstituenciesBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list ;
    }

    @Override
    public List<ConstituencyType> getConsType(){
        List<ConstituencyType> list = new ArrayList<>();
        String sql = "select * from constituencytype";
        try {
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while(rs.next()){
                list.add(new ConstituencyType(
                        rs.getInt("ConstituencyTypeId"),
                        rs.getString("ConstituencyTypeName")));
            }
        } catch (SQLException ex) {
            Logger.getLogger(AdminConstituenciesBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list ;
    }
}
