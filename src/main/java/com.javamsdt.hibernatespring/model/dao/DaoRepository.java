package com.javamsdt.hibernatespring.model.dao;

import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository("daoRepository")
public abstract class DaoRepository<T> implements IDao<T> {

    @PersistenceContext
    protected EntityManager entityManager;




}
