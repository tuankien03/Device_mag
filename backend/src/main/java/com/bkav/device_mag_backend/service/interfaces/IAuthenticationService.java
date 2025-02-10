package com.bkav.device_mag_backend.service.interfaces;


import com.bkav.device_mag_backend.model.DTO.request.AuthenticationRequest;
import com.bkav.device_mag_backend.repository.DAO.interfaces.IUserDAO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
 public interface IAuthenticationService {
    public boolean authenticate(AuthenticationRequest authenticationRequest);
}
