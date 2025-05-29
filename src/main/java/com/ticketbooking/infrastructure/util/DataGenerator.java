package com.ticketbooking.infrastructure.util;

import com.github.javafaker.Faker;
import com.ticketbooking.domain.model.*;
import com.ticketbooking.domain.repository.*;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class DataGenerator {
    private final Faker faker = new Faker();
    private final CustomerRepository customerRepository;
    private final EventRepository eventRepository;
    private final TicketRepository ticketRepository;

    public DataGenerator(
            CustomerRepository customerRepository,
            EventRepository eventRepository,
            TicketRepository ticketRepository) {
        this.customerRepository = customerRepository;
        this.eventRepository = eventRepository;
        this.ticketRepository = ticketRepository;
    }

    public void generateData() {
        List<Place> places = generatePlaces(5);
        List<Event> events = generateEvents(10, places);
        List<Customer> customers = generateCustomers(20);
        generateTickets(events);
    }

    private List<Place> generatePlaces(int count) {
        List<Place> places = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            Place place = new Place(
                faker.address().fullAddress(),
                faker.company().name() + " " + faker.company().suffix()
            );
            places.add(place);
        }
        return places;
    }

    private List<Event> generateEvents(int count, List<Place> places) {
        List<Event> events = new ArrayList<>();
        String[] eventTypes = {"Concert", "Conference", "Festival", "Theater Show", "Sports Match"};
        for (int i = 0; i < count; i++) {
            LocalDateTime eventDate = LocalDateTime.ofInstant(
                faker.date().future(30, TimeUnit.DAYS).toInstant(),
                ZoneId.systemDefault()
            );

            Event event = new Event(
                faker.music().genre() + " " + eventTypes[faker.random().nextInt(eventTypes.length)],
                eventDate,
                places.get(faker.random().nextInt(places.size()))
            );
            events.add(eventRepository.save(event));
        }
        return events;
    }

    private List<Customer> generateCustomers(int count) {
        List<Customer> customers = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            Customer customer = new Customer(
                faker.name().fullName(),
                faker.internet().emailAddress(),
                faker.phoneNumber().cellPhone()
            );
            customers.add(customerRepository.save(customer));
        }
        return customers;
    }

    private void generateTickets(List<Event> events) {
        for (Event event : events) {
            int ticketCount = faker.random().nextInt(50, 200);
            for (int i = 0; i < ticketCount; i++) {
                Ticket ticket = new Ticket(
                    faker.number().randomDouble(2, 10, 1000),
                    String.format("ROW-%d-SEAT-%d", (i / 10) + 1, (i % 10) + 1),
                    event
                );
                ticketRepository.save(ticket);
                event.addTicket(ticket);
            }
            eventRepository.save(event);
        }
    }
} 