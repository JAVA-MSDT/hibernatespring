package com.javamsdt.hibernatespring.model.dao;

import java.util.List;

public interface IDao<T> {
      List<T> list();

      T get(int id);

      void saveOrUpdate(T t);

      void delete(int id);
}
