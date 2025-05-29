package domain.model;

import java.util.*;

public class Customer {
    private final UUID id;
    private String name;
    private String email;
    private String phone;
    private List<Ticket> tickets = new ArrayList<>();

    public Customer(UUID id, String name, String email, String phone) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.phone = phone;
    }

    public UUID getId() { return id; }
    public List<Ticket> getTickets() { return tickets; }

    public void addTicket(Ticket ticket) {
        tickets.add(ticket);
    }

    // геттеры/сеттеры
}