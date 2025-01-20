package com.bkav.device_mag_backend.repository.DAO;

import com.bkav.device_mag_backend.entity.User;
import com.bkav.device_mag_backend.repository.DAO.interfaces.IUserDAO;
import com.bkav.device_mag_backend.repository.JpaRepository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.UUID;

@Component
public class UserDaoImpl implements IUserDAO {
    @Autowired
    private UserRepository userRepository;


    @Override
    public User findByUsername(String username) {
        return userRepository.findByUsername(username).orElse(null);
    }


    @Override
    public User save(User user) {

        return userRepository.save(user);
    }

    @Override
    public void delete(User user) {
        userRepository.delete(user);
    }

    @Override
    public Optional<User> findById(UUID uuid) {
        return userRepository.findById(uuid);
    }

    @Override
    public Iterable<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public Page<User> findAll(Pageable pageable) {
        return userRepository.findAll(pageable);
    }

    @Override
    public Iterable<User> findAll(Sort sort) {
        return userRepository.findAll(sort);
    }

    @Override
    public void deleteAll(Iterable<User> entities) {
        userRepository.deleteAll(entities);
    }
}
