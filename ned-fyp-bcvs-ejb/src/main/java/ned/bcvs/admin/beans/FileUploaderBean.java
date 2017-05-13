/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ned.bcvs.admin.beans;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Local;
import javax.ejb.Stateful;
import javax.inject.Inject;
import ned.bcvs.admin.beans_local_interfaces.FileUploaderBeanLocal;
import ned.bcvs.cdi.qualifier.jdbcfypdb;

/**
 *
 * @author StackHouse
 */
@Stateful
@Local(FileUploaderBeanLocal.class)
public class FileUploaderBean implements FileUploaderBeanLocal {
    
        @Inject
        @jdbcfypdb
        private Connection conn ;
        private PreparedStatement ps;

    @Override
        public String fileDbUploader(String filePath, String tableName){

            try {
                
                switch(tableName){
                    case("voter"): 
                            ps = conn.prepareStatement("LOAD DATA LOCAL INFILE ? INTO TABLE voter FIELDS TERMINATED BY ','"
                        + " LINES TERMINATED BY '\n' (VoterId, VoterFirstName, VoterLastName, VoterContactNumber,"
                        + " ConstituencyID, VoterBiometrics) ");
                        break;    
                    case("candidate"): 
                            ps = conn.prepareStatement("LOAD DATA LOCAL INFILE ? INTO TABLE candidate FIELDS TERMINATED BY ','"
                        + " LINES TERMINATED BY '\n' (candidateId ,firstName, lastName, VoterId, ConstituencyId, ElectionSymbolId, candidateType) ");
                        break;    
                    case("constituency"): 
                            ps = conn.prepareStatement("LOAD DATA LOCAL INFILE ? INTO TABLE constituency FIELDS TERMINATED BY ','"
                        + " LINES TERMINATED BY '\n' (ConstituencyId, ConstituencyTypeId, ConstituencyName, NoOfCandidates, NoOfVoters) ");
                        break;
                    case("constituencytype"): 
                            ps = conn.prepareStatement("LOAD DATA LOCAL INFILE ? INTO TABLE constituencytype FIELDS TERMINATED BY ','"
                        + " LINES TERMINATED BY '\n' (ConstituencyTypeId, ConstituencyTypeName) ");
                        break;
                    case("electionparty"): 
                            ps = conn.prepareStatement("LOAD DATA LOCAL INFILE ? INTO TABLE electionparty FIELDS TERMINATED BY ','"
                        + " LINES TERMINATED BY '\n' (ElectionPartyId, ElectionPartyName, ElectionSymbolId) ");
                        break;
                }
                
                ps.setString(1, filePath);
                ps.executeQuery();
                System.out.println("****************Dayar e Ishq me*******************"); 
                System.out.println("****************apna maqam paida kr**********************"); 
                System.out.println("****************naya zamana, nai subh o shaam*****************"); 
                System.out.println("****************paida kar*****************"); 
            } catch (SQLException ex) {
                Logger.getLogger(FileUploaderBean.class.getName()).log(Level.SEVERE, null, ex);
            }

            return "saved " + tableName + ".csv in Data base";
        }
    

    
}
