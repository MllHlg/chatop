package com.chatop.service;

import java.sql.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.chatop.DTO.RentalCreateFormDTO;
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

    public Rental toRental(RentalCreateFormDTO dto, Integer userId) {
        Rental rental = new Rental();
        rental.setOwner_id(userId);
        rental.setName(dto.getName());
        rental.setSurface(dto.getSurface());
        rental.setPrice(dto.getPrice());
        rental.setPicture(dto.getPicture().getOriginalFilename());
        rental.setDescription(dto.getDescription());
        rental.setCreated_at(new Date(System.currentTimeMillis()));
        rental.setUpdated_at(new Date(System.currentTimeMillis()));
        return rental;
    }

    public void createRental(RentalCreateFormDTO rentalFormDTO, Integer userId) {
        Rental rental = toRental(rentalFormDTO, userId);
        rentalRepository.save(rental);
    }

    public void updateRental(Rental rental) {
        rentalRepository.save(rental);
    }
}