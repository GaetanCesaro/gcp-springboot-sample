package com.gegette.springgcp.service;

import com.gegette.springgcp.model.UserDB;

public interface IUserService {
    UserDB getUser(String search);

    UserDB postUser(UserDB user);
}
