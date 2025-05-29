package com.ticketbooking.infrastructure.repository;

import com.ticketbooking.domain.model.Event;
import com.ticketbooking.domain.repository.EventRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

@Repository
public class InMemoryEventRepository implements EventRepository {
    private final Map<Long, Event> events = new ConcurrentHashMap<>();
    private final AtomicLong idGenerator = new AtomicLong(1);

    @Override
    public Event save(Event event) {
        if (event.getId() == null) {
            event.setId(idGenerator.getAndIncrement());
        }
        events.put(event.getId(), event);
        return event;
    }

    @Override
    public Optional<Event> findById(Long id) {
        return Optional.ofNullable(events.get(id));
    }

    @Override
    public List<Event> findAll() {
        return new ArrayList<>(events.values());
    }

    @Override
    public List<Event> findByName(String name) {
        return events.values().stream()
                .filter(event -> event.getName().equalsIgnoreCase(name))
                .collect(Collectors.toList());
    }

    @Override
    public List<Event> findByEventDateAfter(LocalDateTime date) {
        return events.values().stream()
                .filter(event -> event.getEventDate().isAfter(date))
                .sorted(Comparator.comparing(Event::getEventDate))
                .collect(Collectors.toList());
    }

    @Override
    public void deleteById(Long id) {
        events.remove(id);
    }
} 