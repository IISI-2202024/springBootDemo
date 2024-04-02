package com.kkk.all.db.dao.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.ColumnMapRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import com.kkk.all.db.dao.GenericDao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

public class GenericDaoImpl<T> implements GenericDao<T> {

	@PersistenceContext
	private EntityManager entityManager;

	@Autowired
	@Qualifier("primaryJdbcTemplate")
	private JdbcTemplate jdbcTemplatePrimary;

	@Autowired
	@Qualifier("secondaryJdbcTemplate")
	private JdbcTemplate jdbcTemplateSecondary;

	public void delete(String id) {
	}

	protected JdbcTemplate getJdbcTemplate() {
		return this.toString().contains("db1") ? jdbcTemplatePrimary : jdbcTemplateSecondary;
	}

	/** ColumnMapRowMapper */
	@Override
	public List<T> find(Class<T> clazz) {
		String tblName = clazz.getClass().getName();
		return getJdbcTemplate().query("SELECT * FROM " + tblName, new ColumnMapRowMapper()).stream()
				.map(row -> mapRowToObject(row, clazz)).toList();
	}

	private T mapRowToObject(Map<String, Object> row, Class<T> clazz) {
		try {
			T instance = clazz.getDeclaredConstructor().newInstance();
			for (Map.Entry<String, Object> entry : row.entrySet()) {
				String fieldName = entry.getKey();
				Object value = entry.getValue();
				clazz.getDeclaredField(fieldName).set(instance, value);
			}
			return instance;
		} catch (Exception e) {
			throw new RuntimeException("Error mapping row to object", e);
		}
	}

}