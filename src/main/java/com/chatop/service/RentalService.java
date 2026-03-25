package com.chatop.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.chatop.model.Rental;
import com.chatop.repository.RentalRepository;

@Service
public class RentalService {
    @Autowired
    private RentalRepository rentalRepository;

    public Iterable<Rental> getRentals() {
        return rentalRepository.findAll();
    }

    public Optional<Rental> getRentalById(Integer id) {
        return rentalRepository.findById(id);
    }
}