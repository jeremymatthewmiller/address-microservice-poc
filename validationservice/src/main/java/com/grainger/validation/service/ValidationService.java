package com.grainger.validation.service;

import com.grainger.validation.model.Address;

public interface ValidationService {

	boolean isValid(final Address address);
}
