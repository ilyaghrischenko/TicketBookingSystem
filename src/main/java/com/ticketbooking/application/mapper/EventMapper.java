package com.ticketbooking.application.mapper;

import com.ticketbooking.application.dto.EventDTO;
import com.ticketbooking.domain.model.Event;

public class EventMapper {
    public static EventDTO toDTO(Event event) {
        return new EventDTO(
            event.getId(),
            event.getName(),
            event.getEventDate(),
            event.getPlace() != null ? event.getPlace().getId() : null
        );
    }
} 