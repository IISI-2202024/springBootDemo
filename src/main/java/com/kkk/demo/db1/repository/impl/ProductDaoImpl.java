package com.kkk.demo.db1.repository.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.ColumnMapRowMapper;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import com.kkk.all.db.dao.impl.GenericDaoImpl;
import com.kkk.demo.all.exception.CustMessageException;
import com.kkk.demo.db1.entity.Product;
import com.kkk.demo.db1.repository.ProductDao;

@Repository
public class ProductDaoImpl extends GenericDaoImpl<Product> implements ProductDao {

	/** PreparedStatementCreator KeyHolder */
	@Override
	public int insert(Product product) {
		String sql = "INSERT INTO product (id, name, price) VALUES (?, ?, ?)";
		KeyHolder keyHolder = new GeneratedKeyHolder();
		getJdbcTemplate().update(new PreparedStatementCreator() {
			@Override
			public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
				PreparedStatement ps = connection.prepareStatement(sql);
				ps.setString(1, product.getId());
				ps.setString(2, product.getName());
				ps.setInt(3, product.getPrice());
				return ps;
			}
		}, keyHolder);
		return keyHolder.getKey().intValue();
	}

	@Override
	public Product replace(String id, Product product) {
		return product;
	}

	@Override
	public void delete(String id) {
	}

	@Override
	public List<Map<String, Object>> find(String id) {
		Map<String, Object> params = new HashMap<>();
		params.put("id", id);
		return getJdbcTemplate().query("SELECT * FROM PRODUCT", new ColumnMapRowMapper());
	}

	@Override
	public void updateOther(Product product) throws CustMessageException {
		try {
			// do something
		} catch (Exception e) {
			new CustMessageException(e.getMessage());
		}
	}
}
