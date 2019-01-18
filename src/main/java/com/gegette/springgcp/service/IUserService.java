package com.gegette.springgcp.service;

import com.gegette.springgcp.model.UserDB;

import java.util.Collection;

public interface IUserService {
    Collection<UserDB> getUsers(String search);

    UserDB postUser(UserDB user);
}
