package com.grainger.address.service;

import java.util.Map;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.grainger.address.clients.ValidationFeignClient;
import com.grainger.address.model.Address;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class AddressServiceImpl implements AddressService {

	@Autowired
	ValidationFeignClient validationFeignClient;

	@Override
	public Address get(Long addressId) {
		// TODO Auto-generated method stub
		return null;
	}

	private void randomlyRunLong(){
		Random rand = new Random();

	    int randomNum = rand.nextInt((3 - 1) + 1) + 1;

	    if (randomNum==3) sleep();
	}

	private void sleep(){
		try {
			Thread.sleep(11000);
	    } catch (InterruptedException e) {
	        log.info(e.getMessage(), e);
	    }
	}

	@Override
	@HystrixCommand(fallbackMethod = "fallbackAddressValidator",
		threadPoolKey = "validationThreadPool",
		threadPoolProperties =
			{@HystrixProperty(name = "coreSize", value = "30"),
			 @HystrixProperty(name = "maxQueueSize", value = "10")
			},
		commandProperties =
			{@HystrixProperty(name="execution.isolation.thread.timeoutInMilliseconds", value="1000")
		})
	public boolean isValid(final Address address) {
		//randomlyRunLong();

		Map<String, Boolean> result = null;
		try {
		/*Map<String, Boolean>*/ result = this.validationFeignClient.validate(address);
		//	result = this.validationRestTemplateClient.validate(address);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}

		log.info("validationFeignClient response: {}", result);
		return result.get("success");
	}

	@SuppressWarnings("unused")
	private boolean fallbackAddressValidator(final Address address) {
		log.info("******************fallbackAddressValidator******************");
		return false;
	}

	/*
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
*/

	@Override
	public Address create(Address address) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Address update(Address address) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean delete(Long addressId) {
		// TODO Auto-generated method stub
		return false;
	}

}
