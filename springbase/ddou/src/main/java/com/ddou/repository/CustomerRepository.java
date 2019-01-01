
package com.ddou.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.Repository;

import com.ddou.entity.Customer;

public interface CustomerRepository extends Repository<Customer, Long> {

	List<Customer> findAll();

	Page<Customer> findByNameContainingAndPasswordContainingAllIgnoringCase(String name, String password,
			Pageable pageable);

	Customer findByNameAndPasswordAllIgnoringCase(String name, String password);

}
