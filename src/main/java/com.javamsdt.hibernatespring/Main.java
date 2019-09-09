package com.javamsdt.hibernatespring;

import com.javamsdt.hibernatespring.model.annotationconfig.ApplicationContextConfig;
import com.javamsdt.hibernatespring.model.dao.UserDao;
import com.javamsdt.hibernatespring.model.entity.User;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {
    public static void main(String[] args) {

        ApplicationContext context = new AnnotationConfigApplicationContext(ApplicationContextConfig.class);
        UserDao userDao = context.getBean(UserDao.class);

        User user = new User("Ahmed", "Email", "Password");
        userDao.saveOrUpdate(user);

        User user1 = userDao.get(1);
        System.out.println(user1);


        System.out.println("hibernatespring");
    }
}
