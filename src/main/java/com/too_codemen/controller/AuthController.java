package com.too_codemen.controller;

import com.too_codemen.model.AuthenticationRequest;
import com.too_codemen.model.AuthenticationResponse;
import com.too_codemen.repository.UserRepository;
import com.too_codemen.service.JwtTokenUtil;
import com.too_codemen.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private UserService userService;
    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @PostMapping("/authenticate")
    public ResponseEntity<?> createAuthenticationToken(@RequestBody AuthenticationRequest authenticationRequest) throws Exception {
        authenticate(authenticationRequest.getEmail(), authenticationRequest.getPassword());
        final UserDetails userDetails = userService.loadUserByUsername(authenticationRequest.getEmail());
        final String token = jwtTokenUtil.generateToken(userDetails);
        System.out.println("успешно");
        return ResponseEntity.ok(new AuthenticationResponse(token));
    }

    private void authenticate(String email, String password) throws Exception {
        try {
            UserDetails userDetails = userService.loadUserByUsername(email);
            if (bCryptPasswordEncoder.matches(password, userDetails.getPassword())) {
                authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(email, password));
            } else {
                throw new BadCredentialsException("INVALID_CREDENTIALS");
            }
        } catch (UsernameNotFoundException e) {
            throw new UsernameNotFoundException("USER_DISABLED", e);
        }
    }

}

