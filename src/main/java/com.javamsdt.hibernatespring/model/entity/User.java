package com.javamsdt.hibernatespring.model.entity;
import com.javamsdt.hibernatespring.model.constant.UserConstantHolder;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Objects;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = UserConstantHolder.USER_TABLE)
public class User {

    @Id
    @GeneratedValue
    @Column(name = UserConstantHolder.USER_ID)
    private int id;

    @Column(name = UserConstantHolder.USER_NAME)
    private String username;

    @Column(name = UserConstantHolder.USER_PASSWORD)
    private String password;

    @Column(name = UserConstantHolder.USER_EMAIL)
    private String email;

    public User(String username, String email, String password) {
        setUsername(username);
        setEmail(email);
        setPassword(password);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return id == user.id &&
                Objects.equals(username, user.username) &&
                Objects.equals(password, user.password) &&
                Objects.equals(email, user.email);
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;

        result = prime * result + id;
        result = prime * (username != null ? username.hashCode() : 0);
        result = prime * (password != null ? password.hashCode() : 0);
        result = prime * (email != null ? email.hashCode() : 0);

        return result;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
