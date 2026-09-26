package com.aniket.helpdesklite;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        TicketDAO ticketDAO = new TicketDAO();

        try (Scanner scanner = new Scanner(System.in)) {
            System.out.print("Enter employee name or part of a name: ");
            String employeeName = scanner.nextLine().trim();

            if (employeeName.isEmpty()) {
                System.out.println("Employee name cannot be empty.");
                return;
            }

            List<Ticket> tickets =
                    ticketDAO.findByEmployeeName(employeeName);

            if (tickets.isEmpty()) {
                System.out.println("No tickets found for: " + employeeName);
            } else {
                System.out.println("Matching tickets:");
                for (Ticket ticket : tickets) {
                    System.out.println("--------------------");
                    System.out.println(ticket);
                }
            }

        } catch (SQLException e) {
            System.out.println("Database error: " + e.getMessage());
        }
    }
}