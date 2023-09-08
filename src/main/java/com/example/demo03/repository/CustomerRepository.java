package com.example.demo03.repository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo03.domain.Customer;

@Repository
@Transactional
public class CustomerRepository {
	@Autowired
	private NamedParameterJdbcTemplate template;

	/**
	 * DBの結果（rs）をCustomerオブジェクトにバインドする
	 */
	private static final RowMapper<Customer> customerRowMapper = (rs, i) -> {
		Customer customer = new Customer();
		customer.setId(rs.getInt("id"));
		customer.setFirstName(rs.getString("first_name"));
		customer.setLastName(rs.getString("last_name"));
		return customer;
	};

	/**
	 * 利用者全検索
	 * 
	 * @return 検索結果
	 */
	public List<Customer> findAll() {
		// SQL文
		String sql = "SELECT id, first_name, last_name FROM customer";
		// 実行結果
		List<Customer> customers = template.query(sql, customerRowMapper);

		return customers;

	}

	/**
	 * 利用者ID検索
	 * 
	 * @param id
	 * @return 検索結果
	 */
	public Customer findOneById(int id) {
		// SQL文
		String sql = "SELECT id, first_name, last_name FROM customer WHERE id = :id";
		// パラメータ設定
		MapSqlParameterSource param = new MapSqlParameterSource();
		param.addValue("id", id);
		// 実行
		Customer resCustomer = template.queryForObject(sql, param, customerRowMapper);

		return resCustomer;
	}

	/**
	 * 利用者登録または更新
	 * 
	 * @param customer
	 */
	public void save(Customer customer) {
		// SQL文
		String sql = null;
		if (customer.getId() == null) {
			// 新規登録
			sql = "INSERT INTO customer(first_name, last_name) VALUES (:firstName, :lastName)";
		} else {
			// 更新
			sql = "UPDATE customer SET first_name=:firstName, last_name=:lastName WHERE id=:id";

		}
		// パラメータ設定
		SqlParameterSource param = new BeanPropertySqlParameterSource(customer);
		// 実行
		template.update(sql, param);
	}

	/**
	 * 利用者の削除
	 * 
	 * @param id
	 */
	public void delete(int id) {
		// SQL文
		String sql = "DELETE FROM customer WHERE id=:id";
		// パラメータ設定
		MapSqlParameterSource param = new MapSqlParameterSource();
		param.addValue("id", id);
		// 実行
		template.update(sql, param);
	}
	
	/**
	 * 利用者リストの登録
	 * 
	 * @param customerList
	 */
	public void saveMulti(List<Customer> customerList) {
		
		String sql = "INSERT INTO customer(first_name, last_name) VALUES (:firstName, :lastName)";
		
		List<SqlParameterSource> paramList = new ArrayList<>();
		for (Customer customer : customerList) {
			SqlParameterSource param = new BeanPropertySqlParameterSource(customer);
			paramList.add(param);
		}
		
		template.batchUpdate(sql, paramList.toArray(new SqlParameterSource[paramList.size()]));
	}

}
