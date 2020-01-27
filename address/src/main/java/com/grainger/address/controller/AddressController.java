package com.grainger.address.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.google.common.collect.ImmutableMap;
import com.grainger.address.model.Address;
import com.grainger.address.service.AddressService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/address")
public class AddressController {

	private static final String SUCCESS = "success";

	private final AddressService addressService;

	@Autowired
	public AddressController(AddressService addressService) {
		this.addressService = addressService;
	}

	@GetMapping("/{addressId}")
	public Address get(@PathVariable final Long addressId) {
		log.info("getAddress: addressId {}", addressId);

		Address address = this.addressService.get(addressId);

		return address;
	}

	@PostMapping(path = "/validate")//, consumes = "application/json", produces = "application/json")
	public Map<String, Boolean> isValid(@RequestBody final Address address) {
		log.info("isValid: address: {}", address);

		boolean isValid = this.addressService.isValid(address);

		Map<String, Boolean> result = ImmutableMap.of(SUCCESS, Boolean.valueOf(isValid));

		log.info("isValid: returning {}", result);
		return result;
	}

	@PostMapping("/")
	public Address create(@RequestBody final Address address) {
		log.info("create: address {}", address);

		Address newAddress = new Address();//this.addressService.create(address.getLine1(), address.getLine2(), address.getCity(), address.getRegion(), address.getPostalCode(), address.getCountryCode());

		log.info("create: returning {}", newAddress);
		return newAddress;
	}

	@PutMapping("/{addressId}")
	public long update(@PathVariable final Address address) {
		//TODO: implement this...
		return 0L;
	}

	@DeleteMapping("/{addressId}")
	public Map<String, Boolean> delete(@PathVariable final long addressId) {
		//TODO: implement this...
		return ImmutableMap.of(SUCCESS, Boolean.valueOf(true));
	}
}
