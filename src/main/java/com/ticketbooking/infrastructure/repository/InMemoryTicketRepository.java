package com.ticketbooking.infrastructure.repository;

import com.ticketbooking.domain.model.Ticket;
import com.ticketbooking.domain.model.TicketStatus;
import com.ticketbooking.domain.repository.TicketRepository;
import org.springframework.stereotype.Repository;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

@Repository
public class InMemoryTicketRepository implements TicketRepository {
    private final Map<Long, Ticket> tickets = new ConcurrentHashMap<>();
    private final AtomicLong idGenerator = new AtomicLong(1);

    @Override
    public Ticket save(Ticket ticket) {
        if (ticket.getId() == null) {
            ticket.setId(idGenerator.getAndIncrement());
        }
        tickets.put(ticket.getId(), ticket);
        return ticket;
    }

    @Override
    public Optional<Ticket> findById(Long id) {
        return Optional.ofNullable(tickets.get(id));
    }

    @Override
    public List<Ticket> findAll() {
        return new ArrayList<>(tickets.values());
    }

    @Override
    public List<Ticket> findByEventIdAndStatus(Long eventId, TicketStatus status) {
        return tickets.values().stream()
                .filter(ticket -> ticket.getEvent().getId().equals(eventId) && ticket.getStatus() == status)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteById(Long id) {
        tickets.remove(id);
    }
} 