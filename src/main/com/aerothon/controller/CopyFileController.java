package com.aerothon.controller;

import static java.nio.file.FileVisitResult.CONTINUE;
import static java.nio.file.FileVisitResult.SKIP_SUBTREE;

import java.io.IOException;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.FileSystemLoopException;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.aerothon.entity.CopyFile;


@RestController
public class CopyFileController {
	
	/*
	 * to run this api hit following url
	 * http://localhost:8082/copyFile
	 * 
	 * and pass the json format source and destination uri in the following format e.g.
	 * {
		    "source": "C:\\Users\\Aman Kumar\\Desktop\\Aerothon",
		    "target": "C:\\Users\\Aman Kumar\\Desktop\\Aerothon\\AerothonCopiedFolder"
	   }
	 * 
	 */
	
	
	@RequestMapping("/copyFile")
	public void copyFile( @RequestBody CopyFile  theCopyFile) {
	
		System.out.println("Copying file");
	
		Path sourceLocation = Paths.get(theCopyFile.getSource());
		Path targetLocation = Paths.get(theCopyFile.getTarget());
		
	    //set source and target location
		CustomFileVisitor fileVisitor = new CustomFileVisitor(sourceLocation, targetLocation);
	
	    try {
			Files.walkFileTree(sourceLocation, fileVisitor);
		} catch (IOException e) {
			System.out.println("Exception occured while copying file or folder");
			e.printStackTrace();
		}    
	}
	
}
	    
//    Path sourceLocation= Paths.get("C:\\Users\\Aman Kumar\\Desktop\\Aerothon");
//    Path targetLocation =Paths.get("C:\\Users\\Aman Kumar\\Desktop\\Aerothon\\AerothonCopiedFolder");
  

class CustomFileVisitor extends SimpleFileVisitor<Path> {

    final Path source;
    final Path target;

    public CustomFileVisitor(Path source, Path target) {
        this.source = source;
        this.target = target;
    }

    @Override
    public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs)
            throws IOException
    {      
        Path newDirectory= target.resolve(source.relativize(dir));
        try{
            Files.copy(dir,newDirectory);
        }
        catch (FileAlreadyExistsException ioException){
            //log it and move
            return SKIP_SUBTREE; // skip processing
        }
        return FileVisitResult.CONTINUE;
    }

    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {

       Path newFile = target.resolve(source.relativize(file));

        try{
            Files.copy(file,newFile);
        }
        catch (IOException ioException){
        	System.out.println("Exception occured while copyFile in visitFileMethod");
           ioException.printStackTrace();
        }

        return FileVisitResult.CONTINUE;

    }

    @Override
    public FileVisitResult postVisitDirectory(Path dir, IOException exc) throws IOException {      
        return FileVisitResult.CONTINUE;
    }

    @Override
    public FileVisitResult visitFileFailed(Path file, IOException exc) {
        if (exc instanceof FileSystemLoopException) {
            //log error
        	System.out.println("FileSystemLoopException");
        } else {
            //log error
        	System.out.println("VisitFileFailed");
        }
        return CONTINUE;
    }
}