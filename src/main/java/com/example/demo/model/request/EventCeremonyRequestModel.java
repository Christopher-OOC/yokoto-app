package com.example.demo.model.request;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.example.demo.model.entity.CateringServiceType;
import com.example.demo.model.entity.Location;
import com.fasterxml.jackson.annotation.JsonAlias;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class EventCeremonyRequestModel {
	
	private int expectedNumberOfPeople;

	private String ceremonyName;

	@JsonAlias({"dishedTobePrepared", "dishes"})
	private List<String> nameOfdishesToBePrepared = new ArrayList<>();

	private Date eventDate;

	private Location eventLocation;

	private int numberOfMeatPerPerson;

	private CateringServiceType serviceType;
}
