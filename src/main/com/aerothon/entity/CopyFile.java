package com.aerothon.entity;

public class CopyFile {

	private String source="";
	private String target="";
	
	
	public CopyFile() {
		
	}
	
	public CopyFile(String source, String target) {
		super();
		this.source = source;
		this.target = target;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public String getTarget() {
		return target;
	}

	public void setTarget(String target) {
		this.target = target;
	}

	@Override
	public String toString() {
		return "CopyFile [source=" + source + ", target=" + target + "]";
	}
	
	
	
}
