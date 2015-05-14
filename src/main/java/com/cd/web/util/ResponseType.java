package com.cd.web.util;

public enum ResponseType {
	SUCCESS("success"),
	ERROR("error");
	
	private final String response;
	
	private ResponseType(final String response) {
		this.response = response;
	}
	
	@Override
	public String toString() {
		return response;
	}
}
