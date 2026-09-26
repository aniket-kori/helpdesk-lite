package com.aniket.helpdesklite;

import java.sql.SQLException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        TicketDAO ticketDAO = new TicketDAO();

        try (Scanner scanner = new Scanner(System.in)) {
            System.out.print("Enter ticket ID to search: ");
            int ticketId = Integer.parseInt(scanner.nextLine());

            Ticket ticket = ticketDAO.findById(ticketId);

            if (ticket == null) {
                System.out.println("No ticket found with ID " + ticketId);
            } else {
                System.out.println("Ticket found:");
                System.out.println("--------------------");
                System.out.println(ticket);
            }

        } catch (NumberFormatException e) {
            System.out.println("Please enter a valid whole-number ticket ID.");
        } catch (SQLException e) {
            System.out.println("Database error: " + e.getMessage());
        }
    }
}