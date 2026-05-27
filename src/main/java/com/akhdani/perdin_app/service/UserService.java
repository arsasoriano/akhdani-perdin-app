package com.akhdani.perdin_app.service;

import com.akhdani.perdin_app.dto.UserRequest;
import com.akhdani.perdin_app.entity.User;

import java.util.List;

public interface UserService {
    void saveUser(UserRequest request);

    List<User> getAllUser();
}
