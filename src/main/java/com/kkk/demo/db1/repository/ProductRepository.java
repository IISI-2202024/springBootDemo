package com.kkk.demo.db1.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.kkk.demo.db1.entity.Product;

public interface ProductRepository extends JpaRepository<Product, String> {

	Product findProductById(String id);

	List<Product> findByNameLike(String productName);

	/**
	 * 參數 ?1...
	 * 
	 * @param price
	 * @return
	 */
	@Query("SELECT u FROM Product u WHERE u.price = ?1")
	Optional<Product> getProductBySql(int price);

	/**
	 * 參數 @Param("id") -> :id
	 * 
	 * @param id
	 * @param price
	 * @return
	 */
	@Modifying
	@Query("UPDATE Product p SET p.price = :price, p.id = :newId, p.name = :name WHERE p.id = :id")
	Product updatePrice(@Param("id") String id, @Param("name") String name, @Param("price") int price);
}
