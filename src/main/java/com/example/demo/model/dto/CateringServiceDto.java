package com.example.demo.model.dto;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

import com.example.demo.model.entity.Location;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CateringServiceDto {
	
	private long id;
	
	private String cateringServiceId;
	
	private String businessName;
	
	private String businessVision;
	
	private LocationDto location;
	
	private Date dateRegistered;
	
	private String logoURL;
	
	private List<MediaPostDto> images = new ArrayList<>();
	
	private List<MediaPostDto> videos = new ArrayList<>();
	
	private UserDto user;

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
		CateringServiceDto other = (CateringServiceDto) obj;
		return id == other.id;
	}
}
