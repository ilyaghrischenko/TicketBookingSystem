package com.ticketbooking.application.mapper;

import com.ticketbooking.application.dto.TicketDTO;
import com.ticketbooking.domain.model.Ticket;

public class TicketMapper {
    public static TicketDTO toDTO(Ticket ticket) {
        return new TicketDTO(
            ticket.getId(),
            ticket.getCost(),
            ticket.getNumber(),
            ticket.getCustomer() != null ? ticket.getCustomer().getId() : null,
            ticket.getEvent() != null ? ticket.getEvent().getId() : null,
            ticket.getStatus()
        );
    }
} 