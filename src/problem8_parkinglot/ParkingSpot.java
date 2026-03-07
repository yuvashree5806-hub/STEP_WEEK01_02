package problem8_parkinglot;

public class ParkingSpot {

    String licensePlate;
    long entryTime;

    public ParkingSpot(String licensePlate) {
        this.licensePlate = licensePlate;
        this.entryTime = System.currentTimeMillis();
    }
}