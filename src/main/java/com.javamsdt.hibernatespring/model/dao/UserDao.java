package com.javamsdt.hibernatespring.model.dao;

import com.javamsdt.hibernatespring.model.entity.User;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository("userDao")
@Transactional
public class UserDao extends DaoRepository<User> {


    @Override
    public List<User> list() {

        return null;
    }

    @Override
    public User get(int id) {
        /*String getById = FIND_BY_ID + id;
        Query query = sessionFactory.openSession().createQuery(FIND_BY_ID + id);
        List<User> users = (List<User>) query.list();

        if (!users.equals(null) && !users.isEmpty()) {
            return users.get(0);
        }*/
        return entityManager.find(User.class, id);
    }

    @Override
    public void saveOrUpdate(User user) {
        entityManager.persist(user);

      //  transaction.begin();
       /* try {
            entityManager.persist(user);
        //    transaction.commit();
        }catch (Exception ex){
      //      transaction.rollback();
        }*/

    }

    @Override
    public void delete(int id) {
    }
}
