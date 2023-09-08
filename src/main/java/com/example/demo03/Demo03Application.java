package com.example.demo03;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.example.demo03.domain.Customer;
import com.example.demo03.repository.CustomerRepository;

@SpringBootApplication
public class Demo03Application implements CommandLineRunner {
	@Autowired
	private CustomerRepository repository;

	public static void main(String[] args) {
		SpringApplication.run(Demo03Application.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		// 全検索
		System.out.println("全検索-------");
		List<Customer> customers = repository.findAll();
		customers.forEach(c -> System.out.println("ID：" + c.getId() + ", 名前：" + c.getLastName() + c.getFirstName()));
		
		// 新規登録
		System.out.println("新規登録-------");
		var customer1 = new Customer();
		customer1.setLastName("山田");
		customer1.setFirstName("花子");
		repository.save(customer1);
		
		// 更新
		System.out.println("更新-------");
		var customer2 = new Customer(2, "一郎", "木村");
		repository.save(customer2);
		
		// ID検索
		System.out.println("ID検索-------");
		Customer customer3 = repository.findOneById(2);
		System.out.println("ID：" + customer3.getId() + ", 名前：" + customer3.getLastName() + customer3.getFirstName());
		
		// 1件削除
		System.out.println("削除-------");
		repository.delete(2);
		
		// 複数登録
		System.out.println("複数登録-------");
		List<Customer> inputCustomers = new ArrayList<>();
		Arrays.asList(1, 2, 3).forEach(i -> {
			Customer customer = new Customer();
			customer.setLastName("テスト" + i);
			customer.setFirstName("太郎");
			inputCustomers.add(customer);
		});
		repository.saveMulti(inputCustomers);
		
		// 全検索
		System.out.println("全検索-------");
		repository.findAll()
		.forEach(c -> System.out.println("ID：" + c.getId() + ", 名前：" + c.getLastName() + c.getFirstName()));
	}

}
