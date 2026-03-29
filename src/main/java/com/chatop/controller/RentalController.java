package com.chatop.controller;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.chatop.DTO.RentalCreateFormDTO;
import com.chatop.DTO.RentalUpdateFormDTO;
import com.chatop.model.Rental;
import com.chatop.service.RentalService;
import com.chatop.service.UserService;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

@RestController
@RequestMapping("/api/rentals")
public class RentalController {
    @Autowired
    private RentalService rentalService;

    @Autowired
    private UserService userService;

    // Récupération de toutes les maisons
    @GetMapping("")
    public ResponseEntity<Map<String, Iterable<Rental>>> getRentals() {
        Map<String, Iterable<Rental>> response = new HashMap<>();
        response.put("rentals", rentalService.getRentals());
        return ResponseEntity.ok(response);
    }

    // Récupération des détails sur la maison dont l'id est spécifié
    @GetMapping("/{id}")
    public ResponseEntity<Rental> getRentalById(@PathVariable("id") final Integer id) throws Exception {
        Optional<Rental> rental = rentalService.getRentalById(id);
        if (rental.isPresent()) {
            return ResponseEntity.ok(rental.get());
        } else {
            throw new BadCredentialsException("La maison sélectionnée n'existe pas");
        }
    }

    // Création d'une nouvelle maison
    @PostMapping(value = "", consumes = "multipart/form-data")
    public ResponseEntity<Map<String, String>> createRental(@ModelAttribute RentalCreateFormDTO dto,
            Authentication authentication) {
        Integer userId = userService.getUserByEmail(authentication.getName()).getId();
        rentalService.createRental(dto, userId);
        Map<String, String> response = new HashMap<>();
        response.put("message", "Rental created !");
        return ResponseEntity.ok(response);
    }

    // Mise à jour de la maison dont l'id est spécifié
    @PutMapping(value = "/{id}", consumes = "multipart/form-data")
    public ResponseEntity<Map<String, String>> updateRental(@PathVariable("id") final Integer id,
            @ModelAttribute RentalUpdateFormDTO dto) throws Exception {
        Optional<Rental> r = rentalService.getRentalById(id);
        if (r.isPresent()) {
            Rental rental = r.get();
            // Vérification des informations à modifier
            if (dto.getName() != null) {
                rental.setName(dto.getName());
            }
            if (dto.getSurface() != 0) {
                rental.setSurface(dto.getSurface());
            }
            if (dto.getPrice() != 0) {
                rental.setPrice(dto.getPrice());
            }
            if (dto.getDescription() != null) {
                rental.setDescription(dto.getDescription());
            }
            rentalService.updateRental(rental);
            Map<String, String> response = new HashMap<>();
            response.put("message", "Rental updated !");
            return ResponseEntity.ok(response);
        } else {
            throw new BadCredentialsException("La maison sélectionnée n'existe pas");
        }
    }

}