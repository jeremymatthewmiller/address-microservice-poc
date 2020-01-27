package com.grainger.address.service;

import com.grainger.address.model.Address;

public interface AddressService {

	Address get(final Long addressId);
	boolean isValid(final Address address);
	Address create(final Address address);
	Address update(final Address address);
	boolean delete(final Long addressId);
}
