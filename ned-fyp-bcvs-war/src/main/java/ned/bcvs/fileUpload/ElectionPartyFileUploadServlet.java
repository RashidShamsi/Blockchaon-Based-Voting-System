/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ned.bcvs.fileupload;

import java.io.File;
import java.util.Iterator;
import java.util.List;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import ned.bcvs.admin.beans_local_interfaces.FileUploaderBeanLocal;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

/**
 *
 * @author StackHouse
 */
@WebServlet(urlPatterns = "/adminn/electionpartyfileupload")
public class ElectionPartyFileUploadServlet extends HttpServlet{
    
     private boolean isMultipart;
    private String filePath;
    private int maxFileSize = 5000 * 1024;
    private int maxMemSize = 4 * 1024;
    private File file ;
    private String fileName ;
    
    @EJB
    private FileUploaderBeanLocal upbean;
    
    
   public void init( ){
      // Get the file location where it would be stored.
      filePath = 
             getServletContext().getInitParameter("file-upload"); 
   }
   public void doPost(HttpServletRequest request, 
               HttpServletResponse response)
              throws ServletException, java.io.IOException {
            // Check that we have a file upload request
            isMultipart = ServletFileUpload.isMultipartContent(request);
            response.setContentType("text/html");
            java.io.PrintWriter out = response.getWriter( );
            if( !isMultipart ){
               out.println("<html>");
               out.println("<head>");
               out.println("<title>Servlet upload</title>");  
               out.println("</head>");
               out.println("<body>");
               out.println("<p>No file uploaded</p>"); 
               out.println("</body>");
               out.println("</html>");
               return;
            }
            DiskFileItemFactory factory = new DiskFileItemFactory();
            // maximum size that will be stored in memory
            factory.setSizeThreshold(maxMemSize);
            // Location to save data that is larger than maxMemSize.
            factory.setRepository(new File("D:/glassfish12October/glassfish-4.0/glassfish4/"
                    + "glassfish/domains/domain1/applications/temp"));

            // Create a new file upload handler
            ServletFileUpload upload = new ServletFileUpload(factory);
            // maximum file size to be uploaded.
            upload.setSizeMax( maxFileSize );

            try{ 
            // Parse the request to get file items.
            List fileItems = upload.parseRequest(request);

            // Process the uploaded file items
            Iterator i = fileItems.iterator();

            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet upload</title>");  
            out.println("</head>");
            out.println("<body>");
            while ( i.hasNext () ) 
            {
               FileItem fi = (FileItem)i.next();
               if ( !fi.isFormField () )	
               {
                  // Get the uploaded file parameters
                  String fieldName = fi.getFieldName();
                  fileName = fi.getName();
                  String contentType = fi.getContentType();
                  boolean isInMemory = fi.isInMemory();
                  long sizeInBytes = fi.getSize();
                  // Write the file
                  if( fileName.lastIndexOf("\\") >= 0 ){
                     file = new File( filePath + 
                     fileName.substring( fileName.lastIndexOf("\\"))) ;
                  }else{
                     file = new File( filePath + 
                     fileName.substring(fileName.lastIndexOf("\\")+1)) ;
                  }
                  fi.write( file ) ;
                  out.println("Uploaded Filename: " + fileName + "<br>");
               }
            }
            
            //calling the ejb method to save voter.csv file to data base
            out.println(upbean.fileDbUploader(filePath + fileName, "electionparty"));
            out.println("</body>");
            out.println("</html>");
         }catch(Exception ex) {
             System.out.println(ex);
         }
   }
   
   
   public void doGet(HttpServletRequest request, 
                       HttpServletResponse response)
        throws ServletException, java.io.IOException {
        
        throw new ServletException("GET method used with " +
                getClass( ).getName( )+": POST method required.");
   }
}
