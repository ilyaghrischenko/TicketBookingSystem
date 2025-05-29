package domain.model;

import java.time.LocalDate;
import java.util.*;

public class Event {
    private final UUID id;
    private String name;
    private LocalDate eventDate;
    private List<Ticket> tickets = new ArrayList<>();
    private Place place;

    public Event(UUID id, String name, LocalDate eventDate, Place place) {
        this.id = id;
        this.name = name;
        this.eventDate = eventDate;
        this.place = place;
    }

    public void addTicket(Ticket ticket) {
        tickets.add(ticket);
    }

    public String getName() { return name; }

    public LocalDate getEventDate() { return eventDate; }

    public List<Ticket> getTickets() { return tickets; }
}