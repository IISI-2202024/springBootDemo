package com.kkk.demo.db1.repository;

import java.util.List;
import java.util.Map;

import com.kkk.all.db.dao.GenericDao;
import com.kkk.demo.db1.entity.Product;

public interface ProductDao extends GenericDao<Product> {

	int insert(Product product);

	Product replace(String id, Product product);

	List<Map<String, Object>> find(String id);

	void updateOther(Product product);
}
