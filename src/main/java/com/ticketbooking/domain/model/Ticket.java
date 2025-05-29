package com.ticketbooking.domain.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
public class Ticket {
    private Long id;
    private double cost;
    private String number;
    @ToString.Exclude
    private Customer customer;
    @ToString.Exclude
    private Event event;
    private TicketStatus status = TicketStatus.FREE;

    public Ticket(double cost, String number, Event event) {
        this.cost = cost;
        this.number = number;
        this.event = event;
        this.status = TicketStatus.FREE;
    }
} 