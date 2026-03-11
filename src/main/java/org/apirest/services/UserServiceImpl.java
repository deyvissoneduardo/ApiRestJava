package org.apirest.services;

import io.smallrye.jwt.build.Jwt;
import jakarta.enterprise.context.ApplicationScoped;
import org.apirest.core.exception.BusinessException;
import org.apirest.core.exception.ResourceNotFoundException;
import org.apirest.domain.dto.*;
import org.apirest.domain.entity.User;
import org.apirest.interfaces.UserRepository;
import org.apirest.interfaces.UserService;

import java.time.Duration;
import java.util.List;

@ApplicationScoped
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserResponse create(UserRequest request) {
        userRepository.findByEmail(request.email())
            .ifPresent(u -> { throw new BusinessException("Email already registered", 409); });
        User user = new User();
        user.name = request.name();
        user.email = request.email();
        user.password = request.password();
        userRepository.save(user);
        return toResponse(user);
    }

    @Override
    public UserResponse findById(Long id) {
        User user = userRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("User not found"));
        return toResponse(user);
    }

    @Override
    public List<UserResponse> findAll() {
        return userRepository.findAll().stream()
            .map(this::toResponse)
            .toList();
    }

    @Override
    public UserResponse update(Long id, UserRequest request) {
        User user = userRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("User not found"));
        user.name = request.name();
        user.email = request.email();
        user.password = request.password();
        userRepository.save(user);
        return toResponse(user);
    }

    @Override
    public void delete(Long id) {
        User user = userRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("User not found"));
        userRepository.delete(user);
    }

    @Override
    public LoginResponse login(LoginRequest request) {
        User user = userRepository.findByEmail(request.email())
            .orElseThrow(() -> new BusinessException("Invalid credentials", 401));
        if (!user.password.equals(request.password())) {
            throw new BusinessException("Invalid credentials", 401);
        }
        String token = Jwt.issuer("https://example.com/issuer")
            .upn(user.email)
            .groups("user")
            .expiresIn(Duration.ofHours(24))
            .sign();
        return new LoginResponse(token, "Bearer");
    }

    private UserResponse toResponse(User user) {
        return new UserResponse(user.id, user.name, user.email);
    }
}
