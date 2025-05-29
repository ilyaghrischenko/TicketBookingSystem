package com.ticketbooking.domain.repository;

import com.ticketbooking.domain.model.Event;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface EventRepository {
    Event save(Event event);
    Optional<Event> findById(Long id);
    List<Event> findAll();
    void deleteById(Long id);
    List<Event> findByEventDateAfter(LocalDateTime date);
    List<Event> findByName(String name);
} 