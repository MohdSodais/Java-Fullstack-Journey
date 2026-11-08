package Days;

import java.util.Scanner;

public class Day25 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int maxContacts = 5;

        // Parallel arrays for names and phone numbers
        String[] names = new String[maxContacts];
        String[] phones = new String[maxContacts];
        int contactCount = 0;

        boolean running = true;
        System.out.println("=== PHONE CONTACT DIRECTORY ===");

        while (running) {
            System.out.println("\n----------------------------------");
            System.out.println("📇 Directory (" + contactCount + "/" + maxContacts + "):");

            if (contactCount == 0) {
                System.out.println("Directory is currently empty.");
            } else {
                for (int i = 0; i < maxContacts; i++) {
                    if (names[i] != null) {
                        System.out.println((i + 1) + ". " + names[i] + " — " + phones[i]);
                    }
                }
            }

            System.out.println("\n1. Add Contact");
            System.out.println("2. Search Contact by Name");
            System.out.println("3. Delete Contact");
            System.out.println("4. Exit");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume leftover newline character

            if (choice == 1) {
                if (contactCount >= maxContacts) {
                    System.out.println("❌ Directory is full! Delete a contact to add a new one.");
                } else {
                    System.out.print("Enter name: ");
                    String name = scanner.nextLine();
                    System.out.print("Enter phone number: ");
                    String phone = scanner.nextLine();

                    // Find the first empty (null) slot in the arrays
                    for (int i = 0; i < maxContacts; i++) {
                        if (names[i] == null) {
                            names[i] = name;
                            phones[i] = phone;
                            contactCount++;
                            System.out.println("✅ Contact added successfully!");
                            break;
                        }
                    }
                }

            } else if (choice == 2) {
                System.out.print("Enter name to search: ");
                String searchName = scanner.nextLine();
                boolean found = false;

                // Linear search through names array
                for (int i = 0; i < maxContacts; i++) {
                    if (names[i] != null && names[i].equalsIgnoreCase(searchName)) {
                        System.out.println("🔍 Found! " + names[i] + "'s phone number is: " + phones[i]);
                        found = true;
                        break;
                    }
                }

                if (!found) {
                    System.out.println("❌ No contact found with the name '" + searchName + "'.");
                }

            } else if (choice == 3) {
                System.out.print("Enter contact number to delete (1-" + maxContacts + "): ");
                int index = scanner.nextInt() - 1;

                if (index >= 0 && index < maxContacts && names[index] != null) {
                    System.out.println("🗑️ Deleted contact: " + names[index]);
                    names[index] = null;  // Clear name
                    phones[index] = null; // Clear phone
                    contactCount--;
                } else {
                    System.out.println("❌ Invalid selection or empty slot.");
                }

            } else if (choice == 4) {
                System.out.println("Exiting Contact Directory. Goodbye!");
                running = false;
            } else {
                System.out.println("❌ Invalid option. Please choose between 1 and 4.");
            }
        }

        scanner.close();
    }
}
