package com.example.demo.form;

import java.io.Serializable;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class AdminForm implements Serializable {
	
	@NotBlank
    private String lastName;

    @NotBlank
    private String firstName;

    @NotBlank
     @Email
    private String email;

    @NotBlank
    @Pattern(regexp="^(?=.*[A-Z])[a-zA-Z0-9_]{8,24}+$")
    private String password;
}