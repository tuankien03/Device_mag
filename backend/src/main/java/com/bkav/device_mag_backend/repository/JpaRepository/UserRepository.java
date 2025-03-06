package com.bkav.device_mag_backend.repository.JpaRepository;

import com.bkav.device_mag_backend.model.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserRepository  extends JpaRepository<User, UUID> {
    Optional<User> findByUsername(String username);
    @Query("SELECT u FROM User u WHERE (LOWER(u.username) LIKE LOWER(CONCAT('%', :searchText, '%')) OR :searchText IS NULL OR :searchText = '')")
    Page<User> findUsersByUsernameContaining(@Param("searchText") String username, Pageable pageable);
}
