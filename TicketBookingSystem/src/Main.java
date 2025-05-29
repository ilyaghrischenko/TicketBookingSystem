package main;

import application.services.*;
import domain.model.*;
import infrastructure.repositories.*;
import utils.DataGenerator;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        DataGenerator generator = new DataGenerator();

        List<Customer> customers = generator.generateCustomers(3);
        List<Place> places = generator.generatePlaces(2);
        List<Event> events = generator.generateEvents(places, 3);

        EventService eventService = new EventService();
        TicketService ticketService = new TicketService();

        System.out.println("=== Ближайші події ===");
        eventService.findUpcomingEvents(events).forEach(e -> {
            System.out.println(e.getName() + " | " + e.getEventDate());
        });

        Event targetEvent = events.get(0);
        List<Ticket> freeTickets = ticketService.findFreeTicketsByEvent(targetEvent);

        System.out.println("\nВільні квитки на подію: " + targetEvent.getName());
        freeTickets.forEach(t -> System.out.println("Місце #" + t.getNumber() + ", Ціна: " + t.getCost()));

        ticketService.assignTicketToCustomer(freeTickets.get(0), customers.get(0));
        System.out.println("\nПокупець отримав квиток: " + customers.get(0).getTickets().get(0).getNumber());
    }
}