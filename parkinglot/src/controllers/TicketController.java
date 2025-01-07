package controllers;

import dtos.IssueTicketRequest;
import dtos.IssueTicketResponse;
import dtos.ResponseStatus;
import exceptions.GateNotFoundException;
import exceptions.ParkingLotFullException;
import exceptions.ParkinglotNotFoundException;
import models.Ticket;
import services.TicketService;

public class TicketController {
    public static String TICKET_ISSUED_MESSAGE= "Ticket issued successfully";
    public static String INVALID_GATE= "Gate is not valid";
    public static String INVALID_PARKINGLOT= "ParkingLot is not valid";
    public static String PARKINGLOT_FUll= "ParkingLot is Full";
    private TicketService ticketService;

    public TicketController(TicketService ticketService) {
        this.ticketService = ticketService;
    }

    public IssueTicketResponse issueTicket(IssueTicketRequest ticketRequest) {
        Ticket ticket = null;
        try {
            ticket = ticketService.issueTicket(ticketRequest);
        } catch (GateNotFoundException e) {
            return new IssueTicketResponse(null, ResponseStatus.FAILURE, INVALID_GATE);
        } catch (ParkinglotNotFoundException e) {
            return new IssueTicketResponse(null, ResponseStatus.FAILURE, INVALID_PARKINGLOT);
        } catch (ParkingLotFullException e) {
            return new IssueTicketResponse(null, ResponseStatus.FAILURE, PARKINGLOT_FUll);
        }

        return new IssueTicketResponse(ticket, ResponseStatus.SUCCESS, TICKET_ISSUED_MESSAGE);
    }
}
