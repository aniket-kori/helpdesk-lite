package com.aniket.helpdesklite;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class TicketDAO {

    public Ticket createTicket(Ticket ticket) throws SQLException {
        String sql = """
                INSERT INTO tickets
                (employee_name, department, title, description, priority, status)
                VALUES (?, ?, ?, ?, ?, ?)
                """;

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(
                     sql, Statement.RETURN_GENERATED_KEYS)) {

            statement.setString(1, ticket.getEmployeeName());
            statement.setString(2, ticket.getDepartment());
            statement.setString(3, ticket.getTitle());
            statement.setString(4, ticket.getDescription());
            statement.setString(5, ticket.getPriority());
            statement.setString(6, ticket.getStatus());

            statement.executeUpdate();

            try (var keys = statement.getGeneratedKeys()) {
                if (keys.next()) {
                    ticket.setTicketId(keys.getInt(1));
                }
            }
        }

        return ticket;
    }
    public List<Ticket> getAllTickets() throws SQLException {
        List<Ticket> tickets = new ArrayList<>();

        String sql = """
                SELECT ticket_id, employee_name, department,
                       title, description, priority, status
                FROM tickets
                ORDER BY ticket_id
                """;

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                Ticket ticket = new Ticket(
                        resultSet.getString("employee_name"),
                        resultSet.getString("department"),
                        resultSet.getString("title"),
                        resultSet.getString("description"),
                        resultSet.getString("priority"),
                        resultSet.getString("status")
                );

                ticket.setTicketId(resultSet.getInt("ticket_id"));
                tickets.add(ticket);
            }
        }

        return tickets;
    }
    public Ticket findById(int ticketId) throws SQLException {
        String sql = """
                SELECT ticket_id, employee_name, department,
                       title, description, priority, status
                FROM tickets
                WHERE ticket_id = ?
                """;

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, ticketId);

            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    Ticket ticket = new Ticket(
                            resultSet.getString("employee_name"),
                            resultSet.getString("department"),
                            resultSet.getString("title"),
                            resultSet.getString("description"),
                            resultSet.getString("priority"),
                            resultSet.getString("status")
                    );

                    ticket.setTicketId(resultSet.getInt("ticket_id"));
                    return ticket;
                }
            }
        }

        return null;
    }
    public boolean updateStatus(int ticketId, String newStatus)
            throws SQLException {

        String sql = """
                UPDATE tickets
                SET status = ?
                WHERE ticket_id = ?
                """;

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, newStatus);
            statement.setInt(2, ticketId);

            int rowsUpdated = statement.executeUpdate();
            return rowsUpdated > 0;
        }
    }
}