package com.ticketbooking.application.dto;

import com.ticketbooking.domain.model.TicketStatus;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TicketDTO {
    private Long id;
    private double cost;
    private String number;
    private Long customerId;
    private Long eventId;
    private TicketStatus status;
} 