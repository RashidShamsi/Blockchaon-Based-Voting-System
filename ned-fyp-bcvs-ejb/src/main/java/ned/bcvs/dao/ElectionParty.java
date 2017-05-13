/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ned.bcvs.dao;

/**
 *
 * @author StackHouse
 */
public class ElectionParty {
    
    private int electionPartyId ;
    private String partyName ;
    private int symbolId ;

    public ElectionParty(int electionPartyId, String partyName, int symbolId) {
        this.electionPartyId = electionPartyId;
        this.partyName = partyName;
        this.symbolId = symbolId;
    }

    
    
    /**
     * @return the electionPartyId
     */
    public int getElectionPartyId() {
        return electionPartyId;
    }

    /**
     * @param electionPartyId the electionPartyId to set
     */
    public void setElectionPartyId(int electionPartyId) {
        this.electionPartyId = electionPartyId;
    }

    /**
     * @return the partyName
     */
    public String getPartyName() {
        return partyName;
    }

    /**
     * @param partyName the partyName to set
     */
    public void setPartyName(String partyName) {
        this.partyName = partyName;
    }

    /**
     * @return the symbolId
     */
    public int getSymbolId() {
        return symbolId;
    }

    /**
     * @param symbolId the symbolId to set
     */
    public void setSymbolId(int symbolId) {
        this.symbolId = symbolId;
    }
    
}
