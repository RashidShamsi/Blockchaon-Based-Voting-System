/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ned.bcvs.admin.beans;

import ned.bcvs.admin.beans_local_interfaces.AdminLoginValidatorLocal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.ejb.Local;
import javax.ejb.Stateful;
import javax.inject.Inject;
import ned.bcvs.cdi.qualifier.jdbcfypdb;
import ned.bcvs.dao.ConstituencyType;

/**
 *
 * @author StackHouse
 */
@Stateful
@Local(AdminLoginValidatorLocal.class)
public class AdminLoginValidatorBean implements AdminLoginValidatorLocal {
    
    @Inject
    @jdbcfypdb
    Connection con ;
    
    @Override
    public boolean validateCredentials(String un, String pwd){
//        PreparedStatement ps = null;

		try(PreparedStatement ps = con.prepareStatement("Select username, password from user"
                        + " where username = ? and password = ?")) {
                    ps.setString(1, "admin");
                    ps.setString(2, "admin");
                    ResultSet rs = ps.executeQuery();     
                    while (rs.next()) {                        
                        System.out.println("=======================================================================");
                        System.out.println("Executing validateCredentials() method printing retrived parameters");
                        System.out.println("user name =" + rs.getString("username") + " password = " + rs.getString("password"));
                        System.out.println("=======================================================================");
                        return true;
                        }     
		} catch (SQLException ex) {
			System.out.println("Login error -->" + ex.getMessage());
			return false;
		} 
		return false;
	} 
    }

//                        ps = con.prepareStatement("Select username, password from user where username = 'admin' and password = 'admin'");
//			ps.setString(1, un);
//			ps.setString(2, pwd);

//			ResultSet rs = ps.executeQuery();
//                        System.out.println("=======================================================================");
//                        System.out.println("user name =" + rs.getString("username") + "password = " + rs.getString("password"));
//			System.out.println("=======================================================================");