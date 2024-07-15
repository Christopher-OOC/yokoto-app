package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.model.entity.EventCeremony;

public interface EventCeremonyRepository extends JpaRepository<EventCeremony, Long> {
	
	EventCeremony findByEventCeremonyId(String eventCeremonyId);

}
