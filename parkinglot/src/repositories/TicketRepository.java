package repositories;

import models.Ticket;

import java.util.HashMap;
import java.util.Map;

public class TicketRepository {

    Map<Long, Ticket> tickets = new HashMap<>();
    Long previousTicketId = 0L;

    public Ticket save(Ticket ticket) {
        previousTicketId++;
        ticket.setId(previousTicketId);
        tickets.putIfAbsent(previousTicketId, ticket);
        return ticket;
    }
}
