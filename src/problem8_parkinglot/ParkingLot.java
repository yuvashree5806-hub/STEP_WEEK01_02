package problem8_parkinglot;

public class ParkingLot {

    private ParkingSpot[] table;
    private int capacity;
    private int occupied = 0;
    private int totalProbes = 0;

    public ParkingLot(int capacity) {
        this.capacity = capacity;
        table = new ParkingSpot[capacity];
    }

    private int hash(String plate) {
        return Math.abs(plate.hashCode()) % capacity;
    }

    public void parkVehicle(String plate) {

        int index = hash(plate);
        int probes = 0;

        while (table[index] != null) {
            index = (index + 1) % capacity; // linear probing
            probes++;
        }

        table[index] = new ParkingSpot(plate);
        occupied++;
        totalProbes += probes;

        System.out.println(
                "Assigned spot #" + index + " (" + probes + " probes)"
        );
    }

    public void exitVehicle(String plate) {

        int index = hash(plate);

        while (table[index] != null) {

            if (table[index].licensePlate.equals(plate)) {

                long duration =
                        System.currentTimeMillis() - table[index].entryTime;

                double hours = duration / (1000.0 * 60 * 60);

                double fee = hours * 5;

                table[index] = null;
                occupied--;

                System.out.println(
                        "Spot #" + index +
                                " freed, Duration: " +
                                String.format("%.2f", hours) +
                                "h Fee: $" + String.format("%.2f", fee)
                );

                return;
            }

            index = (index + 1) % capacity;
        }

        System.out.println("Vehicle not found");
    }

    public void getStatistics() {

        double occupancy =
                (occupied * 100.0) / capacity;

        double avgProbes =
                occupied == 0 ? 0 : (double) totalProbes / occupied;

        System.out.println("Occupancy: " + occupancy + "%");
        System.out.println("Avg Probes: " + avgProbes);
    }
}
