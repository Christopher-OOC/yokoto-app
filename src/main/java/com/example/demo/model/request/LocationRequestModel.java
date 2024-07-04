package com.example.demo.model.request;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class LocationRequestModel {

	private String cityName;

	private String state;

	private String country;

}
