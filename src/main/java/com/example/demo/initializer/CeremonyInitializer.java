package com.example.demo.initializer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import com.example.demo.model.entity.Ceremony;
import com.example.demo.repository.CeremonyRepository;
import com.example.demo.utils.PublicIdGeneratorUtils;


@Component
public class CeremonyInitializer {
	
	@Autowired
	private CeremonyRepository ceremonyRepository;
	
	@EventListener
	public void initializeCeremony(ApplicationReadyEvent event) {
		
		createCeremony("WEDDING");
		createCeremony("NAMING");
		createCeremony("HOUSE WARMING");
		createCeremony("FINAL BURIAL");
		
	}

	private void createCeremony(String ceremonyName) {
		
		Ceremony ceremony = ceremonyRepository.findByCeremonyName(ceremonyName);
		
		if (ceremony == null) {
			
			Ceremony newCeremony = new Ceremony();
			newCeremony.setCeremonyId(PublicIdGeneratorUtils.generatePublicId(30));
			newCeremony.setCeremonyName(ceremonyName);
			
			ceremonyRepository.save(newCeremony);
		}
	}
}
