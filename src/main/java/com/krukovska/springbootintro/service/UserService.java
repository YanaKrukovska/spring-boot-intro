package com.krukovska.springbootintro.service;

import com.krukovska.springbootintro.model.User;

import java.util.List;

public interface UserService {

    User save(User user);

    List<User> findAll();

    void delete(long id);

}
