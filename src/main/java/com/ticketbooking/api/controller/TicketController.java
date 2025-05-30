package com.ticketbooking.api.controller;

import com.ticketbooking.application.dto.EventDTO;
import com.ticketbooking.application.dto.TicketDTO;
import com.ticketbooking.application.service.TicketBookingService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tickets")
@RequiredArgsConstructor
@Tag(name = "Tickets")
public class TicketController {
    private final TicketBookingService ticketBookingService;

    @Operation(summary = "Get upcoming events")
    @GetMapping("/upcoming-events")
    public ResponseEntity<List<EventDTO>> getUpcomingEvents() {
        return ResponseEntity.ok(ticketBookingService.findUpcomingEvents());
    }

    @Operation(summary = "Get available tickets")
    @GetMapping("/available")
    public ResponseEntity<List<TicketDTO>> getAvailableTickets(@RequestParam String eventName) {
        return ResponseEntity.ok(ticketBookingService.findAvailableTicketsByEventName(eventName));
    }

    @Operation(summary = "Book ticket")
    @PostMapping("/{ticketId}/book")
    public ResponseEntity<TicketDTO> bookTicket(
            @PathVariable Long ticketId,
            @RequestParam Long customerId) {
        return ResponseEntity.ok(ticketBookingService.assignTicketToCustomer(ticketId, customerId));
    }
} 