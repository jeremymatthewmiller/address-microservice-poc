package com.grainger.validation.model;

import lombok.Data;

@Data
public final class Address {

	private Long id;
	private String line1;
	private String line2;
	private String city;
	private String region;
	private String postalCode;
	private String countryCode;
}