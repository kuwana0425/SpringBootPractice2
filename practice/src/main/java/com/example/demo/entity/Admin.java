package com.example.demo.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "admins")
public class Admin {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name = "id")
	private Long id;
	
	@Column(name ="last_name",nullable = false)
	private String lastName;
	
	@Column(name ="first_name",nullable = false)
	private String firstName;
	
	@Column(name ="email",nullable = true)
	private String email;
	
	@Column(name ="password",nullable = false)
	private String password;

	public Long getId() {
        return this.id;
    }
    public String getLastName() {
        return this.lastName;
    }
    public String getFirstName() {
        return firstName;
    }
    public String getEmail() {
        return this.email;
    }
    public String getPassword() {
        return this.password;
    }
    // 以下セッター
    public void setId(Long id) {
        this.id = id;
    }
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public void setPassword(String password) {
        this.password = password;
	}
}