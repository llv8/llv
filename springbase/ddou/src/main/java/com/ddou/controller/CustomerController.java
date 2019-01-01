package com.ddou.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ddou.pojo.Customer;
import com.ddou.service.CustomerService;
import com.ddou.util.ColUtil;
import com.ddou.util.JsonUtil;

@RestController
@RequestMapping("/register")
public class CustomerController {
	Logger logger = LoggerFactory.getLogger(CustomerController.class);
	@Autowired
	CustomerService customerService;

	@RequestMapping(method = RequestMethod.POST)
	public String register(@RequestBody Customer customer) {
		int id = customerService.add(customer);
		return JsonUtil.toJson(ColUtil.createMap("id", id));
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public String get(@PathVariable String id) {
		Customer customer = customerService.getCustomer(Integer.valueOf(id));
		return JsonUtil.toJson(customer);
	}

	@RequestMapping(method = RequestMethod.DELETE)
	public String del(@RequestParam String id) {
		customerService.delete(Integer.valueOf(id));
		return JsonUtil.toJson(ColUtil.createMap("id", id));
	}

	@RequestMapping(method = RequestMethod.PUT)
	public String update(@RequestParam String id, @RequestParam String name) {
		Customer customer = new Customer();
		customer.setId(Integer.valueOf(id));
		customer.setName(name);
		int rows = customerService.update(customer);
		return JsonUtil.toJson(ColUtil.createMap("rows", rows));
	}

}
