package domain.model;

import domain.enums.TicketStatus;

import java.util.UUID;

public class Ticket {
    private final UUID id;
    private final double cost;
    private final int number;
    private TicketStatus status;
    private Customer owner;

    public Ticket(UUID id, double cost, int number) {
        this.id = id;
        this.cost = cost;
        this.number = number;
        this.status = TicketStatus.FREE;
    }

    public void assignTo(Customer customer) {
        this.owner = customer;
        this.status = TicketStatus.SOLD;
    }

    public int getNumber() { return number; }

    public double getCost() { return cost; }

    public TicketStatus getStatus() { return status; }
}