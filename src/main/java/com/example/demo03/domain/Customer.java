package com.example.demo03.domain;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Customer implements Serializable {
	/** id */
	private Integer id;
	/** 名 */
	private String firstName;
	/** 姓 */
	private String lastName;
}
