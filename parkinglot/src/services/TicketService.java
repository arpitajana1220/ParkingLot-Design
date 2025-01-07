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

    public TicketService(GateRepository gateRepository, ParkingLotRepository parkingLotRepository,
                         VehicleRepository vehicleRepository, TicketRepository ticketRepository) {
        this.gateRepository = gateRepository;
        this.parkingLotRepository = parkingLotRepository;
        this.vehicleRepository = vehicleRepository;
        this.ticketRepository = ticketRepository;
    }

    public Ticket issueTicket(IssueTicketRequest issueTicketRequest) throws GateNotFoundException, ParkinglotNotFoundException, ParkingLotFullException {
        //Set Time
        Ticket ticket = new Ticket();
        ticket.setEntryTime(new Date());

        //Get gate details
        Gate gate = gateRepository.findGateByGateId(issueTicketRequest.getGateId());
        ticket.setEntryGate(gate);

        //Get vehicle details
        ticket.setVehicle(getVehicleFromNumber(issueTicketRequest));

        //get parkinglot details
        ticket.setParkingSlot(getParkingSlot(issueTicketRequest));

        //ticket number
        ticket.setTicketNumber(issueTicketRequest.getOwnerName()+ UUID.randomUUID());

        return ticketRepository.save(ticket);

    }

    private ParkingSlot getParkingSlot(IssueTicketRequest issueTicketRequest) throws ParkinglotNotFoundException,
            ParkingLotFullException {
        ParkingLot parkinglot = parkingLotRepository.getParkingLotById(issueTicketRequest.getParkingLotId());
        ParkingPlaceAllotmentStrategy allotmentStartegy = parkinglot.getParkingPlaceAllotmentStrategy();

        ParkingPlaceAllotmentStrategy parkingAllotmentRule = ParkingSlotAllotmentStrategyFactory
                .getParkingAllotmentStrategyForType(allotmentStartegy,parkingLotRepository.getRepository());
        ParkingSlot parkingSlot = parkingAllotmentRule.getParkingSlot(issueTicketRequest.getVehicleType(),
                issueTicketRequest.getParkingLotId());
        return parkingSlot;
    }

    private Vehicle getVehicleFromNumber(IssueTicketRequest issueTicketRequest) {
        Vehicle vehicle = vehicleRepository.getVehicleByVehicleNumber(issueTicketRequest.getVehicleNumber());
        if (vehicle==null) {
            vehicle =  new Vehicle(issueTicketRequest.getVehicleNumber(),
                    issueTicketRequest.getOwnerName(), issueTicketRequest.getVehicleType());
            vehicleRepository.save(vehicle);
        }
        return vehicle;
    }
}
