package repositories;

import models.Vehicle;

import java.util.HashMap;
import java.util.Map;

public class VehicleRepository {
    Map<String,Vehicle> vehicleMap = new HashMap<>();
    Long previousId = 0L;

    public Vehicle getVehicleByVehicleNumber(String vehicleNumber) {
        if (vehicleMap.containsKey(vehicleNumber)) {
            return vehicleMap.get(vehicleNumber);
        }
        return null;
    }

    public Vehicle save(Vehicle vehicle) {
        previousId++;
        vehicle.setId(previousId);
        vehicleMap.putIfAbsent(vehicle.getVehicleNumber(), vehicle);
        return vehicleMap.get(vehicle.getVehicleNumber());
    }
}
