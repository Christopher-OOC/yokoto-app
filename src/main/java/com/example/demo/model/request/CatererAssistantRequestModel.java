package com.example.demo.model.request;

import java.util.ArrayList;
import java.util.List;

import com.example.demo.model.entity.CaterSkill;
import com.example.demo.model.entity.EducationQualification;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CatererAssistantRequestModel {
	
	private String nickname;
	
	private EducationQualification qualification;
	
	private List<CaterSkill> caterSkill = new ArrayList<>();

}
