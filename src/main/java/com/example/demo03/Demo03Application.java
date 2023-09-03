package com.example.demo03;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.example.demo03.domain.Customer;
import com.example.demo03.service.CustomerService;

@SpringBootApplication
public class Demo03Application implements CommandLineRunner {
	@Autowired
	private CustomerService service;
	
	public static void main(String[] args) {
		SpringApplication.run(Demo03Application.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		// 新規登録
		System.out.println("新規登録");
		service.create("山田", "花子");
        // 更新
        System.out.println("更新");
        service.update(2, "木村", "一郎");
        // ID検索
        System.out.println("ID検索");
        Customer customer = service.findOneById(2);
        System.out.println("ID：" + customer.getId() + ", 名前：" 
        		+ customer.getLastName() + customer.getFirstName());
        // 1件削除
        System.out.println("削除");
        service.delete(2);
		// 全検索
		System.out.println("全検索");
		List<Customer>customers = service.findAll();
        customers.forEach(c -> 
        	System.out.println("ID：" + c.getId() + ", 名前：" + c.getLastName() + c.getFirstName())
        );
	}

}
