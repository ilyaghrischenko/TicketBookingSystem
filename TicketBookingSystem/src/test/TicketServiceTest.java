package test;

import application.services.TicketService;
import domain.enums.TicketStatus;
import domain.model.*;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

public class TicketServiceTest {
    @Test
    public void testAssignTicket() {
        TicketService service = new TicketService();
        Customer customer = new Customer(UUID.randomUUID(), "Test", "test@email.com", "123");
        Ticket ticket = new Ticket(UUID.randomUUID(), 50.0, 1);

        service.assignTicketToCustomer(ticket, customer);

        assertEquals(TicketStatus.SOLD, ticket.getStatus());
        assertEquals(1, customer.getTickets().size());
    }
}