package com.ticketbooking.api.controller;

import com.ticketbooking.application.dto.EventDTO;
import com.ticketbooking.application.service.EventService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/events")
@RequiredArgsConstructor
@Tag(name = "Events")
public class EventController {
    private final EventService eventService;

    @Operation(summary = "Get events by place")
    @GetMapping("/by-place/{placeId}")
    public ResponseEntity<List<EventDTO>> getEventsByPlace(@PathVariable Long placeId) {
        return ResponseEntity.ok(eventService.findEventsByPlace(placeId));
    }

    @Operation(summary = "Create event")
    @PostMapping
    public ResponseEntity<EventDTO> createEvent(@RequestBody EventDTO eventDTO) {
        return ResponseEntity.ok(eventService.createEvent(eventDTO.getName(), eventDTO.getEventDate(), eventDTO.getPlaceId()));
    }
} 