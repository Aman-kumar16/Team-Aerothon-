package com.aerothon.entity;

public class ZipDirectory {

	private String source;
	private String target;
	
	public ZipDirectory() {
		
	}
	
	public ZipDirectory(String source, String target) {
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
		return "ZipDirectory [source=" + source + ", target=" + target + "]";
	}
	
	
}
