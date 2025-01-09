# Parking Lot Management System

## Overview
The Parking Lot Management System is a Java-based application designed to manage parking lots, including the creation and management of parking slots, floors, gates, and parking lots. It handles the issuance of parking tickets and the allocation of parking slots based on vehicle type.

## UML Diagram
![uml_diagram.png](https://github.com/arpitajana1220/ParkingLot-Design/blob/main/parkinglot/src/ClassDiagram.png)

## Project Structure
- `models`: Contains the data models such as `ParkingSlot`, `ParkingFloor`, `Gate`, `ParkingLot`, `Ticket`, and `Payment`.
- `repositories`: Provides an abstraction over data storage with CRUD operations for models.
- `services`: Contains the business logic of the application, including ticket issuance and parking slot management.
- `strategies`: Contains the parking slot allotment strategies.
- `app`: The entry point of the application.

## Key Components
### Models
- **ParkingSlot**: Represents a parking slot with attributes like number, vehicle type, parking status, and associated parking floor.
- **ParkingFloor**: Represents a floor in the parking lot containing multiple parking slots.
- **Gate**: Represents an entry or exit gate in the parking lot.
- **ParkingLot**: Represents the entire parking lot with multiple floors and gates.
- **Ticket**: Represents a parking ticket issued to a vehicle.
- **Payment**: Represents a payment made for parking.

### Repositories
- **ParkingLotRepository**: Manages parking lot data.
- **GateRepository**: Manages gate data.
- **VehicleRepository**: Manages vehicle data.
- **TicketRepository**: Manages ticket data.

### Services
- **TicketService**: Handles the issuance of parking tickets and interacts with repositories.
- **ParkingSlotService**: Manages the creation and allocation of parking slots, floors, gates, and parking lots.

### Strategies
- **SimpleParkingLotAllotmentStrategy**: Implements a simple strategy for allocating parking slots based on vehicle type and availability.

## Setup and Usage
1. **Clone the repository**:
    ```sh
    git clone <repository-url>
    cd parkinglot
    ```

2. **Build the project**:
    ```sh
    ./gradlew build
    ```

3. **Run the application**:
    ```sh
    ./gradlew run
    ```

4. **Simulate a request**:
    Modify the `ParkingLotApplication` class to simulate requests and test the application.

## Example
Here is an example of how to create and manage parking slots, floors, gates, and parking lots in the application:

```java
package app;

import models.*;
import repositories.*;
import services.ParkingSlotService;

public class ParkingLotApplication {
    public static void main(String[] args) {
        // Initialize repositories
        ParkingLotRepository parkingLotRepository = new ParkingLotRepository();
        GateRepository gateRepository = new GateRepository();

        // Initialize service
        ParkingSlotService parkingSlotService = new ParkingSlotService(parkingLotRepository, gateRepository);

        // Create Parking Slots
        ParkingSlot slot1 = parkingSlotService.createParkingSlot(ParkingStatus.VACANT, VehicleType.CAR);
        ParkingSlot slot2 = parkingSlotService.createParkingSlot(ParkingStatus.VACANT, VehicleType.BIKE);

        // Create Parking Floor
        ParkingFloor floor1 = parkingSlotService.createParkingFloor(1, slot1, slot2);

        // Create Gate
        Gate gate1 = parkingSlotService.createGate(1L, GateType.ENTRY);

        // Create Parking Lot
        ParkingLot parkingLot = parkingSlotService.createParkingLot(ParkingLotStatus.AVAILABLE, floor1);

        // Print created objects for verification
        System.out.println("Parking Lot created with ID: " + parkingLot.getId());
        System.out.println("Gate created with ID: " + gate1.getGateId());
    }
}
```

## License
This project is licensed under the MIT License. See the `LICENSE` file for details.
