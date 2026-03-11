package org.apirest.repositories;

import jakarta.enterprise.context.ApplicationScoped;
import org.apirest.domain.entity.User;
import org.apirest.interfaces.UserRepository;

import java.util.List;
import java.util.Optional;

@ApplicationScoped
public class UserRepositoryImpl implements UserRepository {

    @Override
    public User save(User user) {
        user.persist();
        return user;
    }

    @Override
    public Optional<User> findById(Long id) {
        return User.findByIdOptional(id);
    }

    @Override
    public Optional<User> findByEmail(String email) {
        return User.find("email", email).firstResultOptional();
    }

    @Override
    public List<User> findAll() {
        return User.listAll();
    }

    @Override
    public void delete(User user) {
        user.delete();
    }
}
