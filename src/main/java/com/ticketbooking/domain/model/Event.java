package com.ticketbooking.domain.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.AccessLevel;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
public class Event {
    private Long id;
    private String name;
    private LocalDateTime eventDate;
    private Place place;
    @Setter(AccessLevel.NONE)
    private List<Ticket> tickets = new ArrayList<>();

    public Event(String name, LocalDateTime eventDate, Place place) {
        this.name = name;
        this.eventDate = eventDate;
        this.place = place;
        this.tickets = new ArrayList<>();
    }

    public void addTicket(Ticket ticket) {
        tickets.add(ticket);
        ticket.setEvent(this);
    }
} 