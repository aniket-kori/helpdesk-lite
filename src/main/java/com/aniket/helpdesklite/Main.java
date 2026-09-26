package com.aniket.helpdesklite;

import java.sql.SQLException;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        TicketDAO ticketDAO = new TicketDAO();

        try {
            List<Ticket> tickets = ticketDAO.getAllTickets();

            if (tickets.isEmpty()) {
                System.out.println("No tickets found.");
            } else {
                for (Ticket ticket : tickets) {
                    System.out.println("--------------------");
                    System.out.println(ticket);
                }
            }
        } catch (SQLException e) {
            System.out.println("Could not retrieve tickets: " + e.getMessage());
        }
    }
}