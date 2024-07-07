package com.example.demo.model.dto;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

import com.example.demo.model.entity.CateringServiceType;
import com.example.demo.model.entity.Ceremony;
import com.example.demo.model.entity.Customer;
import com.example.demo.model.entity.Dish;
import com.example.demo.model.entity.Location;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class EventCeremonyDto {

	private long id;

	private String eventCeremonyId;

	private int expectedNumberOfPeople;

	private Ceremony ceremony;

	private Customer customer;

	private List<Dish> dishesToBePrepared = new ArrayList<>();

	private Date eventDate;

	private Date dateCreated;

	private Location eventLocation;

	private int numberOfMeatPerPerson;

	private CateringServiceType serviceType;

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		EventCeremonyDto other = (EventCeremonyDto) obj;
		return id == other.id;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}
}
