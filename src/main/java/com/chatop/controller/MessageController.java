package com.chatop.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.chatop.DTO.MessageRequestDTO;
import com.chatop.service.MessageService;
import com.chatop.service.UserService;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/api/messages")
public class MessageController {
    @Autowired
    private MessageService messageService;

    @Autowired
    private UserService userService;

    // Envoi d'un message au propriétaire de la maison sélectionnée
    @PostMapping("")
    public ResponseEntity<Map<String, String>> sendMessage(@Valid @RequestBody MessageRequestDTO dto,
            Authentication authentication) throws Exception {
        // On s'assure que l'id de l'envoyeur corresponde bien à celui de l'utilisateur
        // connecté
        Integer userId = userService.getUserByEmail(authentication.getName()).getId();
        dto.setUser_id(userId);
        messageService.createMessage(dto);
        Map<String, String> response = new HashMap<>();
        response.put("message", "Message send with success");
        return ResponseEntity.ok(response);
    }
}