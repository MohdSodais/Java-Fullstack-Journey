package Days;

import java.util.Scanner;

public class Day41 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int numFloors = 6; // Floors 1 to 6 (stored at indices 0 to 5)
        int elevatorFloor = 0; // Starts at Floor 1 (index 0)
        int passengersInside = 0;
        int maxCapacity = 4;

        // Parallel arrays tracking waiting passengers and destination drop-offs per floor
        int[] waitingUp = new int[numFloors];
        int[] waitingDown = new int[numFloors];
        int[] dropOffs = new int[numFloors];

        boolean running = true;
        System.out.println("=== 2D ELEVATOR DISPATCH SIMULATOR ===");
        System.out.println("Building has " + numFloors + " floors (1-" + numFloors + "). Max capacity: " + maxCapacity + " passengers.");

        while (running) {
            renderBuilding(numFloors, elevatorFloor, passengersInside, waitingUp, waitingDown, dropOffs);

            System.out.println("\n1. Call Elevator from a Floor");
            System.out.println("2. Step Elevator (Move 1 Floor Toward Next Request)");
            System.out.println("3. Auto-Run Until All Requests Complete");
            System.out.println("4. Exit");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();

            if (choice == 1) {
                System.out.print("Enter pickup floor (1-" + numFloors + "): ");
                int pickup = scanner.nextInt() - 1;
                System.out.print("Enter destination floor (1-" + numFloors + "): ");
                int dest = scanner.nextInt() - 1;

                if (pickup < 0 || pickup >= numFloors || dest < 0 || dest >= numFloors || pickup == dest) {
                    System.out.println(" Invalid floor combination.");
                    continue;
                }

                if (dest > pickup) {
                    waitingUp[pickup]++;
                } else {
                    waitingDown[pickup]++;
                }
                // Queue destination drop-off once picked up (simplified for single-passenger call)
                System.out.println(" Call logged at Floor " + (pickup + 1) + " going to Floor " + (dest + 1) + ".");

                // If elevator is already at pickup floor, board immediately
                if (elevatorFloor == pickup && passengersInside < maxCapacity) {
                    if (dest > pickup) waitingUp[pickup]--;
                    else waitingDown[pickup]--;
                    passengersInside++;
                    dropOffs[dest]++;
                    System.out.println(" Doors opened immediately! Passenger boarded for Floor " + (dest + 1) + ".");
                } else {
                    // Store pending destination in a simple parallel queue by recording target floor
                    // For multi-passenger simplicity, assign destination directly upon pickup
                    pendingDestinations[pickup] = dest;
                }

            } else if (choice == 2) {
                elevatorFloor = stepElevator(elevatorFloor, passengersInside, maxCapacity, waitingUp, waitingDown, dropOffs, scanner);
                passengersInside = recountPassengers(dropOffs);

            } else if (choice == 3) {
                int steps = 0;
                while (hasActiveRequests(waitingUp, waitingDown, dropOffs) && steps < 25) {
                    elevatorFloor = stepElevator(elevatorFloor, passengersInside, maxCapacity, waitingUp, waitingDown, dropOffs, scanner);
                    passengersInside = recountPassengers(dropOffs);
                    renderBuilding(numFloors, elevatorFloor, passengersInside, waitingUp, waitingDown, dropOffs);
                    steps++;
                }
                if (!hasActiveRequests(waitingUp, waitingDown, dropOffs)) {
                    System.out.println(" All elevator calls and drop-offs completed!");
                }

            } else if (choice == 4) {
                System.out.println("Shutting down Elevator Simulator.");
                running = false;
            } else {
                System.out.println(" Invalid option.");
            }
        }

        scanner.close();
    }

    // Stores the desired destination floor for the next waiting passenger at each floor
    static int[] pendingDestinations = {-1, -1, -1, -1, -1, -1};

    // Moves the elevator one floor toward the nearest active call or drop-off and processes stops
    public static int stepElevator(int currentFloor, int passengers, int maxCap,
                                   int[] up, int[] down, int[] dropOffs, Scanner scanner) {
        int targetFloor = findNearestTarget(currentFloor, up, down, dropOffs);

        if (targetFloor == -1) {
            System.out.println("💤 No active calls. Elevator is idling on Floor " + (currentFloor + 1) + ".");
            return currentFloor;
        }

        // Move 1 floor closer to targetFloor
        if (targetFloor > currentFloor) {
            currentFloor++;
            System.out.println(" Elevator moved UP to Floor " + (currentFloor + 1) + ".");
        } else if (targetFloor < currentFloor) {
            currentFloor--;
            System.out.println(" Elevator moved DOWN to Floor " + (currentFloor + 1) + ".");
        }

        // Process drop-offs at current floor
        if (dropOffs[currentFloor] > 0) {
            System.out.println(" " + dropOffs[currentFloor] + " passenger(s) exited at Floor " + (currentFloor + 1) + ".");
            dropOffs[currentFloor] = 0;
        }

        // Process pickups at current floor
        int currentLoad = recountPassengers(dropOffs);
        while ((up[currentFloor] > 0 || down[currentFloor] > 0) && currentLoad < maxCap) {
            if (up[currentFloor] > 0) up[currentFloor]--;
            else down[currentFloor]--;

            int dest = pendingDestinations[currentFloor];
            if (dest == -1) dest = (currentFloor == 0) ? up.length - 1 : 0;
            dropOffs[dest]++;
            currentLoad++;
            System.out.println(" Passenger boarded at Floor " + (currentFloor + 1) + " heading to Floor " + (dest + 1) + ".");
        }

        return currentFloor;
    }

    // Finds the closest floor index with either a waiting call or a scheduled drop-off
    public static int findNearestTarget(int currentFloor, int[] up, int[] down, int[] dropOffs) {
        int bestFloor = -1;
        int minDistance = Integer.MAX_VALUE;

        for (int f = 0; f < dropOffs.length; f++) {
            boolean needsStop = (up[f] > 0 || down[f] > 0 || dropOffs[f] > 0);
            if (needsStop) {
                int dist = Math.abs(f - currentFloor);
                if (dist < minDistance) {
                    minDistance = dist;
                    bestFloor = f;
                }
            }
        }
        return bestFloor;
    }

    public static int recountPassengers(int[] dropOffs) {
        int total = 0;
        for (int count : dropOffs) total += count;
        return total;
    }

    public static boolean hasActiveRequests(int[] up, int[] down, int[] dropOffs) {
        for (int i = 0; i < dropOffs.length; i++) {
            if (up[i] > 0 || down[i] > 0 || dropOffs[i] > 0) return true;
        }
        return false;
    }

    // Builds and prints a 2D character cross-section of the building from top floor to ground floor
    public static void renderBuilding(int floors, int carFloor, int load, int[] up, int[] down, int[] dropOffs) {
        char[][] shaft = new char[floors][5];
        for (int r = 0; r < floors; r++) {
            for (int c = 0; c < 5; c++) {
                shaft[r][c] = ' ';
            }
        }

        // Place elevator cab in shaft
        shaft[carFloor][1] = '[';
        shaft[carFloor][2] = (char) ('0' + load);
        shaft[carFloor][3] = ']';

        System.out.println("\n--- BUILDING CROSS-SECTION ---");
        // Print top-down (highest index first)
        for (int f = floors - 1; f >= 0; f--) {
            System.out.print("Floor " + (f + 1) + " |");
            for (int c = 0; c < 5; c++) {
                System.out.print(shaft[f][c]);
            }
            System.out.printf("| Waiting: ↑%d ↓%d | Drop-offs queued: %d\n", up[f], down[f], dropOffs[f]);
        }
    }
}