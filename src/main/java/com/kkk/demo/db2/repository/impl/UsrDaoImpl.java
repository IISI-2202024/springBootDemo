package com.kkk.demo.db2.repository.impl;

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
import com.kkk.demo.db2.entity.Usr;
import com.kkk.demo.db2.repository.UsrDao;

@Repository
public class UsrDaoImpl extends GenericDaoImpl<Usr> implements UsrDao {

	/** PreparedStatementCreator KeyHolder */
	@Override
	public int insert(Usr user) {
		String sql = "INSERT INTO usr (id, name) VALUES (?, ?)";
		KeyHolder keyHolder = new GeneratedKeyHolder();
		getJdbcTemplate().update(new PreparedStatementCreator() {
			@Override
			public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
				PreparedStatement ps = connection.prepareStatement(sql);
				ps.setString(1, user.getId());
				ps.setString(2, user.getName());
				return ps;
			}
		}, keyHolder);
		return keyHolder.getKey().intValue();
	}

	@Override
	public List<Map<String, Object>> find(String id) {
		Map<String, Object> params = new HashMap<>();
		params.put("id", id);
		return getJdbcTemplate().query("SELECT * FROM USR", new ColumnMapRowMapper());
	}
}
