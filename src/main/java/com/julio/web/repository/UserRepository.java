package com.julio.web.repository;

import com.julio.web.handler.FieldMandatoryException;
import org.springframework.stereotype.Repository;
import com.julio.web.domain.User;

import java.util.ArrayList;
import java.util.List;

@Repository
public class UserRepository {
    public void save(User user){
        if(user.getEmail()==null)
            throw new FieldMandatoryException("Email");

        if(user.getPassword()==null)
            throw new FieldMandatoryException("Password");

        if(user.getId()==null)
            System.out.println("SAVE - Receiving the user in repository layer");
        else
            System.out.println("UPDATE - Receiving the user in repository layer");

        System.out.println(user);
    }

    public void deleteById(Long id){
        System.out.println(String.format("DELETE/id  - Receiving id: %d to delete a user", id));
        System.out.println(id);
    }

    public List<User> findAll(){
        System.out.println("LIST - Listing the system users");
        List<User> users = new ArrayList<>();
        users.add(new User(
                "Julio",
                "Cesar",
                "julior.cesar.t14@gmail.com",
                "password",
                "92991253065"));
        users.add(new User(
                "Vejo Lucas",
                "Gabiror Mayos",
                "gabiror.mayos@gmail.com",
                "123456",
                "92991253065"));
        return users;
    }

    public User findById(Long id){
        System.out.println(String.format("FIND/id  - Receiving the id: %d to locate a user", id));
        return new User(
                "Julio",
                "Cesar",
                "julior.cesar.t14@gmail.com",
                "password",
                "92991253065");
    }

    public User findByEmail(String email){
        System.out.println(String.format("FIND/id  - Receiving the email: %s to locate a user", email));
        return new User(
                "Julio",
                "Cesar",
                "julior.cesar.t14@gmail.com",
                "password",
                "92991253065");
    }
}
