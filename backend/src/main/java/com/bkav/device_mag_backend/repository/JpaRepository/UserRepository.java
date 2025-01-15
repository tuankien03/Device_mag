package com.bkav.device_mag_backend.repository.JpaRepository;

import com.bkav.device_mag_backend.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;
import java.util.stream.Stream;

@Repository
public interface UserRepository  extends JpaRepository<User, UUID> {
    Optional<User> findByUsername(String username);
}
