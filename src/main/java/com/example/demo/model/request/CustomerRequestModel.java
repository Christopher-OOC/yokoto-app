package com.example.demo.model.request;

import java.util.Date;

import com.example.demo.model.entity.Location;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CustomerRequestModel {
	
	private String email;

	private String firstName;

	private String lastName;

	private String password;

	private Date dateOfBirth;

	private Location location;

}
