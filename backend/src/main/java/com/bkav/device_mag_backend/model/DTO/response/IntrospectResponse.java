package com.bkav.device_mag_backend.model.DTO.response;


import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class IntrospectResponse {
    private boolean valid;
}
