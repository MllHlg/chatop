package com.chatop.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.chatop.DTO.MessageRequestDTO;
import com.chatop.service.MessageService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/api/messages")
public class MessageController {
    @Autowired
    private MessageService messageService;

    @PostMapping("")
    public ResponseEntity<Map<String, String>> postMethodName(@RequestBody MessageRequestDTO dto) {
        messageService.createMessage(dto);
        Map<String, String> response = new HashMap<>();
        response.put("messages", "Message send with success");
        return ResponseEntity.ok(response);
    }
}