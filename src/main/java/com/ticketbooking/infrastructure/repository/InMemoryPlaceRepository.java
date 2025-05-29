package com.ticketbooking.infrastructure.repository;

import com.ticketbooking.domain.model.Place;
import com.ticketbooking.domain.repository.PlaceRepository;
import org.springframework.stereotype.Repository;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

@Repository
public class InMemoryPlaceRepository implements PlaceRepository {
    private final Map<Long, Place> places = new ConcurrentHashMap<>();
    private final AtomicLong idGenerator = new AtomicLong(1);

    @Override
    public Place save(Place place) {
        if (place.getId() == null) {
            place.setId(idGenerator.getAndIncrement());
        }
        places.put(place.getId(), place);
        return place;
    }

    @Override
    public Optional<Place> findById(Long id) {
        return Optional.ofNullable(places.get(id));
    }

    @Override
    public List<Place> findAll() {
        return new ArrayList<>(places.values());
    }

    @Override
    public void deleteById(Long id) {
        places.remove(id);
    }
} 