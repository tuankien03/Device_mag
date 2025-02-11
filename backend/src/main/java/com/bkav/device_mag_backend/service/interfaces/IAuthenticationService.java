package com.bkav.device_mag_backend.service.interfaces;


import com.bkav.device_mag_backend.model.DTO.request.AuthenticationRequest;
import com.bkav.device_mag_backend.model.DTO.request.IntrospectRequest;
import com.bkav.device_mag_backend.model.DTO.response.AuthenticationResponse;
import com.bkav.device_mag_backend.model.DTO.response.IntrospectResponse;
import com.nimbusds.jose.JOSEException;
import org.springframework.stereotype.Service;

import java.text.ParseException;

@Service
 public interface IAuthenticationService {
     AuthenticationResponse authenticate(AuthenticationRequest authenticationRequest);
     IntrospectResponse introspect(IntrospectRequest accessToken) throws JOSEException, ParseException;
}
