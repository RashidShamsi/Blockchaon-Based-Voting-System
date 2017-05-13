/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ned.bcvs.admin.beans;

import ned.bcvs.admin.beans_local_interfaces.AdminEditActionsLocal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Local;
import javax.ejb.Stateful;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.inject.Inject;
import ned.bcvs.cdi.qualifier.jdbcfypdb;
import ned.bcvs.dao.ConstituencyType;

/**
 *
 * @author StackHouse
 */
@Stateful
@Local(AdminEditActionsLocal.class)
public class AdminEditActionsBean implements AdminEditActionsLocal {

    @Inject
    @jdbcfypdb
    Connection conn;

    //@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
    public List<ConstituencyType> getConstituencyTypes(String constituencyName) {
        final List<ConstituencyType> constituencyTypes = new ArrayList<>();
        try (PreparedStatement ps = conn.prepareStatement("Select * from ConstituencyType")) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                constituencyTypes.add(new ConstituencyType(rs.getInt("ConstituencyTypeId"), rs.getString("ConstituencyTypeName")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return constituencyTypes;
    }

}
