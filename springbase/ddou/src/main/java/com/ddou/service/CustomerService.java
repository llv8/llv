package com.ddou.service;

import com.ddou.pojo.Customer;

public interface CustomerService {
	public Customer getCustomer(int id);

	public int add(Customer customer);

	public void delete(int id);

	public int update(Customer customer);
}
