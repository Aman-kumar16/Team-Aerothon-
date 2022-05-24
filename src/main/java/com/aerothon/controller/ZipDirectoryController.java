package com.aerothon.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.aerothon.entity.ZipDirectory;

@RestController
public class ZipDirectoryController {
	
	List<String> filesListInDir = new ArrayList<String>();
	
	
	/* 
	  to run this api go to postman and make a request on url with json data
	  http://localhost:8082/zipDirectory
	
	  in source and target give the directory name to be zipped as source. In my case it is.
	{
	    "source": "C:\\Users\\Aman Kumar\\Desktop\\Aerothon\\AerothonFolder",
	    "target": "C:\\Users\\Aman Kumar\\Desktop\\Aerothon\\AerothonFolder.zip"
	}
    
    */	
	@RequestMapping("/zipDirectory")
	private void zipDirectory(@RequestBody ZipDirectory theZipDirectory) {

		File dir = new File(theZipDirectory.getSource()); // get the path of source directory
		String zipDirName = theZipDirectory.getTarget();  // save in the destination directory

    	try {
            
        	populateFilesList(dir);
            //now zip files one by one
            //create ZipOutputStream to write to the zip file
            FileOutputStream fos = new FileOutputStream(zipDirName);
            ZipOutputStream zos = new ZipOutputStream(fos);
           
            for(String filePath : filesListInDir){
                System.out.println("Zipping "+filePath);
                //for ZipEntry we need to keep only relative file path, so we used substring on absolute path
                ZipEntry ze = new ZipEntry(filePath.substring(dir.getAbsolutePath().length()+1, filePath.length()));
                zos.putNextEntry(ze);
                //read the file and write to ZipOutputStream
                FileInputStream fis = new FileInputStream(filePath);
                byte[] buffer = new byte[1024];
                int len;
                while ((len = fis.read(buffer)) > 0) {
                    zos.write(buffer, 0, len);
                }
                zos.closeEntry();
                fis.close();
            }
            
            zos.close();
            fos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    	
    }
    
    
    private void populateFilesList(File dir) throws IOException {
        File[] files = dir.listFiles();
        for(File file : files){
            if(file.isFile()) {
            	
            	filesListInDir.add(file.getAbsolutePath());
            
            }else {
            	populateFilesList(file);
            }
        }
    }

}
