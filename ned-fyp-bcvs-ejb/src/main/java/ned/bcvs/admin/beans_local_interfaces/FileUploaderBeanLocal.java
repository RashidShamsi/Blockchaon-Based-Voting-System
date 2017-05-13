/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ned.bcvs.admin.beans_local_interfaces;

import javax.ejb.Local;

/**
 *
 * @author StackHouse
 */
@Local
public interface FileUploaderBeanLocal {

    String fileDbUploader(String filePath, String tableName);
    
}
