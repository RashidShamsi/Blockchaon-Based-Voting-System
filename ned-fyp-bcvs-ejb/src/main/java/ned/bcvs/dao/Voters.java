/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ned.bcvs.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author StackHouse
 */
public class Voters {
 
    
   private String voterhash ;
    private String voterId ;
    private String firstName ;
    private String lastName ;
    private int contactNumber ;
    private int constituencyId ;
    private String bioMetrics ;

    public Voters(String voterhash, String voterId, String firstName, String lastName, int contactNumber,  int constituencyId, String bioMetrics) {
        this.voterhash = voterhash;
        this.voterId = voterId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.contactNumber = contactNumber;
        this.bioMetrics = bioMetrics;
        this.constituencyId = constituencyId;
    }

     public Voters(Connection con, String vId) {
        try {
            PreparedStatement ps = con.prepareStatement("select * from voter where voterId = " + vId);
            ResultSet rs = ps.executeQuery();
            rs.next();                
              
                       this.voterhash = rs.getString("VoterHash") ;
                       this.voterId = rs.getString("VoterId") ;
                       this.firstName = rs.getString("VoterFirstName") ;
                       this.lastName = rs.getString("VoterLastName") ;
                       this.contactNumber = rs.getInt("VoterContactNumber") ;
                       this.constituencyId = rs.getInt("ConstituencyID") ;
                       this.bioMetrics = rs.getString("VoterBiometrics") ;
                
            } catch (SQLException ex) {
            Logger.getLogger(Voters.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    
   
    
    
    /**
     * @return the voterhash
     */
    public String getVoterhash() {
        return voterhash;
    }

    /**
     * @param voterhash the voterhash to set
     */
    public void setVoterhash(String voterhash) {
        this.voterhash = voterhash;
    }

    /**
     * @return the voterId
     */
    public String getVoterId() {
        return voterId;
    }

    /**
     * @param voterId the voterId to set
     */
    public void setVoterId(String voterId) {
        this.voterId = voterId;
    }

    /**
     * @return the firstName
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * @param firstName the firstName to set
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * @return the lastName
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * @param lastName the lastName to set
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * @return the contactNumber
     */
    public int getContactNumber() {
        return contactNumber;
    }

    /**
     * @param contactNumber the contactNumber to set
     */
    public void setContactNumber(int contactNumber) {
        this.contactNumber = contactNumber;
    }

    /**
     * @return the bioMetrics
     */
    public String getBioMetrics() {
        return bioMetrics;
    }

    /**
     * @param bioMetrics the bioMetrics to set
     */
    public void setBioMetrics(String bioMetrics) {
        this.bioMetrics = bioMetrics;
    }

    /**
     * @return the constituencyId
     */
    public int getConstituencyId() {
        return constituencyId;
    }

    /**
     * @param constituencyId the constituencyId to set
     */
    public void setConstituencyId(int constituencyId) {
        this.constituencyId = constituencyId;
    }
    
}
