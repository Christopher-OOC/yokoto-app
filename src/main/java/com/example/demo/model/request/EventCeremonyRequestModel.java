package com.example.demo.model.request;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.example.demo.model.entity.CateringServiceType;
import com.example.demo.model.entity.Location;
import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class EventCeremonyRequestModel {
	
	private int expectedNumberOfPeople;

	private String ceremonyName;

	@JsonAlias({"dishes_to_be_prepared", "dishes"})
	private List<String> idOfDishesToBePrepared = new ArrayList<>();

	@JsonFormat(pattern="dd-MM-yyyy")
	private Date eventDate;

	private Location eventLocation;

	private int numberOfMeatPerPerson;

	private CateringServiceType serviceType;
}
