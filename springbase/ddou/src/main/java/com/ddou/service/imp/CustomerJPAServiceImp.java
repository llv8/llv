package com.ddou.service.imp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ddou.entity.Customer;
import com.ddou.repository.CustomerRepository;
import com.ddou.service.CustomerJPAService;

@Service
public class CustomerJPAServiceImp implements CustomerJPAService {
	@Autowired
	private CustomerRepository customerRepository;

	@Override
	public List<Customer> getCustomer() {
		return customerRepository.findAll();
	}

	@Override
	public int add(Customer customer) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void delete(int id) {
		// TODO Auto-generated method stub

	}

	@Override
	public void update(Customer customer) {
		// TODO Auto-generated method stub

	}

}
