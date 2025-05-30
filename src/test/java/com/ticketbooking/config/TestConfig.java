package com.ticketbooking.config;

import com.ticketbooking.domain.repository.CustomerRepository;
import com.ticketbooking.domain.repository.EventRepository;
import com.ticketbooking.domain.repository.TicketRepository;
import com.ticketbooking.infrastructure.util.DataGenerator;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;

@TestConfiguration
@Profile("test")
public class TestConfig {
    @Bean
    public DataGenerator dataGenerator(
            CustomerRepository customerRepository,
            EventRepository eventRepository,
            TicketRepository ticketRepository) {
        DataGenerator generator = new DataGenerator(customerRepository, eventRepository, ticketRepository);
        generator.generateData();
        return generator;
    }
} 