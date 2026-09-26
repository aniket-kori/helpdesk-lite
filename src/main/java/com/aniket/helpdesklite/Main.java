package com.aniket.helpdesklite;

import java.sql.SQLException;

public class Main {

    public static void main(String[] args) {
        Ticket ticket = new Ticket(
                "Aniket Kori",
                "IT",
                "Laptop not connecting to Wi-Fi",
                "The laptop cannot connect to the office Wi-Fi network.",
                "HIGH",
                "OPEN"
        );

        TicketDAO ticketDAO = new TicketDAO();

        try {
            Ticket createdTicket = ticketDAO.createTicket(ticket);

            System.out.println("Ticket created successfully!");
            System.out.println("Generated ticket ID: "
                    + createdTicket.getTicketId());
        } catch (SQLException e) {
            System.out.println("Could not create ticket: " + e.getMessage());
        }
    }
}
