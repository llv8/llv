package com.ddou.service.imp;

import org.springframework.stereotype.Service;

import com.ddou.mapper.CustomerMapper;
import com.ddou.pojo.Customer;
import com.ddou.service.CustomerService;
import com.ddou.util.SpringUtil;

@Service
public class CustomerServiceImp implements CustomerService {

	@Override
	public Customer getCustomer(int id) {
		return SpringUtil.getDao(CustomerMapper.class).getById(id);
	}

	@Override
	public int add(Customer customer) {
		SpringUtil.getDao(CustomerMapper.class).add(customer);
		return customer.getId();
	}

	@Override
	public void delete(int id) {
		SpringUtil.getDao(CustomerMapper.class).delete(id);

	}

	@Override
	public int update(Customer customer) {
		return SpringUtil.getDao(CustomerMapper.class).update(customer);

	}
}
