package utils;

import com.github.javafaker.Faker;
import domain.model.*;

import java.time.LocalDate;
import java.util.*;

public class DataGenerator {
    private final Faker faker = new Faker();

    public List<Customer> generateCustomers(int count) {
        List<Customer> customers = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            customers.add(new Customer(UUID.randomUUID(),
                    faker.name().fullName(),
                    faker.internet().emailAddress(),
                    faker.phoneNumber().cellPhone()));
        }
        return customers;
    }

    public List<Place> generatePlaces(int count) {
        List<Place> places = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            places.add(new Place(UUID.randomUUID(),
                    faker.company().name(),
                    faker.address().fullAddress()));
        }
        return places;
    }

    public List<Event> generateEvents(List<Place> places, int count) {
        List<Event> events = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            Place place = places.get(i % places.size());
            Event event = new Event(UUID.randomUUID(),
                    faker.book().title(),
                    LocalDate.now().plusDays(faker.number().numberBetween(1, 30)),
                    place);

            for (int j = 1; j <= 5; j++) {
                event.addTicket(new Ticket(UUID.randomUUID(),
                        faker.number().randomDouble(2, 20, 100),
                        j));
            }

            events.add(event);
        }
        return events;
    }
}