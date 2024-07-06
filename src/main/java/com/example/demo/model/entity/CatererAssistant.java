package com.example.demo.model.entity;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinTable;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
@Table(name="cater_assistants")
public class CatererAssistant {
	
	// caterAssistantId will be the same as customerId 
	@Id
	@Column(name="catering_assistant_id")
	private String caterAssistantId;
		
	@Column(nullable=false)
	private String nickname;
	
	@Enumerated(EnumType.STRING)
	private EducationQualification qualification;
	
	@OneToOne(mappedBy="catererAssistant", fetch=FetchType.EAGER, cascade=CascadeType.PERSIST)
	private Customer customer;
	
	@OneToMany(fetch=FetchType.EAGER, cascade=CascadeType.PERSIST)
	@JoinTable(name="cater_assistant_skills")
	private List<CaterSkill> caterSkills = new ArrayList<>();

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CatererAssistant other = (CatererAssistant) obj;
		return Objects.equals(caterAssistantId, other.caterAssistantId);
	}

	@Override
	public int hashCode() {
		return Objects.hash(caterAssistantId);
	}
}
