package dtos;

import models.enums.VehicleType;

public class IssueTicketRequest {
    private VehicleType vehicleType;
    private String vehicleNumber;
    private String ownerName;
    private Long gateId;
    private Long parkingLotId;

    public IssueTicketRequest(Long gateId, String ownerName, Long parkingLotId, String vehicleNumber, VehicleType vehicleType) {
        this.gateId = gateId;
        this.ownerName = ownerName;
        this.parkingLotId = parkingLotId;
        this.vehicleNumber = vehicleNumber;
        this.vehicleType = vehicleType;
    }

    public Long getGateId() {
        return gateId;
    }

    public void setGateId(Long gateId) {
        this.gateId = gateId;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }

    public Long getParkingLotId() {
        return parkingLotId;
    }

    public void setParkingLotId(Long parkingLotId) {
        this.parkingLotId = parkingLotId;
    }

    public String getVehicleNumber() {
        return vehicleNumber;
    }

    public void setVehicleNumber(String vehicleNumber) {
        this.vehicleNumber = vehicleNumber;
    }

    public VehicleType getVehicleType() {
        return vehicleType;
    }

    public void setVehicleType(VehicleType vehicleType) {
        this.vehicleType = vehicleType;
    }
}
