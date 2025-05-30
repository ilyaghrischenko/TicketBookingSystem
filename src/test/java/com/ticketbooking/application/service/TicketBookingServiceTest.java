package com.ticketbooking.application.service;

import com.ticketbooking.application.dto.EventDTO;
import com.ticketbooking.application.dto.TicketDTO;
import com.ticketbooking.domain.model.*;
import com.ticketbooking.domain.repository.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ActiveProfiles("test")
class TicketBookingServiceTest {
    @Autowired
    private TicketBookingService ticketBookingService;
    
    @Autowired
    private CustomerRepository customerRepository;
    
    @Autowired
    private EventRepository eventRepository;
    
    @Autowired
    private TicketRepository ticketRepository;

    @Test
    void findAvailableTicketsByEventName() {
        List<Event> allEvents = eventRepository.findAll();
        assertFalse(allEvents.isEmpty(), "Should have generated events");
        
        Event firstEvent = allEvents.get(0);
        String eventName = firstEvent.getName();

        List<TicketDTO> availableTickets = ticketBookingService.findAvailableTicketsByEventName(eventName);

        assertFalse(availableTickets.isEmpty(), "Should have found available tickets for event: " + eventName);
        availableTickets.forEach(ticket -> {
            assertEquals(TicketStatus.FREE, ticket.getStatus());
            assertEquals(firstEvent.getId(), ticket.getEventId(), 
                "Ticket should belong to the event we searched for");
        });
    }

    @Test
    void findUpcomingEvents() {
        List<EventDTO> upcomingEvents = ticketBookingService.findUpcomingEvents();
        assertFalse(upcomingEvents.isEmpty(), "Should have found upcoming events");
    }

    @Test
    void assignTicketToCustomer() {
        List<Event> allEvents = eventRepository.findAll();
        Event event = allEvents.get(0);
        Ticket freeTicket = event.getTickets().stream()
                .filter(t -> t.getStatus() == TicketStatus.FREE)
                .findFirst()
                .orElseThrow();

        List<Customer> customers = customerRepository.findAll();
        Customer customer = customers.get(0);

        TicketDTO assignedTicket = ticketBookingService.assignTicketToCustomer(
                freeTicket.getId(),
                customer.getId()
        );

        assertEquals(TicketStatus.SOLD, assignedTicket.getStatus());
        assertEquals(customer.getId(), assignedTicket.getCustomerId());
        assertTrue(customer.getTickets().stream()
                .anyMatch(t -> t.getId().equals(assignedTicket.getId())));
    }

    @Test
    void assignTicketToCustomer_ticketAlreadySold() {
        List<Event> allEvents = eventRepository.findAll();
        Event event = allEvents.get(0);
        Ticket ticket = event.getTickets().get(0);
        Customer customer1 = customerRepository.findAll().get(0);
        Customer customer2 = customerRepository.findAll().get(1);

        ticketBookingService.assignTicketToCustomer(ticket.getId(), customer1.getId());

        assertThrows(RuntimeException.class, () ->
            ticketBookingService.assignTicketToCustomer(ticket.getId(), customer2.getId())
        );
    }
} 