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
public class ConstituencyType {
    
    private int constId;
    
    private String constName;

    public ConstituencyType(int constId, String constName) {
        this.constId = constId;
        this.constName = constName;
    }

    public int getConstId() {
        return constId;
    }

    public String getConstName() {
        return constName;
    }

    public void setConstId(int constId) {
        this.constId = constId;
    }

    public void setConstName(String constName) {
        this.constName = constName;
    }
    
}