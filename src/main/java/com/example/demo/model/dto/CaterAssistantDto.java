package com.example.demo.model.dto;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

import com.example.demo.model.entity.CaterSkill;
import com.example.demo.model.entity.EducationQualification;
import com.example.demo.model.entity.User;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CaterAssistantDto {
	
	private long id;
	
	private String caterAssistantId;
	
	private String email;
	
	private String firstName;
	
	private String lastName;
	
	private Date dateOfBirth;
	
	private EducationQualification qualification;
	
	private User user;
	
	private List<CaterSkill> caterSkill = new ArrayList<>();

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CaterAssistantDto other = (CaterAssistantDto) obj;
		return id == other.id;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}
}
