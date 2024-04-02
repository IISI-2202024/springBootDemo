package com.kkk.demo.api.command;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.kkk.demo.db1.entity.Product;
import com.kkk.demo.service.ProductService;
import com.kkk.manager.command.ManagerCommand;

@Component
public class DeleteProductCmd implements ManagerCommand<String, Product> {

	@Autowired
	private ProductService productService;

	@Override
	public Product execute(String id) {
		productService.deleteProduct(id);
		return null;
	}

}
