package com.chatop.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.chatop.model.Message;

@Repository
public interface MessageRepository extends CrudRepository<Message, Integer> {

}
