package com.example.demo.model.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
@Table(name="event_ceremonies")
public class EventCeremony {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long id;
	
	@Column(nullable=false)
	private String eventCeremonyId;
	
	private int expectedNumberOfPeople;
	
	@ManyToOne(fetch=FetchType.EAGER)
	private Ceremony ceremony;
	
	@ManyToOne(fetch=FetchType.EAGER)
	private Customer customer;
	
	@OneToMany(fetch=FetchType.EAGER)
	@JoinTable(name="event_ceremony_dishes",
			joinColumns=@JoinColumn(name="event_ceremonies_id", referencedColumnName="id"),
			inverseJoinColumns=@JoinColumn(name="dishes_id", referencedColumnName="id")
			)
	private List<Dish> dishesToBePrepared = new ArrayList<>();
	
	private Date eventDate;
	
	@CreationTimestamp
	private Date dateCreated;
	
	@OneToOne(fetch=FetchType.EAGER, cascade=CascadeType.ALL)
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
