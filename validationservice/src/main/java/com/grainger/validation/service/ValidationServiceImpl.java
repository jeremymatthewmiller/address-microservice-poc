package com.grainger.validation.service;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import com.grainger.validation.model.Address;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class ValidationServiceImpl implements ValidationService {

	@Override
	public boolean isValid(Address address) {
		
		boolean result =
				(StringUtils.isNotBlank(address.getLine1()) || StringUtils.isNotBlank(address.getLine2())) &&
				StringUtils.isNotBlank(address.getCity()) &&
				StringUtils.isNotBlank(address.getRegion()) &&
				StringUtils.isNotBlank(address.getPostalCode()) &&
				StringUtils.isNotBlank(address.getCountryCode());
		
		log.info("isValid: {} result={}", address, result);
		return result;
	}

}
