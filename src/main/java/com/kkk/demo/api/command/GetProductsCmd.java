package com.kkk.demo.api.command;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.kkk.demo.api.req.ProductRequestParameter;
import com.kkk.demo.db1.entity.Product;
import com.kkk.demo.service.ProductService;
import com.kkk.manager.command.ManagerCommand;

@Component
public class GetProductsCmd implements ManagerCommand<ProductRequestParameter, List<Product>> {

	@Autowired
	private ProductService productService;

	@Override
	public List<Product> execute(ProductRequestParameter request) {
		return productService.getProducts(request);
	}
}
