package com.chatop.controller;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.chatop.model.Rental;
import com.chatop.service.RentalService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


@RestController
@RequestMapping("/api/rentals")
public class RentalController {
    @Autowired
    private RentalService rentalService;

    @GetMapping("")
    public ResponseEntity<Map<String, Iterable<Rental>>> getRentals() {
        Map<String, Iterable<Rental>> response = new HashMap<>();
        response.put("rentals", rentalService.getRentals());
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Rental> getRentalById(@PathVariable("id") final Integer id) throws Exception {
        Optional<Rental> rental = rentalService.getRentalById(id);
        if(rental.isPresent()) {
            return ResponseEntity.ok(rental.get());
        } else {
            throw new Exception();
        }
    }
    
}