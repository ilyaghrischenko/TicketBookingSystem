package com.ticketbooking.api.controller;

import com.ticketbooking.domain.model.Place;
import com.ticketbooking.domain.repository.PlaceRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/places")
@RequiredArgsConstructor
@Tag(name = "Places", description = "API для работы с местами проведения")
public class PlaceController {
    private final PlaceRepository placeRepository;

    @Operation(summary = "Получить список всех мест проведения")
    @GetMapping
    public ResponseEntity<List<Place>> getAllPlaces() {
        return ResponseEntity.ok(placeRepository.findAll());
    }

    @Operation(summary = "Создать новое место проведения")
    @PostMapping
    public ResponseEntity<Place> createPlace(@RequestBody Place place) {
        return ResponseEntity.ok(placeRepository.save(place));
    }
} 