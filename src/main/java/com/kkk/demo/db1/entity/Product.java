package com.kkk.demo.db1.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "PRODUCT")
public class Product {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private String id;

	@Column(name = "NAME")
	private String name;

	@Column(name = "PRICE")
	private int price;

	public Product() {
	}

	public Product(String id, String name, int price) {
		this.id = id;
		this.name = name;
		this.price = price;
	}

}
