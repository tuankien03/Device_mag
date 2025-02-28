package com.bkav.device_mag_backend.repository.JpaRepository;

import com.bkav.device_mag_backend.model.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserRepository  extends JpaRepository<User, UUID> {
    Optional<User> findByUsername(String username);

    Page<User> findUsersByUsernameContaining(String username, Pageable pageable);
}
