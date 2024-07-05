package com.example.demo.model.request;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CateringServiceRequestModel {

	private String businessName;

	private String businessVision;

	private String logoURL;
	
	private LocationRequestModel location;

}
