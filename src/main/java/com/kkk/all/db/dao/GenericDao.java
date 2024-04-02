package com.kkk.all.db.dao;

import java.util.List;

public interface GenericDao<T> {

	void delete(String id);

	/** ColumnMapRowMapper */
	List<T> find(Class<T> clazz);

}
