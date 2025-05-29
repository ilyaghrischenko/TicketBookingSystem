package com.ticketbooking.domain.repository;

import com.ticketbooking.domain.model.Ticket;
import com.ticketbooking.domain.model.TicketStatus;
import java.util.Optional;
import java.util.List;

public interface TicketRepository {
    Ticket save(Ticket ticket);
    Optional<Ticket> findById(Long id);
    List<Ticket> findAll();
    List<Ticket> findByEventIdAndStatus(Long eventId, TicketStatus status);
    void deleteById(Long id);
} 