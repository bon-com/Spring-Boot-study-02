package com.example.demo03.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo03.domain.Customer;
import com.example.demo03.repository.CustomerRepository;

@Service
public class CustomerService {
	@Autowired
	private CustomerRepository customerRepository;
	
	/**
	 * 利用者リスト取得
	 * 
	 * @return 利用者リスト
	 */
	public List<Customer> findAll() {
		return customerRepository.findAll();
	}
	
	/**
	 * 利用者取得
	 * 
	 * @param id
	 * @return 利用者
	 */
	public Customer findOneById(int id) {
		return customerRepository.findOneById(id);
	}

	/**
	 * 利用者登録
	 * 
	 * @param lastName
	 * @param firstName
	 */
	public void create(String lastName, String firstName) {
		//  データ作成
		var customer = new Customer();
		customer.setLastName(lastName);
		customer.setFirstName(firstName);
		// 登録
		customerRepository.save(customer);
	}

	/**
	 * 利用者更新
	 * 
	 * @param id
	 * @param lastName
	 * @param firstName
	 */
	public void update(int id, String lastName, String firstName) {
		// データ作成
		var customer = new Customer(id, firstName, lastName);
		// 更新
		customerRepository.save(customer);
	}
	
	/**
	 * 利用者削除
	 * 
	 * @param id
	 */
	public void delete(int id) {
		customerRepository.delete(id);
	}
	
}
