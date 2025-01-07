package repositories;

import exceptions.GateNotFoundException;
import models.Gate;

import java.util.HashMap;
import java.util.Map;

public class GateRepository {

    // Map to store gates with their IDs as keys
    Map<Long, Gate> gates = new HashMap<>();
    private Long previousId = 0L;

    public Gate save(Gate gate) {
        previousId++;
        gate.setId(previousId);
        gates.put(previousId, gate);
        return gate;
    }
    // Method to find a gate by its ID
    // Throws GateNotFoundException if the gate is not found
    public Gate findGateByGateId(Long gateId) throws GateNotFoundException {
        if (gates.containsKey(gateId)) {
            return gates.get(gateId);
        }
        throw new GateNotFoundException();
    }
}
