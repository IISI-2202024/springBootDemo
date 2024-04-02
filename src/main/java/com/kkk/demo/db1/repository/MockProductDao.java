package com.kkk.demo.db1.repository;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Repository;

import com.kkk.demo.api.req.ProductRequestParameter;
import com.kkk.demo.db1.entity.Product;

import jakarta.annotation.PostConstruct;

@Repository
public class MockProductDao {

	private final List<Product> productDB = new ArrayList<>();

	@PostConstruct
	private void initDB() {
		productDB.add(new Product("B0001", "Android Development (Java)", 380));
		productDB.add(new Product("B0002", "Android Development (Kotlin)", 420));
		productDB.add(new Product("B0003", "Data Structure (Java)", 250));
		productDB.add(new Product("B0004", "Finance Management", 450));
		productDB.add(new Product("B0005", "Human Resource Management", 330));
	}

	public Product insert(Product product) {
		productDB.add(product);
		return product;
	}

	public Product replace(String id, Product product) {
		Optional<Product> productOp = find(id);
		productOp.ifPresent(p -> {
			p.setName(product.getName());
			p.setPrice(product.getPrice());
		});

		return product;
	}

	public void delete(String id) {
		productDB.removeIf(p -> p.getId().equals(id));
	}

	public Optional<Product> find(String id) {
		return productDB.stream().filter(p -> p.getId().equals(id)).findFirst();
	}

	public List<Product> find(ProductRequestParameter param) {
		String keyword = Optional.ofNullable(param.getKey()).orElse("");
		String orderBy = param.getOrderBy();
		String sortRule = param.getSortRule();
		Comparator<Product> comparator = genSortComparator(orderBy, sortRule);

		return productDB.stream().filter(p -> p.getName().contains(keyword)).sorted(comparator)
				.collect(Collectors.toList());
	}

	private Comparator<Product> genSortComparator(String orderBy, String sortRule) {
		Comparator<Product> comparator = (p1, p2) -> 0;
		if (Objects.isNull(orderBy) || Objects.isNull(sortRule)) {
			return comparator;
		}

		if (orderBy.equalsIgnoreCase("price")) {
			comparator = Comparator.comparing(Product::getPrice);
		} else if (orderBy.equalsIgnoreCase("name")) {
			comparator = Comparator.comparing(Product::getName);
		}

		return sortRule.equalsIgnoreCase("desc") ? comparator.reversed() : comparator;
	}
}
