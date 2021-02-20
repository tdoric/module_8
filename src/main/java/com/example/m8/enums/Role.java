package com.example.m8.enums;

public enum Role {
	
	WRITER("Writer"),
	READER("Reader");
	
	public final String value;

    private Role(String role) {
        this.value = role;
    }
    
    public static Role valueOfRole(String role) {
        for (Role e : values()) {
            if (e.value.equals(role)) {
                return e;
            }
        }
        return null;
    }

}
