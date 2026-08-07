package com.code.api.controllers;

import com.code.api.models.AuthRequest;
import com.code.api.models.AuthResponse;
import com.code.api.models.RegisterRequest;
import com.code.api.models.Users;
import com.code.api.reposatories.IUsersrepository;
import com.code.api.security.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("auth/")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private IUsersrepository usersRepository;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("login")
    public ResponseEntity<?> login(@RequestBody AuthRequest request) {
        try {
            Authentication auth = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword())
            );

            UserDetails userDetails = (UserDetails) auth.getPrincipal();
            List<String> roles = userDetails.getAuthorities().stream().map(a -> a.getAuthority()).collect(Collectors.toList());
            String token = jwtUtil.generateToken(userDetails.getUsername(), roles);
            return ResponseEntity.ok(new AuthResponse(token));
        } catch (BadCredentialsException ex) {
            return ResponseEntity.status(401).body("Invalid username or password");
        }
    }

    // optional: registration endpoint
    @PostMapping("register")
    public ResponseEntity<?> register(@RequestBody RegisterRequest req) {
        if (usersRepository.findByEmailid(req.getUsername()) != null) {
            return ResponseEntity.badRequest().body("Username already exists");
        }
        Users u = new Users();
        u.setEmailid(req.getUsername());
        u.setName(req.getName());
        u.setPassword(passwordEncoder.encode(req.getPassword()));
        u.setRole(req.getRole() != null ? req.getRole() : "USER");
        usersRepository.save(u);
        return ResponseEntity.ok("User created");
    }
}

