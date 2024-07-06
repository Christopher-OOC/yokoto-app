package com.example.demo.model.dto;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import com.example.demo.model.entity.EducationQualification;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CatererAssistantDto {
	
	private String caterAssistantId;
	
	private String nickname;
	
	private CustomerDto customer;
	
	private EducationQualification qualification;
	
	private List<CaterSkillDto> caterSkills = new ArrayList<>();

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CatererAssistantDto other = (CatererAssistantDto) obj;
		return Objects.equals(caterAssistantId, other.caterAssistantId);
	}

	@Override
	public int hashCode() {
		return Objects.hash(caterAssistantId);
	}
}
