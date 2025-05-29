package com.ticketbooking.domain.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Data
@NoArgsConstructor
public class Place {
    private Long id;
    private String address;
    private String name;

    public Place(String address, String name) {
        this.address = address;
        this.name = name;
    }
} 