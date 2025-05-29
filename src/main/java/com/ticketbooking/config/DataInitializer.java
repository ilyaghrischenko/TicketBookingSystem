package com.ticketbooking.config;

import com.ticketbooking.domain.repository.CustomerRepository;
import com.ticketbooking.domain.repository.EventRepository;
import com.ticketbooking.domain.repository.TicketRepository;
import com.ticketbooking.infrastructure.util.DataGenerator;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class DataInitializer {
    private final CustomerRepository customerRepository;
    private final EventRepository eventRepository;
    private final TicketRepository ticketRepository;

    @Bean
    public CommandLineRunner initData() {
        return args -> {
            DataGenerator generator = new DataGenerator(customerRepository, eventRepository, ticketRepository);
            generator.generateData();
        };
    }
} 