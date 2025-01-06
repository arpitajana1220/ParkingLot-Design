package services;

import dtos.IssueTicketRequest;
import exceptions.GateNotFoundException;
import exceptions.ParkingLotFullException;
import exceptions.ParkinglotNotFoundException;
import models.*;
import repositories.GateRepository;
import repositories.ParkingLotRepository;
import repositories.TicketRepository;
import repositories.VehicleRepository;
import strategies.ParkingPlaceAllotmentStrategy;
import strategies.ParkingSlotAllotmentStrategyFactory;

import java.util.Date;
import java.util.UUID;

public class TicketService {
    private GateRepository gateRepository;
    private VehicleRepository vehicleRepository;
    private repositories.ParkingLotRepository parkingLotRepository;
    private TicketRepository ticketRepository;

    public Ticket issueTicket(IssueTicketRequest issueTicketRequest) throws GateNotFoundException, ParkinglotNotFoundException, ParkingLotFullException {
//        Set Time
        Ticket ticket = new Ticket();
        ticket.setEntryTime(new Date());

//        Get gate details
        Gate gate = gateRepository.findGateByGateId(issueTicketRequest.getGateId());
        ticket.setEntryGate(gate);

        Vehicle vehicle = vehicleRepository.getVehicleByVehicleNumber(issueTicketRequest.getVehicleNumber());
        if (vehicle==null) {
            vehicle =  new Vehicle(issueTicketRequest.getVehicleNumber(),
                    issueTicketRequest.getOwnerName(), issueTicketRequest.getVehicleType());
            vehicleRepository.save(vehicle);
        }
        ticket.setVehicle(vehicle);

//        get parkinglot

        ParkingLot parkinglot = parkingLotRepository.getParkingLotById(issueTicketRequest.getParkingLotId());
        ParkingPlaceAllotmentStrategy allotmentStartegy = parkinglot.getParkingPlaceAllotmentStrategy();


        ParkingPlaceAllotmentStrategy parkingAllotmentRule = ParkingSlotAllotmentStrategyFactory.getParkingAllotmentStrategyForType(allotmentStartegy);
        ParkingSlot parkingSlot = parkingAllotmentRule.getParkingSlot(issueTicketRequest.getVehicleType(), issueTicketRequest.getParkingLotId());

        ticket.setParkingSlot(parkingSlot);

        //ticket number
        ticket.setTicketNumber(issueTicketRequest.getOwnerName()+ UUID.randomUUID());

        return ticketRepository.save(ticket);

    }
}
