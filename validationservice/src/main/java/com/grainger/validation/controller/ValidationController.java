package com.grainger.validation.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.google.common.collect.ImmutableMap;
import com.grainger.validation.model.Address;
import com.grainger.validation.service.ValidationService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/")
public class ValidationController {

	private static final String SUCCESS = "success";

	private final ValidationService validationService;

	@Autowired
	public ValidationController(ValidationService validationService) {
		this.validationService = validationService;
	}

	@PostMapping(path = "/validate")//, consumes = "application/json", produces = "application/json")
	public Map<String, Boolean> isValid(@RequestBody final Address address) {
		log.info("isValid: address: {}", address);

		boolean isValid = this.validationService.isValid(address);

		Map<String, Boolean> result = ImmutableMap.of(SUCCESS, Boolean.valueOf(isValid));

		log.info("isValid: returning {}", result);
		return result;
	}
}
