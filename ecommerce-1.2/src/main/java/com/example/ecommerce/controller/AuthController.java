
package com.example.ecommerce.controller;

import com.example.ecommerce.dto.AuthRequest;
import com.example.ecommerce.dto.AuthResponse;
import com.example.ecommerce.entity.User;
import com.example.ecommerce.security.JwtUtil;
import com.example.ecommerce.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final UserService userService;
    private final JwtUtil jwtUtil;

    public AuthController(UserService userService, JwtUtil jwtUtil) {
        this.userService = userService;
        this.jwtUtil = jwtUtil;
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody AuthRequest req) {
        User u = userService.register(req.getUsername(), req.getPassword());
        String token = jwtUtil.generateToken(u.getUsername());
        return ResponseEntity.ok(new AuthResponse(token));
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody AuthRequest req) {
        return userService.findByUsername(req.getUsername())
                .filter(u -> org.springframework.security.crypto.bcrypt.BCrypt.checkpw(req.getPassword(), u.getPassword()))
                .map(u -> ResponseEntity.ok(new AuthResponse(jwtUtil.generateToken(u.getUsername()))))
                .orElse(ResponseEntity.status(401).body(java.util.Map.of("error","Invalid credentials")));
    }
}
