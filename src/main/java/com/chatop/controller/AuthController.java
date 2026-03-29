package com.chatop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.chatop.DTO.AuthResponseDTO;
import com.chatop.DTO.LoginDTO;
import com.chatop.DTO.UserCreateDTO;
import com.chatop.DTO.UserDTO;
import com.chatop.service.JWTService;
import com.chatop.service.UserService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    @Autowired
    private UserService userService;

    @Autowired
    private JWTService jwtService;

    @Autowired
    private AuthenticationManager authenticationManager;

    // Création d'un utilisateur
    @PostMapping("/register")
    public ResponseEntity<AuthResponseDTO> register(@RequestBody UserCreateDTO dto) {
        // Ajout de l'utilisateur à la base de données
        userService.createUser(dto);
        // Authentification de l'utilisateur
        Authentication authentication = authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(dto.getEmail(), dto.getPassword()));
        // Création d'un JWT lié à l'utilisateur
        String token = jwtService.generateToken(authentication);
        // Envoie du token en réponse à la requête
        AuthResponseDTO response = new AuthResponseDTO();
        response.setToken(token);
        return ResponseEntity.ok(response);
    }

    // Connexion d'un utilisateur
    @PostMapping("/login")
    public ResponseEntity<AuthResponseDTO> login(@RequestBody LoginDTO dto) {
        // Authentification de l'utilisateur
        Authentication authentication = authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(dto.getEmail(), dto.getPassword()));
        // Création d'un JWT lié à l'utilisateur
        String token = jwtService.generateToken(authentication);
        // Envoie du token en réponse à la requête
        AuthResponseDTO response = new AuthResponseDTO();
        response.setToken(token);
        return ResponseEntity.ok(response);
    }

    // Récupération des informations de l'utilisateur authentifié
    @GetMapping("/me")
    public ResponseEntity<UserDTO> getCurrentUser(Authentication authentication) {
        UserDTO userDTO = userService.getUserByEmail(authentication.getName());
        return ResponseEntity.ok(userDTO);
    }
}