package com.ddou.example;

import org.springframework.jdbc.core.JdbcTemplate;

import com.ddou.entity.Customer;
import com.ddou.util.SpringUtil;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JdbcTemplateExample {
	public static void getCustomer() {
		JdbcTemplate jdbcTemplate = SpringUtil.getCxt().getBean(JdbcTemplate.class);
		Customer customer = jdbcTemplate.queryForObject("select * from customer where id=1", (rs, rowNum) -> {
			Customer cust = new Customer();
			cust.setId(rs.getInt("id"));
			cust.setName(rs.getString("name"));
			cust.setPhone(rs.getString("phone"));
			cust.setPassword(rs.getString("password"));
			return cust;
		});

		try {
			System.out.println(new ObjectMapper().writeValueAsString(customer));
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
	}
}
