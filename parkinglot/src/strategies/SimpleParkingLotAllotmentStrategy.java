package strategies;

import models.ParkingFloor;
import models.ParkingLot;
import models.ParkingSlot;
import models.enums.ParkingLotStatus;
import models.enums.ParkingStatus;
import models.enums.VehicleType;
import repositories.ParkingLotRepository;
import exceptions.ParkinglotNotFoundException;

public class SimpleParkingLotAllotmentStrategy implements ParkingPlaceAllotmentStrategy {
    private ParkingLotRepository parkingLotRepository;

    // Constructor to initialize the repository
    public SimpleParkingLotAllotmentStrategy(ParkingLotRepository parkingLotRepository) {
        this.parkingLotRepository = parkingLotRepository;
    }

    @Override
    public ParkingSlot getParkingSlot(VehicleType vehicleType, Long parkingLotId) {
        try {
            // Retrieve the parking lot by its ID
            ParkingLot parkingLot = parkingLotRepository.getParkingLotById(parkingLotId);

            // Check if the parking lot status is AVAILABLE
            if (parkingLot.getParkingLotStatus() == ParkingLotStatus.AVAILABLE) {
                // Iterate through the floors of the parking lot
                for (ParkingFloor floor : parkingLot.getParkingFloors()) {
                    // Iterate through the parking slots on the floor
                    for (ParkingSlot slot : floor.getParkingSlots()) {
                        // Check if the slot is vacant and matches the vehicle type
                        if (slot.getParkingStatus() == ParkingStatus.VACANT && slot.getVehicleType().equals(vehicleType)) {
                            return slot; // Return the first matching free slot
                        }
                    }
                }
            }
        } catch (ParkinglotNotFoundException e) {
            e.printStackTrace(); // Handle the exception
        }
        return null; // Return null if no matching slot is found
    }


}