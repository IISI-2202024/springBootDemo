package com.kkk.demo.db2.repository;

import java.util.List;
import java.util.Map;

import com.kkk.all.db.dao.GenericDao;
import com.kkk.demo.db2.entity.Usr;

public interface UsrDao extends GenericDao<Usr> {

	/** PreparedStatementCreator KeyHolder */
	int insert(Usr usr);

	List<Map<String, Object>> find(String id);

}
