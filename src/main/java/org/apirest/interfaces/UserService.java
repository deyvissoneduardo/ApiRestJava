package org.apirest.interfaces;

import org.apirest.domain.dto.LoginRequest;
import org.apirest.domain.dto.LoginResponse;
import org.apirest.domain.dto.UserRequest;
import org.apirest.domain.dto.UserResponse;

import java.util.List;

public interface UserService {
    UserResponse create(UserRequest request);
    UserResponse findById(Long id);
    List<UserResponse> findAll();
    UserResponse update(Long id, UserRequest request);
    void delete(Long id);
    LoginResponse login(LoginRequest request);
}
