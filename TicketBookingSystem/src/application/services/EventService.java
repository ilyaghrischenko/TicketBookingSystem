package application.services;

import domain.model.Event;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

public class EventService {
    public List<Event> findUpcomingEvents(List<Event> allEvents) {
        return allEvents.stream()
                .filter(e -> e.getEventDate().isAfter(LocalDate.now()))
                .collect(Collectors.toList());
    }

    public List<Event> findByName(List<Event> allEvents, String name) {
        return allEvents.stream()
                .filter(e -> e.getName().equalsIgnoreCase(name))
                .collect(Collectors.toList());
    }
}