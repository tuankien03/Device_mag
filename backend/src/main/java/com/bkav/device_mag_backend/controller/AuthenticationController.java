package com.bkav.device_mag_backend.controller;


import Constant.CodeStatus;
import com.bkav.device_mag_backend.model.DTO.request.AuthenticationRequest;
import com.bkav.device_mag_backend.model.DTO.request.IntrospectRequest;
import com.bkav.device_mag_backend.model.DTO.response.ApiResponse;
import com.bkav.device_mag_backend.model.DTO.response.AuthenticationResponse;
import com.bkav.device_mag_backend.model.DTO.response.IntrospectResponse;
import com.bkav.device_mag_backend.service.AuthenticationService;
import com.nimbusds.jose.JOSEException;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthenticationController {
    private final AuthenticationService authenticationService;

    @PostMapping("/login")
    public ApiResponse<AuthenticationResponse> login(@RequestBody AuthenticationRequest authenticationRequest) {
        AuthenticationResponse result = authenticationService.authenticate(authenticationRequest);
        return new ApiResponse<>(CodeStatus.SUCCESS, CodeStatus.SUCCESS_TEXT, AuthenticationResponse.builder().authenticated(result.isAuthenticated()).token(result.getToken()).build());
    }

    @PostMapping("/introspect")
    public ApiResponse<IntrospectResponse> introspect(@RequestBody IntrospectRequest introspectRequest) throws ParseException, JOSEException {
        return new ApiResponse<>(CodeStatus.SUCCESS, CodeStatus.SUCCESS_TEXT,  authenticationService.introspect(introspectRequest));
    }
}
