package com.kkk.demo.api.command;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.kkk.demo.db1.entity.Product;
import com.kkk.demo.service.ProductService;
import com.kkk.manager.command.ManagerCommand;

@Component
public class GetProductCmd implements ManagerCommand<String, Product> {

	@Autowired
	private ProductService productService;

	public Product execute(String id) {
		return productService.getProduct(id);
	}
}
