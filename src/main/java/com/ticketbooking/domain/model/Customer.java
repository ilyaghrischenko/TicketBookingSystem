package com.ticketbooking.domain.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.AccessLevel;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
public class Customer {
    private Long id;
    private String name;
    private String email;
    private String phone;
    @Setter(AccessLevel.NONE)
    private List<Ticket> tickets = new ArrayList<>();

    public Customer(String name, String email, String phone) {
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.tickets = new ArrayList<>();
    }

    public void addTicket(Ticket ticket) {
        tickets.add(ticket);
        ticket.setCustomer(this);
    }
} 