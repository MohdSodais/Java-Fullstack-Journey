package Days;

import java.util.Scanner;

public class Day42 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int size = 8; // 8x8 square canvas (required for clean 90-degree rotation)
        char[][] canvas = new char[size][size];
        char[][] undoBuffer = new char[size][size];

        // Fill initial canvas with blank dots
        clearCanvas(canvas, '.');
        copyGrid(canvas, undoBuffer);

        boolean running = true;
        System.out.println("=== 2D ASCII PIXEL ART CANVAS EDITOR ===");
        System.out.println("Draw characters onto an " + size + "x" + size + " grid and transform your artwork!");

        while (running) {
            printCanvas(canvas);

            System.out.println("\n1. Draw Single Pixel (row, col, symbol)");
            System.out.println("2. Draw Horizontal or Vertical Line");
            System.out.println("3. Mirror Canvas Horizontally (Left <-> Right)");
            System.out.println("4. Rotate Canvas 90° Clockwise");
            System.out.println("5. Invert / Swap Two Symbols");
            System.out.println("6. Undo Last Action");
            System.out.println("7. Clear Canvas");
            System.out.println("8. Exit");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();

            if (choice == 1) {
                System.out.print("Enter row (1-" + size + "): ");
                int r = scanner.nextInt() - 1;
                System.out.print("Enter col (1-" + size + "): ");
                int c = scanner.nextInt() - 1;
                System.out.print("Enter character symbol (e.g., #, *, @, O): ");
                char brush = scanner.next().charAt(0);

                if (r >= 0 && r < size && c >= 0 && c < size) {
                    copyGrid(canvas, undoBuffer);
                    canvas[r][c] = brush;
                    System.out.println(" Plotted '" + brush + "' at (" + (r + 1) + ", " + (c + 1) + ").");
                } else {
                    System.out.println(" Coordinates out of bounds!");
                }

            } else if (choice == 2) {
                System.out.print("Line direction — (1) Horizontal Row or (2) Vertical Column: ");
                int dir = scanner.nextInt();
                System.out.print("Enter row/col number (1-" + size + "): ");
                int index = scanner.nextInt() - 1;
                System.out.print("Enter start position (1-" + size + "): ");
                int start = scanner.nextInt() - 1;
                System.out.print("Enter end position (1-" + size + "): ");
                int end = scanner.nextInt() - 1;
                System.out.print("Enter brush symbol: ");
                char brush = scanner.next().charAt(0);

                if (index >= 0 && index < size && start >= 0 && end < size && start <= end) {
                    copyGrid(canvas, undoBuffer);
                    for (int pos = start; pos <= end; pos++) {
                        if (dir == 1) {
                            canvas[index][pos] = brush; // Fill across row
                        } else if (dir == 2) {
                            canvas[pos][index] = brush; // Fill down column
                        }
                    }
                    System.out.println(" Line drawn!");
                } else {
                    System.out.println(" Invalid line coordinates (ensure start <= end and within 1-" + size + ").");
                }

            } else if (choice == 3) {
                copyGrid(canvas, undoBuffer);
                mirrorHorizontal(canvas);
                System.out.println("🪞 Canvas mirrored horizontally!");

            } else if (choice == 4) {
                copyGrid(canvas, undoBuffer);
                canvas = rotateClockwise90(canvas);
                System.out.println(" Canvas rotated 90° clockwise!");

            } else if (choice == 5) {
                System.out.print("Enter symbol to replace: ");
                char target = scanner.next().charAt(0);
                System.out.print("Enter replacement symbol: ");
                char replacement = scanner.next().charAt(0);

                copyGrid(canvas, undoBuffer);
                int replacedCount = 0;
                for (int r = 0; r < size; r++) {
                    for (int c = 0; c < size; c++) {
                        if (canvas[r][c] == target) {
                            canvas[r][c] = replacement;
                            replacedCount++;
                        }
                    }
                }
                System.out.println(" Replaced " + replacedCount + " pixel(s).");

            } else if (choice == 6) {
                char[][] temp = new char[size][size];
                copyGrid(canvas, temp);
                copyGrid(undoBuffer, canvas);
                copyGrid(temp, undoBuffer);
                System.out.println(" Undid last change!");

            } else if (choice == 7) {
                copyGrid(canvas, undoBuffer);
                clearCanvas(canvas, '.');
                System.out.println(" Canvas cleared.");

            } else if (choice == 8) {
                System.out.println("Exiting Pixel Editor. Goodbye!");
                running = false;
            } else {
                System.out.println(" Invalid option. Choose between 1 and 8.");
            }
        }

        scanner.close();
    }

    // Mirrors each row in-place by swapping left and right elements up to the midpoint
    public static void mirrorHorizontal(char[][] grid) {
        int size = grid.length;
        for (int r = 0; r < size; r++) {
            for (int c = 0; c < size / 2; c++) {
                char temp = grid[r][c];
                grid[r][c] = grid[r][size - 1 - c];
                grid[r][size - 1 - c] = temp;
            }
        }
    }

    // Rotates an NxN 2D array 90 degrees clockwise using coordinate mapping: [r][c] -> [c][N - 1 - r]
    public static char[][] rotateClockwise90(char[][] grid) {
        int size = grid.length;
        char[][] rotated = new char[size][size];
        for (int r = 0; r < size; r++) {
            for (int c = 0; c < size; c++) {
                rotated[c][size - 1 - r] = grid[r][c];
            }
        }
        return rotated;
    }

    // Deep-copies all cells from source 2D array into destination 2D array
    public static void copyGrid(char[][] src, char[][] dest) {
        for (int r = 0; r < src.length; r++) {
            for (int c = 0; c < src[r].length; c++) {
                dest[r][c] = src[r][c];
            }
        }
    }

    public static void clearCanvas(char[][] grid, char fillChar) {
        for (int r = 0; r < grid.length; r++) {
            for (int c = 0; c < grid[r].length; c++) {
                grid[r][c] = fillChar;
            }
        }
    }

    public static void printCanvas(char[][] grid) {
        System.out.println("\n    1 2 3 4 5 6 7 8");
        System.out.println("  +-----------------+");
        for (int r = 0; r < grid.length; r++) {
            System.out.print((r + 1) + " | ");
            for (int c = 0; c < grid[r].length; c++) {
                System.out.print(grid[r][c] + " ");
            }
            System.out.println("|");
        }
        System.out.println("  +-----------------+");
    }
}
