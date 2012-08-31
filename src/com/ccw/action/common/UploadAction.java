package com.ccw.action.common;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Date;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.ServletActionContext;

import com.ccw.common.Params;
import com.opensymphony.xwork2.ActionSupport;

public class UploadAction extends ActionSupport {
    private static final long serialVersionUID = 2854105381965508552L; 
    private Log log = LogFactory.getLog(UploadAction.class);
    private static final int BUFFER_SIZE = 20 * 1024;
    private File myFile;
    private String fileName;
    private String contentType;
    private String folderName;

	public File getMyFile() {
		return myFile;
	}

	public void setMyFile(File myFile) {
		this.myFile = myFile;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getContentType() {
		return contentType;
	}

	public void setContentType(String contentType) {
		this.contentType = contentType;
	}

	public String getFolderName() {
		return folderName;
	}

	public void setFolderName(String folderName) {
		this.folderName = folderName;
	}

	public String execute()  
    {
    	try {
    		log.debug("UploadAction.excute()");
	        String newFilePath = ServletActionContext.getServletContext().getRealPath(Params.PICTURECONTEXT + "/" + folderName);
	        File path = new File(newFilePath);
			if(!path.exists())
				path.mkdirs();
			
	        String newFileName = newFilePath + File.separator + String.valueOf(new Date().getTime());
	        
	        File imageFile = new File(newFileName + ".jpg");
	        upload(myFile, imageFile);  
	  
	        return SUCCESS;
    	}catch(Exception e) {
			log.error(e);
			log.error(e.getMessage());
			return ERROR;
		}
    }
    
    private static void upload(File src, File dst) throws Exception {
        InputStream in = null;  
        OutputStream out = null;  
        try {  
            in = new BufferedInputStream(new FileInputStream(src),  
                    BUFFER_SIZE);  
            out = new BufferedOutputStream(new FileOutputStream(dst),  
                    BUFFER_SIZE);  
            byte[] buffer = new byte[BUFFER_SIZE];  
            while (in.read(buffer) > 0)  
            {  
                out.write(buffer);  
            }  
        }   
        finally  
        {  
            if (null != in)  
            {  
                in.close();  
            }  
            if (null != out)  
            {  
                out.close();  
            }  
        }
    }
}