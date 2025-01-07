package App;

import controllers.TicketController;
import dtos.IssueTicketRequest;
import exceptions.GateNotFoundException;
import exceptions.ParkingLotFullException;
import exceptions.ParkinglotNotFoundException;
import models.*;
import models.enums.GateType;
import models.enums.ParkingLotStatus;
import models.enums.ParkingStatus;
import models.enums.VehicleType;
import repositories.*;
import services.TicketService;

import java.util.Arrays;
import java.util.List;

public class AppRunner {
    public static void main(String[] args) throws ParkingLotFullException, ParkinglotNotFoundException, GateNotFoundException {
        // Initialize repositories
        ParkingLotRepository parkingLotRepository = new ParkingLotRepository();
        GateRepository gateRepository = new GateRepository();
        VehicleRepository vehicleRepository = new VehicleRepository();
        TicketRepository ticketRepository = new TicketRepository();

        // Create Parking Slots
        ParkingSlot slot1 = new ParkingSlot();
        slot1.setParkingStatus(ParkingStatus.VACANT);
        slot1.setVehicleType(VehicleType.CAR);

        ParkingSlot slot2 = new ParkingSlot();
        slot2.setParkingStatus(ParkingStatus.VACANT);
        slot2.setVehicleType(VehicleType.BIKE);

        // Create Parking Floor
        ParkingFloor floor1 = new ParkingFloor();
        floor1.setFloorNumber(1);
        floor1.setParkingSlots(Arrays.asList(slot1, slot2));

        // Create Gate
        Gate gate1 = new Gate();
        gate1.setId(1L);
        gate1.setGateType(GateType.ENTRY);

        // Create Parking Lot
        ParkingLot parkingLot = new ParkingLot();
        parkingLot.setParkingFloors(List.of(floor1));
        parkingLot.setParkingLotStatus(ParkingLotStatus.AVAILABLE);

        // Save Parking Lot and Gate to repositories
        parkingLotRepository.save(parkingLot);
        gateRepository.save(gate1);

        // Initialize services and controllers
        TicketService ticketService = new TicketService(gateRepository, parkingLotRepository, vehicleRepository, ticketRepository);
        TicketController ticketController = new TicketController(ticketService);

        // Simulate a request
        IssueTicketRequest request = new IssueTicketRequest(1L, "John Doe", parkingLot.getId(),"ABC123",VehicleType.CAR);


        Ticket ticket = ticketService.issueTicket(request);
        System.out.println("Ticket issued: " + ticket.getTicketNumber());
    }
}