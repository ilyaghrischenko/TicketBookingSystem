package com.ticketbooking.application.service;

import com.ticketbooking.application.dto.EventDTO;
import com.ticketbooking.domain.model.Place;
import com.ticketbooking.domain.repository.EventRepository;
import com.ticketbooking.domain.repository.PlaceRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import java.time.LocalDateTime;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ActiveProfiles("test")
class EventServiceTest {
    @Autowired
    private EventService eventService;
    
    @Autowired
    private PlaceRepository placeRepository;
    
    @Autowired
    private EventRepository eventRepository;

    private Place createTestPlace() {
        Place testPlace = new Place("Test Address", "Test Place");
        return placeRepository.save(testPlace);
    }

    @Test
    void createEvent_Success() {
        Place testPlace = createTestPlace();
        LocalDateTime eventDate = LocalDateTime.now().plusDays(1);
        EventDTO event = eventService.createEvent("Test Event", eventDate, testPlace.getId());

        assertNotNull(event);
        assertEquals("Test Event", event.getName());
        assertEquals(eventDate, event.getEventDate());
        assertEquals(testPlace.getId(), event.getPlaceId());
    }

    @Test
    void createEvent_OverlappingEvents() {
        Place testPlace = createTestPlace();
        LocalDateTime baseTime = LocalDateTime.now().plusDays(1);
        eventService.createEvent("First Event", baseTime, testPlace.getId());

        LocalDateTime overlappingTime = baseTime.plusHours(2);
        assertThrows(RuntimeException.class, () ->
            eventService.createEvent("Second Event", overlappingTime, testPlace.getId())
        );

        LocalDateTime nonOverlappingTime = baseTime.plusHours(7);
        EventDTO event = eventService.createEvent("Third Event", nonOverlappingTime, testPlace.getId());
        assertNotNull(event);
    }

    @Test
    void findEventsByPlace() {
        Place testPlace = createTestPlace();
        LocalDateTime eventDate = LocalDateTime.now().plusDays(1);
        eventService.createEvent("Test Event 1", eventDate, testPlace.getId());
        eventService.createEvent("Test Event 2", eventDate.plusDays(1), testPlace.getId());

        var events = eventService.findEventsByPlace(testPlace.getId());
        assertEquals(2, events.size());
        assertTrue(events.stream().allMatch(e -> e.getPlaceId().equals(testPlace.getId())));
    }
} 