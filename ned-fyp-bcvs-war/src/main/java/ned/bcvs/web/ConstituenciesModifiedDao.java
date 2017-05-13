/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ned.bcvs.web;

/**
 *
 * @author StackHouse
 */
public class ConstituenciesModifiedDao {
 
    
    private int constId ;
    private String constName ;
    private String constTypeName ;
    private int constTypeId ;
    private int noOfCandidates ;
    private int noOfVoters ;

    public ConstituenciesModifiedDao(int constId, String constName,
            String constTypeName, int constTypeId, int noOfCandidates, int noOfVoters) {
        this.constId = constId;
        this.constName = constName;
        this.constTypeName = constTypeName;
        this.constTypeId = constTypeId;
        this.noOfCandidates = noOfCandidates;
        this.noOfVoters = noOfVoters;
    }

    
    /**
     * @return the constId
     */
    public int getConstId() {
        return constId;
    }

    /**
     * @param constId the constId to set
     */
    public void setConstId(int constId) {
        this.constId = constId;
    }

    /**
     * @return the constName
     */
    public String getConstName() {
        return constName;
    }

    /**
     * @param constName the constName to set
     */
    public void setConstName(String constName) {
        this.constName = constName;
    }

    /**
     * @return the constTypeName
     */
    public String getConstTypeName() {
        return constTypeName;
    }

    /**
     * @param constTypeName the constTypeName to set
     */
    public void setConstTypeName(String constTypeName) {
        this.constTypeName = constTypeName;
    }

    /**
     * @return the constTypeId
     */
    public int getConstTypeId() {
        return constTypeId;
    }

    /**
     * @param constTypeId the constTypeId to set
     */
    public void setConstTypeId(int constTypeId) {
        this.constTypeId = constTypeId;
    }

    /**
     * @return the noOfCandidates
     */
    public int getNoOfCandidates() {
        return noOfCandidates;
    }

    /**
     * @param noOfCandidates the noOfCandidates to set
     */
    public void setNoOfCandidates(int noOfCandidates) {
        this.noOfCandidates = noOfCandidates;
    }

    /**
     * @return the noOfVoters
     */
    public int getNoOfVoters() {
        return noOfVoters;
    }

    /**
     * @param noOfVoters the noOfVoters to set
     */
    public void setNoOfVoters(int noOfVoters) {
        this.noOfVoters = noOfVoters;
    }

}
