package com.grainger.address.clients;

import java.util.Map;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.grainger.address.model.Address;

@FeignClient("validationservice")
public interface ValidationFeignClient {

	@RequestMapping(
			method= RequestMethod.POST,
			value="/validate",
			consumes="application/json")
	Map<String, Boolean> validate(@RequestBody Address address);
}
