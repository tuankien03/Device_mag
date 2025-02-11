package com.bkav.device_mag_backend.service;

import com.bkav.device_mag_backend.exception.BadRequestException;
import com.bkav.device_mag_backend.exception.EntityNotFoundException;
import com.bkav.device_mag_backend.model.DTO.request.AuthenticationRequest;
import com.bkav.device_mag_backend.model.DTO.request.IntrospectRequest;
import com.bkav.device_mag_backend.model.DTO.response.AuthenticationResponse;
import com.bkav.device_mag_backend.model.DTO.response.IntrospectResponse;
import com.bkav.device_mag_backend.model.DTO.response.UserAuthenticationDTO;
import com.bkav.device_mag_backend.service.interfaces.IAuthenticationService;
import com.bkav.device_mag_backend.service.interfaces.IUserService;
import com.nimbusds.jose.*;
import com.nimbusds.jose.crypto.MACSigner;
import com.nimbusds.jose.crypto.MACVerifier;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.SignedJWT;
import lombok.RequiredArgsConstructor;
import lombok.experimental.NonFinal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;


@Service
@RequiredArgsConstructor
public class AuthenticationService implements IAuthenticationService {
    private final IUserService userService;
    private final PasswordEncoder passwordEncoder;

    @NonFinal
    @Value("${jwt.signer.key}")
    protected String SIGNER_KEY;




    @Override
    public AuthenticationResponse authenticate(AuthenticationRequest authenticationRequest) {
        UserAuthenticationDTO user = userService.findUserByUsername(authenticationRequest.getUsername());
        if(user == null) {
            throw new EntityNotFoundException("User không tồn tại!!");
        }

        boolean authenticated = passwordEncoder.matches(authenticationRequest.getPassword(), user.getPassword());

        AuthenticationResponse authenticationResponse = AuthenticationResponse.builder().authenticated(authenticated).build();
        if (!authenticated) {
            throw new BadRequestException("Sai mật khẩu rồi bro!!");
        }
        var token = generateToken(user);
        authenticationResponse.setToken(token);

        return authenticationResponse;
    }

    @Override
    public IntrospectResponse introspect(IntrospectRequest accessToken) throws JOSEException, ParseException {
        String token = accessToken.getToken();
        JWSVerifier verifier = new MACVerifier(SIGNER_KEY.getBytes());
        SignedJWT signedJWT = SignedJWT.parse(token);
        Date expiration = signedJWT.getJWTClaimsSet().getExpirationTime();

        var verifiedToken = signedJWT.verify(verifier);

        return IntrospectResponse.builder().valid(verifiedToken && expiration.after(new Date())).build();
    }

    public String generateToken(UserAuthenticationDTO user) {
        JWSHeader header = new JWSHeader(JWSAlgorithm.HS512);
        System.out.println(user.getRole().getValue());
        JWTClaimsSet jwtClaimsSet = new JWTClaimsSet.Builder()
                .subject(user.getUsername())
                .claim("roles", user.getRole().getValue())
                .issuer(user.getUsername())
                .issueTime(new Date())
                .expirationTime(new Date(Instant.now().plus(1, ChronoUnit.DAYS).toEpochMilli()))
                .build();

        Payload payload = new Payload(jwtClaimsSet.toJSONObject());

        JWSObject jwsObject = new JWSObject(header, payload);

        try {
            jwsObject.sign(new MACSigner(SIGNER_KEY.getBytes()));
            return jwsObject.serialize();
        } catch (JOSEException e) {
            throw new RuntimeException(e);
        }
    }

}
