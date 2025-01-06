package repositories;

import exceptions.ParkinglotNotFoundException;
import models.ParkingLot;

import java.util.HashMap;
import java.util.Map;

public class ParkingLotRepository {

    Map<Long, ParkingLot> parkingLotMap = new HashMap<>();
    public ParkingLot getParkingLotById(Long parkingLotId) throws ParkinglotNotFoundException {
        if(parkingLotMap.containsKey(parkingLotId)) {
            return parkingLotMap.get(parkingLotId);
        }
        throw new ParkinglotNotFoundException();
    }
}
