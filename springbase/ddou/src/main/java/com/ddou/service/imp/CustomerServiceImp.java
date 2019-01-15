package com.ddou.service.imp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ddou.mapper.CustomerMapper;
import com.ddou.pojo.Customer;
import com.ddou.service.CustomerService;

@Service
public class CustomerServiceImp implements CustomerService {
	@Autowired
	CustomerMapper dao;

	@Override
	public Customer getCustomer(int id) {
		return dao.getById(id);
	}

	@Override
	public int add(Customer customer) {
		dao.add(customer);
		return customer.getId();
	}

	@Override
	public void delete(int id) {
		dao.delete(id);

	}

	@Override
	public int update(Customer customer) {
		return dao.update(customer);

	}
}
