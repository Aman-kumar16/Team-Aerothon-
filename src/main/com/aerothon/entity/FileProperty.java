package com.aerothon.entity;

public class FileProperty {

	private String message ="";
	private String fileName="";
	private String extension="";
	
	
	public FileProperty() {
		
	}
	
	public FileProperty(String message, String fileName, String fileExtension) {
		super();
		this.message = message;
		this.fileName = fileName;
		this.extension = fileExtension;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getExtension() {
		return extension;
	}

	public void setExtension(String extension) {
		this.extension = extension;
	}

	@Override
	public String toString() {
		return "FileProperty [message=" + message + ", fileName=" + fileName + ", fileExtension=" + extension + "]";
	}
	
}
