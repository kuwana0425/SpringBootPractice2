package com.example.demo.entity;

import jakarta.validation.constraints.NotEmpty;

public class AdminDto {
	
	@NotEmpty
	private String Lastname;
	
	@NotEmpty
	private String Firstname;
	
	@NotEmpty
	private static String email;
	
	@NotEmpty
	private String password;
	
	public String getLastname() {
        return Lastname;
    }

    public void setLastname(String lastName) {
        this.Lastname = lastName;
    }
    
    public String getFirstname() {
        return Firstname;
    }

    public void setFirstname(String firstName) {
        this.Firstname= firstName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public static String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
