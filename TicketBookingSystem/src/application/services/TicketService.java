package application.services;

import domain.enums.TicketStatus;
import domain.model.*;

import java.util.List;
import java.util.stream.Collectors;

public class TicketService {
    public List<Ticket> findFreeTicketsByEvent(Event event) {
        return event.getTickets().stream()
                .filter(t -> t.getStatus() == TicketStatus.FREE)
                .collect(Collectors.toList());
    }

    public void assignTicketToCustomer(Ticket ticket, Customer customer) {
        if (ticket.getStatus() == TicketStatus.FREE) {
            ticket.assignTo(customer);
            customer.addTicket(ticket);
        }
    }
}