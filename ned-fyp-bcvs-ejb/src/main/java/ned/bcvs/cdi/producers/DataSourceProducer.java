/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ned.bcvs.cdi.producers;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Disposes;
import javax.enterprise.inject.Produces;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import ned.bcvs.cdi.qualifier.jdbcfypdb;

/**
 *
 * @author StackHouse
 */
public class DataSourceProducer {

    @Produces
    @jdbcfypdb
    @RequestScoped
    private Connection m1() {
        Connection conn = null;
        try {
            DataSource ds = (DataSource) new InitialContext().lookup("jdbc/fypdb");
            conn = ds.getConnection();
            System.out.println("--- --- ---jdbc/fypdb connection created");
        } catch (NamingException ex) {
            Logger.getLogger(DataSourceProducer.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(DataSourceProducer.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            return conn;
        }
    }
    
    private void m2(@Disposes Connection conn){
        try {
            conn.close();
            System.out.println("--- --- ---jdbc/fypdb connection closed");
        } catch (SQLException ex) {
            Logger.getLogger(DataSourceProducer.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
