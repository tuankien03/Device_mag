package com.bkav.device_mag_backend.service;

import com.bkav.device_mag_backend.exception.EntityNotFoundException;
import com.bkav.device_mag_backend.model.DTO.request.AuthenticationRequest;
import com.bkav.device_mag_backend.service.interfaces.IAuthenticationService;
import com.bkav.device_mag_backend.service.interfaces.IUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class AuthenticationService implements IAuthenticationService {
    private final IUserService userService;

    @Override
    public boolean authenticate(AuthenticationRequest authenticationRequest) {
        var user = userService.findUserByUsername(authenticationRequest.getUsername());
        if(user == null) {
            throw new EntityNotFoundException("User không tồn tại!!");
        }

        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder(6);


        return passwordEncoder.matches(user.getPassword(), authenticationRequest.getPassword());
    }

}
