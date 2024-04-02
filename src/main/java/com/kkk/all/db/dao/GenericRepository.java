package com.kkk.all.db.dao;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;

public interface GenericRepository<T, ID extends Serializable> extends JpaRepository<T, ID> {

	public T find(String id);

}