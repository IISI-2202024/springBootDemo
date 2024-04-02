package com.kkk.demo.api.command;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.kkk.demo.db1.entity.Product;
import com.kkk.demo.service.ProductService;
import com.kkk.manager.command.ManagerCommand;

@Component
public class ReplaceProductCmd implements ManagerCommand<Product, Product> {

	@Autowired
	private ProductService productService;

	@Override
	public Product execute(Product request) throws Exception {
		return null;
	}

	public Product updateProduct(String id, Product request) {
		return productService.replaceProduct(id, request);
	}

}
