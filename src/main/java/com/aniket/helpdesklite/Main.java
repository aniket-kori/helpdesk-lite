package com.aniket.helpdesklite;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class Main {

    private static final Scanner scanner = new Scanner(System.in);
    private static final TicketDAO ticketDAO = new TicketDAO();

    public static void main(String[] args) {
        boolean running = true;

        System.out.println("================================");
        System.out.println("       HELPDESK LITE");
        System.out.println("   IT Support Ticket Tracker");
        System.out.println("================================");

        while (running) {
            showMenu();
            int choice = readInt("Choose an option: ");

            try {
                switch (choice) {
                    case 1:
                        createTicket();
                        break;
                    case 2:
                        listAllTickets();
                        break;
                    case 3:
                        searchTicketById();
                        break;
                    case 4:
                        searchTicketsByEmployeeName();
                        break;
                    case 5:
                        updateTicketStatus();
                        break;
                    case 6:
                        updateTicketPriority();
                        break;
                    case 0:
                        running = false;
                        System.out.println("Exiting HelpDesk Lite. Goodbye!");
                        break;
                    default:
                        System.out.println("Invalid menu option. Try again.");
                }
            } catch (SQLException e) {
                System.out.println("Database error: " + e.getMessage());
            }

            System.out.println();
        }

        scanner.close();
    }

    private static void showMenu() {
        System.out.println("----------- MENU -----------");
        System.out.println("1. Create a new ticket");
        System.out.println("2. View all tickets");
        System.out.println("3. Search ticket by ID");
        System.out.println("4. Search tickets by employee name");
        System.out.println("5. Update ticket status");
        System.out.println("6. Update ticket priority");
        System.out.println("0. Exit");
        System.out.println("----------------------------");
    }

    private static void createTicket() throws SQLException {
        System.out.println("\n--- Create Ticket ---");

        String employeeName = readRequired("Employee name: ");
        String department = readRequired("Department: ");
        String title = readRequired("Ticket title: ");

        System.out.print("Description (optional): ");
        String description = scanner.nextLine().trim();

        String priority = readAllowedValue(
                "Priority (LOW, MEDIUM, HIGH): ",
                "LOW", "MEDIUM", "HIGH"
        );

        Ticket ticket = new Ticket(
                employeeName,
                department,
                title,
                description,
                priority,
                "OPEN"
        );

        Ticket createdTicket = ticketDAO.createTicket(ticket);

        System.out.println("Ticket created successfully!");
        System.out.println("Generated ticket ID: " + createdTicket.getTicketId());
    }

    private static void listAllTickets() throws SQLException {
        System.out.println("\n--- All Tickets ---");

        List<Ticket> tickets = ticketDAO.getAllTickets();

        if (tickets.isEmpty()) {
            System.out.println("No tickets found.");
            return;
        }

        for (Ticket ticket : tickets) {
            printTicket(ticket);
        }
    }

    private static void searchTicketById() throws SQLException {
        System.out.println("\n--- Search Ticket by ID ---");

        int ticketId = readInt("Enter ticket ID: ");
        Ticket ticket = ticketDAO.findById(ticketId);

        if (ticket == null) {
            System.out.println("No ticket found with ID " + ticketId);
        } else {
            printTicket(ticket);
        }
    }

    private static void searchTicketsByEmployeeName() throws SQLException {
        System.out.println("\n--- Search by Employee Name ---");

        String employeeName = readRequired("Enter employee name or part of name: ");
        List<Ticket> tickets =
                ticketDAO.findByEmployeeName(employeeName);

        if (tickets.isEmpty()) {
            System.out.println("No tickets found for: " + employeeName);
            return;
        }

        for (Ticket ticket : tickets) {
            printTicket(ticket);
        }
    }

    private static void updateTicketStatus() throws SQLException {
        System.out.println("\n--- Update Ticket Status ---");

        int ticketId = readInt("Enter ticket ID: ");
        String newStatus = readAllowedValue(
                "New status (OPEN, IN_PROGRESS, CLOSED): ",
                "OPEN", "IN_PROGRESS", "CLOSED"
        );

        boolean updated = ticketDAO.updateStatus(ticketId, newStatus);

        if (updated) {
            System.out.println("Ticket status updated successfully!");
        } else {
            System.out.println("No ticket found with ID " + ticketId);
        }
    }

    private static void updateTicketPriority() throws SQLException {
        System.out.println("\n--- Update Ticket Priority ---");

        int ticketId = readInt("Enter ticket ID: ");
        String newPriority = readAllowedValue(
                "New priority (LOW, MEDIUM, HIGH): ",
                "LOW", "MEDIUM", "HIGH"
        );

        boolean updated = ticketDAO.updatePriority(ticketId, newPriority);

        if (updated) {
            System.out.println("Ticket priority updated successfully!");
        } else {
            System.out.println("No ticket found with ID " + ticketId);
        }
    }

    private static void printTicket(Ticket ticket) {
        System.out.println("----------------------------");
        System.out.println(ticket);
    }

    private static int readInt(String prompt) {
        while (true) {
            System.out.print(prompt);
            String input = scanner.nextLine().trim();

            try {
                int value = Integer.parseInt(input);

                if (value < 0) {
                    System.out.println("Please enter zero or a positive number.");
                } else {
                    return value;
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid number. Please enter a whole number.");
            }
        }
    }

    private static String readRequired(String prompt) {
        while (true) {
            System.out.print(prompt);
            String value = scanner.nextLine().trim();

            if (!value.isEmpty()) {
                return value;
            }

            System.out.println("This field cannot be empty.");
        }
    }

    private static String readAllowedValue(String prompt, String... allowedValues) {
        while (true) {
            System.out.print(prompt);
            String value = scanner.nextLine().trim().toUpperCase();

            for (String allowed : allowedValues) {
                if (value.equals(allowed)) {
                    return value;
                }
            }

            System.out.println("Invalid value. Allowed values: "
                    + String.join(", ", allowedValues));
        }
    }
}