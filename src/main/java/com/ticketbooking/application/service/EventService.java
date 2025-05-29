package com.ticketbooking.application.service;

import com.ticketbooking.application.dto.EventDTO;
import com.ticketbooking.application.mapper.EventMapper;
import com.ticketbooking.domain.model.Event;
import com.ticketbooking.domain.model.Place;
import com.ticketbooking.domain.repository.EventRepository;
import com.ticketbooking.domain.repository.PlaceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class EventService {
    private final EventRepository eventRepository;
    private final PlaceRepository placeRepository;

    public EventDTO createEvent(String name, LocalDateTime eventDate, Long placeId) {
        Place place = placeRepository.findById(placeId)
                .orElseThrow(() -> new RuntimeException("Place not found"));

        List<Event> eventsAtPlace = eventRepository.findByEventDateAfter(eventDate.minusHours(3));
        boolean hasOverlap = eventsAtPlace.stream()
                .filter(e -> e.getPlace() != null && placeId.equals(e.getPlace().getId()))
                .anyMatch(e -> {
                    LocalDateTime start = e.getEventDate().minusHours(3);
                    LocalDateTime end = e.getEventDate().plusHours(3);
                    return eventDate.isAfter(start) && eventDate.isBefore(end);
                });

        if (hasOverlap) {
            throw new RuntimeException("There is already an event at this place at the specified time");
        }

        Event event = new Event(name, eventDate, place);
        return EventMapper.toDTO(eventRepository.save(event));
    }

    public List<EventDTO> findEventsByPlace(Long placeId) {
        return eventRepository.findAll().stream()
                .filter(event -> event.getPlace() != null && placeId.equals(event.getPlace().getId()))
                .map(EventMapper::toDTO)
                .toList();
    }
} 