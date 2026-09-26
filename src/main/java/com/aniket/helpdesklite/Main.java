package com.aniket.helpdesklite;

import java.sql.SQLException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        TicketDAO ticketDAO = new TicketDAO();

        try (Scanner scanner = new Scanner(System.in)) {
            System.out.print("Enter ticket ID to update: ");
            int ticketId = Integer.parseInt(scanner.nextLine());

            System.out.print("Enter new status (OPEN, IN_PROGRESS, CLOSED): ");
            String newStatus = scanner.nextLine().trim().toUpperCase();

            if (!newStatus.equals("OPEN")
                    && !newStatus.equals("IN_PROGRESS")
                    && !newStatus.equals("CLOSED")) {
                System.out.println("Invalid status. No changes made.");
                return;
            }

            boolean updated = ticketDAO.updateStatus(ticketId, newStatus);

            if (updated) {
                System.out.println("Ticket status updated successfully!");
            } else {
                System.out.println("No ticket found with ID " + ticketId);
            }

        } catch (NumberFormatException e) {
            System.out.println("Please enter a valid whole-number ticket ID.");
        } catch (SQLException e) {
            System.out.println("Database error: " + e.getMessage());
        }
    }
}