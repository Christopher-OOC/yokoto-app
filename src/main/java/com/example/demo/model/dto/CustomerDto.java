package com.example.demo.model.dto;

import java.util.Date;
import java.util.Objects;

import com.example.demo.model.entity.Location;
import com.example.demo.model.entity.User;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CustomerDto {

	private long id;
	
	private String customerId;

	private String email;

	private String firstName;

	private String lastName;

	private String password;

	private Date dateOfBirth;

	private Location location;

	private User user;

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CustomerDto other = (CustomerDto) obj;
		return id == other.id;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}
}
