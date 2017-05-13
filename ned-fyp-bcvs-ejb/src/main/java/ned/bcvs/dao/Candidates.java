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
public class Candidates {

    private String candidateHash;
    private String candidateId;
    private String firstName;
    private String lastName;
    private String voterId;
    private int constituencyId;
    private int electionSymbolId;
    private String candidateType;

    public Candidates(String hash, String fname, String lname, String id, int cid, int esid) {
        setCandidateHash(hash);
        setFirstName(fname);
        setLastName(lname);
        setVoterId(id);
        setConstituencyId(cid);
        setElectionSymbolId(esid);
    }
    
    public Candidates(String candidateHash, String candidateId, String firstName, String lastName, String voterId, int ConstituencyId, int electionSymbolId, String candidateType) {
        this.candidateHash = candidateHash;
        this.candidateId = candidateId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.voterId = voterId;
        this.constituencyId = ConstituencyId;
        this.electionSymbolId = electionSymbolId;
        this.candidateType = candidateType;
    }

    public Candidates(Connection con, String candidateId) {
        try {

            PreparedStatement ps = con.prepareStatement("select * from candidate where candidateId = " + candidateId);
            ResultSet rs = ps.executeQuery();
            rs.next();
            this.candidateHash = rs.getString("candidateHash");
            this.candidateId = rs.getString("candidateId");
            this.firstName = rs.getString("firstName");
            this.lastName = rs.getString("lastName");
            this.voterId = rs.getString("VoterId");
            this.constituencyId = rs.getInt("ConstituencyId");
            this.electionSymbolId = rs.getInt("ElectionSymbolId");
            this.candidateType = rs.getString("candidateType");

        } catch (SQLException ex) {
            Logger.getLogger(Candidates.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * @return the candidateHash
     */
    public String getCandidateHash() {
        return candidateHash;
    }

    /**
     * @param candidateHash the candidateHash to set
     */
    public void setCandidateHash(String candidateHash) {
        this.candidateHash = candidateHash;
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

    /**
     * @return the electionSymbolId
     */
    public int getElectionSymbolId() {
        return electionSymbolId;
    }

    /**
     * @param electionSymbolId the electionSymbolId to set
     */
    public void setElectionSymbolId(int electionSymbolId) {
        this.electionSymbolId = electionSymbolId;
    }

    /**
     * @return the candidateId
     */
    public String getCandidateId() {
        return candidateId;
    }

    /**
     * @param candidateId the candidateId to set
     */
    public void setCandidateId(String candidateId) {
        this.candidateId = candidateId;
    }

    /**
     * @return the candidateType
     */
    public String getCandidateType() {
        return candidateType;
    }

    /**
     * @param candidateType the candidateType to set
     */
    public void setCandidateType(String candidateType) {
        this.candidateType = candidateType;
    }
}
