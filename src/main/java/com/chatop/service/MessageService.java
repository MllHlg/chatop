package com.chatop.service;

import java.sql.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.chatop.DTO.MessageRequestDTO;
import com.chatop.model.Message;
import com.chatop.repository.MessageRepository;

@Service
public class MessageService {
    @Autowired
    private MessageRepository messageRepository;

    public Message toMessage(MessageRequestDTO dto) {
        Message message = new Message();
        message.setUser_id(dto.getUser_id());
        message.setRental_id(dto.getRental_id());
        message.setMessage(dto.getMessage());
        message.setCreated_at(new Date(System.currentTimeMillis()));
        message.setUpdated_at(new Date(System.currentTimeMillis()));
        return message;
    }

    public void createMessage(MessageRequestDTO messageRequestDTO) {
        Message message = toMessage(messageRequestDTO);
        messageRepository.save(message);
    }
}
