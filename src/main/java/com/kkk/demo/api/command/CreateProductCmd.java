package com.kkk.demo.api.command;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.kkk.demo.db1.entity.Product;
import com.kkk.demo.service.ProductService;
import com.kkk.manager.command.ManagerCommand;

@Component
public class CreateProductCmd implements ManagerCommand<Product, Product> {

	@Autowired
	private ProductService productService;

	@Override
	public Product execute(Product request) {
		return productService.createProduct(request);
	}
}
