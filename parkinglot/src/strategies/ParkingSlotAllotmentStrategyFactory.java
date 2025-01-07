package strategies;

import repositories.ParkingLotRepository;

public class ParkingSlotAllotmentStrategyFactory {

    public static ParkingPlaceAllotmentStrategy getParkingAllotmentStrategyForType(ParkingPlaceAllotmentStrategy parkingPlaceAllotmentStrategy, ParkingLotRepository parkingLotRepository) {

       return new SimpleParkingLotAllotmentStrategy(parkingLotRepository);
    }
}
