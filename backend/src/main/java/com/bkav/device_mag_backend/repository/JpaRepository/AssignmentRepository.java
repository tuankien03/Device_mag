package com.bkav.device_mag_backend.repository.JpaRepository;

import com.bkav.device_mag_backend.model.entity.Assignment;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface AssignmentRepository extends JpaRepository<Assignment, UUID> {
}
