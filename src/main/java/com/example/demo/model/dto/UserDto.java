package com.example.demo.model.dto;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import com.example.demo.model.entity.Role;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UserDto {
	
	private long id;
	
	private String userId;
	
	private String fullName;

	private List<Role> roles = new ArrayList<>();

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		UserDto other = (UserDto) obj;
		return id == other.id;
	}
}
