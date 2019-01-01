package com.ddou.example;

import com.ddou.mapper.CustomerMapper;
import com.ddou.pojo.Customer;
import com.ddou.util.SpringUtil;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class MybatisExample {
	public static void getCustomer() {
		CustomerMapper customerDao = SpringUtil.getCxt().getBean(CustomerMapper.class);
		Customer cust = customerDao.getById(1);

		try {
			System.out.println(new ObjectMapper().writeValueAsString(cust));
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
	}
}
