package com.kkk.demo.service;

import java.util.List;

import com.kkk.demo.api.req.ProductRequestParameter;
import com.kkk.demo.db1.entity.Product;

public interface ProductService {

	Product createProduct(Product request);

	Product getProduct(String id);

	Product replaceProduct(String id, Product request);

	void deleteProduct(String id);

	List<Product> getProducts(ProductRequestParameter param);

}
