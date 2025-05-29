package com.ticketbooking.domain.repository;

import com.ticketbooking.domain.model.Place;
import java.util.Optional;
import java.util.List;

public interface PlaceRepository {
    Place save(Place place);
    Optional<Place> findById(Long id);
    List<Place> findAll();
    void deleteById(Long id);
} 