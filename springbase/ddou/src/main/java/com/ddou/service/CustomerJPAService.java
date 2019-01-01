package com.ddou.service;

import java.util.List;

import com.ddou.entity.Customer;

public interface CustomerJPAService {
	public List<Customer> getCustomer();

	public int add(Customer customer);

	public void delete(int id);

	public void update(Customer customer);
}
