package com.aerothon.controller;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.aerothon.entity.FileProperty;

@RestController
public class FilesController {

	//for creating folder of given name
	@RequestMapping("/createFolder/{folderName}")
	public static void createFolder(@PathVariable String folderName) throws Exception {
			
		File newFolder = new File("C:\\Users\\Aman Kumar\\Desktop\\Aerothon\\" + folderName);
		
		boolean folderCreated = newFolder.mkdir();
		
		if(folderCreated) {
			System.out.println("Folder Created");
		}else {
			System.out.println("Folder Not Created");
			throw new Exception("Folder Not Created");
		}	
	}
	
	
	
	// for creating file with given  message, filename and extension	
	@RequestMapping("/createFile")
	public static void createFile(@RequestBody FileProperty fileProperty) {
		
		String savePath = "C:\\Users\\Aman Kumar\\Desktop\\Aerothon\\AerothonFolder\\";
		String message=fileProperty.getMessage();
		
		if(fileProperty.getExtension().equals("html")) {
			message = "<div> <h1>" + fileProperty.getMessage() + "</h1> </div>";
		}
		
				
		File newFile = new File(savePath + fileProperty.getFileName() + "." + fileProperty.getExtension() );
		
		System.out.println(fileProperty.getMessage() + "   " + fileProperty.getFileName() + "." + fileProperty.getExtension());
		
		try {
			//creating and writing the content into the file
			BufferedWriter writer = new BufferedWriter(new FileWriter(newFile));
			writer.write(message);
			
			writer.close();
			
		}catch(IOException e) {
			System.out.println("Exception occured while creating file");
			e.printStackTrace();
		}
	
	}
	
}
