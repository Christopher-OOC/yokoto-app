package com.example.demo.model.request;

import java.util.Date;

import com.example.demo.model.entity.Location;
import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

@Data
@NoArgsConstructor
public class CustomerRequestModel {

	@NotBlank(message = "Email cannot be blank")
	@Email(message = "Email must be in correct format")
	private String email;

	@NotBlank(message = "First name cannot be blank")
	private String firstName;

	@NotBlank(message = "Last name cannot be blank")
	private String lastName;

	@NotBlank(message = "Password name cannot be blank")
	@Length(min = 8, max = 20, message = "Password must be between 8 to 20 characters")
	private String password;

	@JsonFormat(pattern="yyyy-MM-dd")
	private Date dateOfBirth;

	private LocationRequestModel location;

}
