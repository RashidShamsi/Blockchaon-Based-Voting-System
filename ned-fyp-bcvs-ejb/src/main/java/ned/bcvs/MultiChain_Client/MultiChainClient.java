/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ned.bcvs.MultiChain_Client;

import com.neemre.btcdcli4j.core.BitcoindException;
import com.neemre.btcdcli4j.core.CommunicationException;
import com.neemre.btcdcli4j.core.client.BtcdClient;
import com.neemre.btcdcli4j.core.client.BtcdClientImpl;
import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;

/**
 *
 * @author StackHouse
 */
public class MultiChainClient {

    InputStream inputStream = null;
    PoolingHttpClientConnectionManager connManager = new PoolingHttpClientConnectionManager();
    CloseableHttpClient httpProvider = HttpClients.custom().setConnectionManager(connManager).build();
    Properties nodeConfig = new Properties();
    BtcdClient client ;
    
    public String getVoterAddress(){
        try {
            inputStream = new BufferedInputStream(new FileInputStream(
                    "C:/Users/StackHouse/Documents/NetBeansProjects/FypRepository"
                            + "/ned-bcvs-admin-ejb/src/main/resources/node_config.properties"));
            nodeConfig.load(inputStream); 
            inputStream.close();
            client = new BtcdClientImpl(httpProvider, nodeConfig);
            return client.getNewAddress();
        } catch (IOException ex) {
            Logger.getLogger(MultiChainClient.class.getName()).log(Level.SEVERE, null, ex);
        } catch (BitcoindException ex) {
            Logger.getLogger(MultiChainClient.class.getName()).log(Level.SEVERE, null, ex);
        } catch (CommunicationException ex) {
            Logger.getLogger(MultiChainClient.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    public String issueVoteAsset(String address, String voteName){
        String assetHash = null;
        try {
            inputStream = new BufferedInputStream(new FileInputStream(
                    "C:/Users/StackHouse/Documents/NetBeansProjects/FypRepository"
                            + "/ned-bcvs-admin-ejb/src/main/resources/node_config.properties"));
            nodeConfig.load(inputStream); 
            inputStream.close();
            client = new BtcdClientImpl(httpProvider, nodeConfig);
            assetHash = client.issueAssetToAddress(address, voteName, 1, 1);
        } catch (IOException ex) {
            Logger.getLogger(MultiChainClient.class.getName()).log(Level.SEVERE, null, ex);
        } catch (BitcoindException ex) {
            Logger.getLogger(MultiChainClient.class.getName()).log(Level.SEVERE, null, ex);
        } catch (CommunicationException ex) {
            Logger.getLogger(MultiChainClient.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "Issued vote " + assetHash + " to voter address " + address;
    } 
    
    public String sendVoteAssetToCandidate(String voterHash, String candidateHash, String assetName){
        String transactionHash = null ;
        try {
            inputStream = new BufferedInputStream(new FileInputStream(
                    "C:/Users/StackHouse/Documents/NetBeansProjects/FypRepository"
                            + "/ned-bcvs-voter-ejb/src/main/resources/node_config.properties"));
            nodeConfig.load(inputStream); 
            inputStream.close();
            client = new BtcdClientImpl(httpProvider, nodeConfig);
            transactionHash = client.sendAssetFrom(voterHash, candidateHash, assetName, 1);
        } catch (IOException ex) {
            Logger.getLogger(MultiChainClient.class.getName()).log(Level.SEVERE, null, ex);
        } catch (BitcoindException ex) {
            Logger.getLogger(MultiChainClient.class.getName()).log(Level.SEVERE, null, ex);
        } catch (CommunicationException ex) {
            Logger.getLogger(MultiChainClient.class.getName()).log(Level.SEVERE, null, ex);
        }
        return transactionHash ;
    }
    
}
