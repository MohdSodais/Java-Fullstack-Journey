package Days;

import java.util.Scanner;

public class Day39 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int aisles = 3; // Rows (Aisle 1 to 3)
        int bins = 4;   // Columns (Bin 1 to 4)
        double maxAisleWeight = 50.0; // Max kg per aisle

        // 2D Arrays tracking parcel tracking IDs and their weights across racks
        String[][] trackingIds = new String[aisles][bins];
        double[][] weights = new double[aisles][bins];

        // Pre-populate a couple of bins
        trackingIds[0][0] = "PKG-101";
        weights[0][0] = 18.5;
        trackingIds[1][2] = "PKG-204";
        weights[1][2] = 24.0;

        boolean running = true;
        System.out.println("=== JAVA LOGISTICS WAREHOUSE RACK SORTER ===");
        System.out.println("Safety Limit: Max " + maxAisleWeight + " kg per aisle.\n");

        while (running) {
            printWarehouseGrid(trackingIds, weights, maxAisleWeight);

            System.out.println("\n1. Store Incoming Parcel (Auto-Assign Lightest Aisle)");
            System.out.println("2. Dispatch / Remove Parcel by Tracking ID");
            System.out.println("3. Move Parcel Between Bins");
            System.out.println("4. Exit");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            if (choice == 1) {
                System.out.print("Enter Parcel Tracking ID (e.g., PKG-305): ");
                String id = scanner.nextLine().toUpperCase();

                System.out.print("Enter Parcel Weight (kg): ");
                double weight = scanner.nextDouble();

                if (weight <= 0) {
                    System.out.println("❌ Weight must be greater than 0 kg.");
                    continue;
                }

                // Find the aisle with the lowest current weight that still has an empty bin
                int bestAisle = -1;
                int bestBin = -1;
                double lowestLoad = Double.MAX_VALUE;

                for (int r = 0; r < aisles; r++) {
                    double currentAisleLoad = getAisleWeight(weights, r);
                    if (currentAisleLoad + weight <= maxAisleWeight && currentAisleLoad < lowestLoad) {
                        // Check if this aisle has an open bin
                        for (int c = 0; c < bins; c++) {
                            if (trackingIds[r][c] == null) {
                                lowestLoad = currentAisleLoad;
                                bestAisle = r;
                                bestBin = c;
                                break;
                            }
                        }
                    }
                }

                if (bestAisle != -1) {
                    trackingIds[bestAisle][bestBin] = id;
                    weights[bestAisle][bestBin] = weight;
                    System.out.printf("✅ Stored [%s] (%.1f kg) in Aisle %d, Bin %d.\n",
                            id, weight, (bestAisle + 1), (bestBin + 1));
                } else {
                    System.out.println("⚠️ Cannot store parcel: all aisles are either full or would exceed the "
                            + maxAisleWeight + " kg limit!");
                }

            } else if (choice == 2) {
                System.out.print("Enter Tracking ID to dispatch: ");
                String targetId = scanner.nextLine().toUpperCase();

                int[] coords = findParcel(trackingIds, targetId);
                if (coords != null) {
                    int r = coords[0];
                    int c = coords[1];
                    System.out.printf("🚚 Dispatched [%s] (%.1f kg) from Aisle %d, Bin %d.\n",
                            targetId, weights[r][c], (r + 1), (c + 1));
                    trackingIds[r][c] = null;
                    weights[r][c] = 0.0;
                } else {
                    System.out.println("❌ Parcel [" + targetId + "] not found in warehouse.");
                }

            } else if (choice == 3) {
                System.out.print("Enter Tracking ID to relocate: ");
                String targetId = scanner.nextLine().toUpperCase();

                int[] src = findParcel(trackingIds, targetId);
                if (src == null) {
                    System.out.println("❌ Parcel [" + targetId + "] not found.");
                    continue;
                }

                System.out.print("Enter destination Aisle (1-" + aisles + ") and Bin (1-" + bins + "): ");
                int destAisle = scanner.nextInt() - 1;
                int destBin = scanner.nextInt() - 1;

                if (destAisle < 0 || destAisle >= aisles || destBin < 0 || destBin >= bins) {
                    System.out.println("❌ Invalid destination coordinates.");
                } else if (trackingIds[destAisle][destBin] != null) {
                    System.out.println("⚠️ Destination bin is already occupied by [" + trackingIds[destAisle][destBin] + "].");
                } else {
                    double pkgWeight = weights[src[0]][src[1]];
                    double newAisleLoad = getAisleWeight(weights, destAisle) + (src[0] == destAisle ? 0 : pkgWeight);

                    if (newAisleLoad > maxAisleWeight) {
                        System.out.println("⚠️ Move blocked: Aisle " + (destAisle + 1) + " would exceed " + maxAisleWeight + " kg.");
                    } else {
                        trackingIds[destAisle][destBin] = trackingIds[src[0]][src[1]];
                        weights[destAisle][destBin] = pkgWeight;
                        trackingIds[src[0]][src[1]] = null;
                        weights[src[0]][src[1]] = 0.0;
                        System.out.println("🔄 Moved [" + targetId + "] to Aisle " + (destAisle + 1) + ", Bin " + (destBin + 1) + ".");
                    }
                }

            } else if (choice == 4) {
                System.out.println("Closing Warehouse System. Shift complete!");
                running = false;
            } else {
                System.out.println("❌ Invalid option. Choose between 1 and 4.");
            }
        }

        scanner.close();
    }

    // Sums all weights across a single row (aisle)
    public static double getAisleWeight(double[][] weights, int aisleRow) {
        double sum = 0;
        for (int c = 0; c < weights[aisleRow].length; c++) {
            sum += weights[aisleRow][c];
        }
        return sum;
    }

    // Searches the 2D array for a tracking ID and returns {row, col}, or null if missing
    public static int[] findParcel(String[][] ids, String target) {
        for (int r = 0; r < ids.length; r++) {
            for (int c = 0; c < ids[r].length; c++) {
                if (ids[r][c] != null && ids[r][c].equals(target)) {
                    return new int[]{r, c};
                }
            }
        }
        return null;
    }

    // Renders the 2D rack grid along with per-aisle weight totals
    public static void printWarehouseGrid(String[][] ids, double[][] weights, double maxWeight) {
        System.out.println("\n-------------------------------------------------------------------------");
        System.out.println("          Bin 1          Bin 2          Bin 3          Bin 4     | Load");
        System.out.println("-------------------------------------------------------------------------");
        for (int r = 0; r < ids.length; r++) {
            System.out.print("Aisle " + (r + 1) + " | ");
            for (int c = 0; c < ids[r].length; c++) {
                if (ids[r][c] != null) {
                    System.out.printf("%-7s(%4.1f) | ", ids[r][c], weights[r][c]);
                } else {
                    System.out.print("   [Empty]    | ");
                }
            }
            System.out.printf("%.1f/%.0fkg\n", getAisleWeight(weights, r), maxWeight);
        }
        System.out.println("-------------------------------------------------------------------------");
    }
}
