package com.kkk.all.db.dao.impl;

import java.io.Serializable;

import org.springframework.data.jpa.repository.support.JpaEntityInformation;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;

import com.kkk.all.db.dao.GenericRepository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;

public class GenericRepositoryImpl<T, ID extends Serializable> extends SimpleJpaRepository<T, ID>
		implements GenericRepository<T, ID> {

	private EntityManager entityManager;

	public GenericRepositoryImpl(JpaEntityInformation<T, ?> entityInformation, EntityManager entityManager) {
		super(entityInformation, entityManager);
		this.entityManager = entityManager;
	}

	@Override
	public T find(String id) {
		CriteriaBuilder builder = entityManager.getCriteriaBuilder();
		CriteriaQuery<T> cQuery = builder.createQuery(getDomainClass());
		Root<T> root = cQuery.from(getDomainClass());
		cQuery.select(root).where(builder.equal(root.get("id"), id));
		TypedQuery<T> query = entityManager.createQuery(cQuery);
		return query.getSingleResult();
	}

	// ...
}
