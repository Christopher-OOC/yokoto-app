package com.example.demo.utils;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.model.entity.Ceremony;

public interface CeremonyRepository extends JpaRepository<Ceremony, Long> {

	Ceremony findByCeremonyName(String ceremonyName);
}
