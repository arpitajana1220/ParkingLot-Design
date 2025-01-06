package strategies;

import exceptions.ParkingLotFullException;
import models.ParkingSlot;
import models.enums.VehicleType;

public interface ParkingPlaceAllotmentStrategy {
    ParkingSlot getParkingSlot(VehicleType vehicleType, Long parkingLotId)
            throws ParkingLotFullException;
}
