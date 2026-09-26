package com.aniket.helpdesklite;

public class Ticket {
	
    private int ticketId;
    private String employeeName;
    private String department;
    private String title;
    private String description;
    private String priority;
    private String status;
    
    // ----------------constructor ---------------------
    
    public Ticket(String employeeName, String department,
                  String title, String description,
                  String priority, String status) {
        this.employeeName = employeeName;
        this.department = department;
        this.title = title;
        this.description = description;
        this.priority = priority;
        this.status = status;
    }
    
    // ---------------getter methods -----------------------
    
    public int getTicketId() {
        return ticketId;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public String getDepartment() {
        return department;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getPriority() {
        return priority;
    }

    public String getStatus() {
        return status;
    }
    
    //----------------setter methods------------------
    
    public void setTicketId(int ticketId) {
        this.ticketId = ticketId;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }

    @Override
    public String toString() {
        return "Ticket ID: " + ticketId
                + "\nEmployee: " + employeeName
                + "\nDepartment: " + department
                + "\nTitle: " + title
                + "\nDescription: " + description
                + "\nPriority: " + priority
                + "\nStatus: " + status;
    }
    
}