package com.bkav.device_mag_backend.model.entity;


import jakarta.persistence.*;
import lombok.Cleanup;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Data
@Table(name = "user_session")
public class UserSession {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "session_id",columnDefinition = "uuid DEFAULT gen_random_uuid()", updatable = false, nullable = false)
    private Long sessionId;

    @Column(name = "user_id")
    private UUID userId;

    @Column(name = "token")
    private String token;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "expires_at")
    private LocalDateTime expiresAt;

    @Column(name = "revoked")
    private boolean revoked;

}
