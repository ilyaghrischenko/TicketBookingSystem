package com.ticketbooking.application.service;

import com.ticketbooking.application.dto.EventDTO;
import com.ticketbooking.application.dto.TicketDTO;
import com.ticketbooking.application.mapper.EventMapper;
import com.ticketbooking.application.mapper.TicketMapper;
import com.ticketbooking.domain.model.Customer;
import com.ticketbooking.domain.model.Event;
import com.ticketbooking.domain.model.Ticket;
import com.ticketbooking.domain.model.TicketStatus;
import com.ticketbooking.domain.repository.CustomerRepository;
import com.ticketbooking.domain.repository.EventRepository;
import com.ticketbooking.domain.repository.TicketRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TicketBookingService {
    private final EventRepository eventRepository;
    private final TicketRepository ticketRepository;
    private final CustomerRepository customerRepository;

    public List<EventDTO> findUpcomingEvents() {
        return eventRepository.findAll().stream()
                .filter(event -> event.getEventDate().isAfter(LocalDateTime.now()))
                .map(EventMapper::toDTO)
                .collect(Collectors.toList());
    }

    public List<TicketDTO> findAvailableTicketsByEventName(String eventName) {
        return eventRepository.findByName(eventName).stream()
                .flatMap(event -> event.getTickets().stream())
                .filter(ticket -> ticket.getStatus() == TicketStatus.FREE)
                .map(TicketMapper::toDTO)
                .collect(Collectors.toList());
    }

    public TicketDTO assignTicketToCustomer(Long ticketId, Long customerId) {
        Ticket ticket = ticketRepository.findById(ticketId)
                .orElseThrow(() -> new RuntimeException("Ticket not found"));

        if (ticket.getStatus() != TicketStatus.FREE) {
            throw new RuntimeException("Ticket is already sold");
        }

        Customer customer = customerRepository.findById(customerId)
                .orElseThrow(() -> new RuntimeException("Customer not found"));

        ticket.setCustomer(customer);
        ticket.setStatus(TicketStatus.SOLD);
        customer.addTicket(ticket);

        ticketRepository.save(ticket);
        customerRepository.save(customer);

        return TicketMapper.toDTO(ticket);
    }
} 