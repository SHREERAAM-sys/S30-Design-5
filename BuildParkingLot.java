import java.util.PriorityQueue;

/**

 S30: Design Parking Lot


 Approach: Min Heap

 Working:
 creating a min heap in the for of priority queue, shuch that elements are placed in the order of floor number and the parking slot number, based on this, the ass remove, peek operations are performed, witha specific class for ParkingLot and parkingSlot


 Time Complexity:
 addParkingSpot - O(log n) // adding a value to heap
 parkVehicle - O(log n) //removing value from heap
 unparkVehicle - O(log n) // adding a spot
 getParkingSpot - O(1) //peek an element constant time

 Space Complexity: O(m * n) //if all spots are available
 m - number of floors (max)
 n - number of spots (max)


 */
public class BuildParkingLot {

    public static class ParkingLot {

        int maxFloor;
        int maxSpot;

        PriorityQueue<ParkingSpot> pq;

        public ParkingLot(int maxFloors, int maxSpots) {

            this.maxFloor = maxFloors;
            this.maxSpot = maxSpots;
            pq = new PriorityQueue <>((a, b) -> {
                if (a.getFloor() == b.getFloor()) {
                    return a.getSpot() - b.getSpot();
                }

                return a.getFloor() - b.getFloor();
            });
        }

        public void addParkingSpot(int floor, int spot) {

            //pre condition

            if (floor > this.maxFloor) {
                throw new IllegalArgumentException("Floor is greate then the max floor");
            }

            if (spot > this.maxSpot) {
                throw new IllegalArgumentException("spot is greater then the max spot available");
            }

            ParkingSpot pSpot = new ParkingSpot(floor, spot);
            this.pq.add(pSpot);

        }

        public ParkingSpot parkVehicle() {

            if (pq.isEmpty()) {
                throw new IllegalArgumentException("No spot available currently");
            }

            return this.pq.poll();
        }

        public void unparkVehicle(int floor, int spot) {

            this.addParkingSpot(floor, spot);
        }

        public ParkingSpot getParkingSpot() {
            if (pq.isEmpty()) {
                throw new IllegalArgumentException("No spot available currently");
            }

            return this.pq.peek();

        }
    }

    public static class ParkingSpot {

        private int floor;
        private int spot;

        public ParkingSpot(int floor, int spot) {
            this.floor = floor;
            this.spot = spot;
        }

        public int getFloor() {
            return this.floor;
        }

        public int getSpot() {
            return this.spot;
        }
    }

    public static void main(String[] args) {
        ParkingLot pl = new ParkingLot(10, 20);

        pl.addParkingSpot(1, 1);

        pl.addParkingSpot(2, 1);

        pl.addParkingSpot(3, 1);

        pl.addParkingSpot(1, 2);

        pl.addParkingSpot(2, 2);

        pl.addParkingSpot(3, 2);

        ParkingSpot n = pl.getParkingSpot();

        System.out.println("Parked at Floor: " + n.getFloor() + ", Slot: " + n.getSpot());

        pl.parkVehicle();

        ParkingSpot n2 = pl.getParkingSpot();

        System.out.println("Parked at Floor: " + n2.getFloor() + ", Slot: " + n2.getSpot());

        pl.unparkVehicle(1, 1);

        ParkingSpot n1 = pl.getParkingSpot();

        System.out.println("Parked at Floor: " + n1.getFloor() + ", Slot: " + n1.getSpot());
    }
}