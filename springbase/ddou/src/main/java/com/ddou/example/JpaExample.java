package com.ddou.example;

import java.util.List;

import com.ddou.entity.Customer;
import com.ddou.repository.CustomerRepository;
import com.ddou.util.SpringUtil;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JpaExample {
	public static void getCustomer() {
		CustomerRepository customerRepository = SpringUtil.getCxt().getBean(CustomerRepository.class);
		List<Customer> list = customerRepository.findAll();

		try {
			System.out.println(new ObjectMapper().writeValueAsString(list.get(0)));
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
	}
}
