# HelpDesk Lite

A Java console-based IT support ticket tracker built with JDBC and MySQL.
It lets users create, search, view, update, and close support tickets.

## Features

- Create a ticket with employee name, department, title, description, and priority
- Automatically generate a ticket ID using MySQL
- View all tickets
- Search tickets by ID
- Search tickets by full or partial employee name
- Update ticket status (`OPEN`, `IN_PROGRESS`, `CLOSED`)
- Update ticket priority (`LOW`, `MEDIUM`, `HIGH`)
- Close an existing ticket
- Validate required inputs and allowed status/priority values

## Technologies

- Java
- Maven
- JDBC
- MySQL
- MySQL Connector/J

## Requirements

- JDK 26
- Maven
- MySQL Server
- Eclipse IDE or another Java IDE

## Database setup

1. Start your local MySQL server.
2. Open MySQL Workbench or a MySQL client.
3. Run the SQL script in `database/schema.sql` to create the database and table.

The app expects a database named `helpdesk_db` on `localhost`, port `3306`.

## Database credentials

Set these environment variables in your run configuration:

- `MYSQL_USER` — your MySQL username
- `MYSQL_PASSWORD` — your MySQL password

Do not commit database passwords or other credentials to the repository.

## Run the application

1. Import the project into your Java IDE as an existing Maven project.
2. Wait for Maven to resolve the dependencies.
3. Set `MYSQL_USER` and `MYSQL_PASSWORD` in the Java application run configuration.
4. Run `com.aniket.helpdesklite.Main`.

The console menu will appear. Choose an option and follow the prompts.

## Project structure

```text
helpdesk-lite/
├── database/
│   └── schema.sql
├── src/
│   └── main/
│       └── java/
│           └── com/
│               └── aniket/
│                   └── helpdesklite/
│                       ├── DBConnection.java
│                       ├── Main.java
│                       ├── Ticket.java
│                       └── TicketDAO.java
├── .gitignore
├── pom.xml
└── README.md