package com.example.demo.model.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
@Table(name="event_ceremonies")
public class EventCeremony {
	
	private long id;
	
	private String eventCeremonyId;
	
	private int expectedNumberOfPeople;
	
	private Ceremony ceremony;
	
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
		EventCeremony other = (EventCeremony) obj;
		return id == other.id;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}
}
