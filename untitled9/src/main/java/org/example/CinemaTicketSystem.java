package org.example;
import java.sql.*;
import java.util.Scanner;

public class CinemaTicketSystem {

    private static final String DB_URL = "jdbc:postgresql://localhost:5432/cinema_tickets";
    private static final String DB_USER = "postgres";
    private static final String DB_PASSWORD = "Altairf4";

    public static void main(String[] args) {
        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
             Scanner scanner = new Scanner(System.in)) {
            System.out.println("Добро пожаловать в систему Тикетон");

            while (true) {
                System.out.println("1. Каталог фильмов");
                System.out.println("2. Мой билет");
                System.out.println("3. Добавить Фильм");
                System.out.println("4. Выти");
                System.out.print("Напиши свой выбор: ");
                int choice = scanner.nextInt();
                scanner.nextLine(); // consume newline

                switch (choice) {
                    case 1:
                        viewMovieCatalog(connection);
                        break;
                    case 2:
                        viewMyTickets(connection);
                        break;
                    case 3:
                        addMovie(connection, scanner);
                        break;
                    case 4:
                        System.out.println("Прощай...");
                        return;
                    default:
                        System.out.println("Такого выбора нету, выбери другую цифру");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void viewMovieCatalog(Connection connection) throws SQLException {
        String sql = "SELECT * FROM movies";
        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)) {
            System.out.println("Каталог фильмов");
            while (resultSet.next()) {
                System.out.println("Название: " + resultSet.getString("Название"));
                System.out.println("Описание: " + resultSet.getString("Описание"));
                System.out.println("Дата выхода: " + resultSet.getDate("Дата выхода"));
                System.out.println("Длительность: " + resultSet.getInt("длительноость_минуты") + " minutes");
                System.out.println("Rating: " + resultSet.getDouble("rating"));
                System.out.println();
            }
        }
    }

    private static void viewMyTickets(Connection connection) throws SQLException {
        // Implement logic to view tickets for the current user
        System.out.println("Viewing my tickets...");
        // Ваша логика для просмотра билетов пользователя
    }

    private static void addMovie(Connection connection, Scanner scanner) throws SQLException {
        System.out.println("Enter movie details:");
        System.out.print("Title: ");
        String title = scanner.nextLine();
        System.out.print("Description: ");
        String description = scanner.nextLine();
        System.out.print("Release Date (YYYY-MM-DD): ");
        String releaseDate = scanner.nextLine();
        System.out.print("Duration (minutes): ");
        int durationMinutes = scanner.nextInt();
        System.out.print("Rating: ");
        double rating = scanner.nextDouble();

        String sql = "INSERT INTO movies (title, description, release_date, duration_minutes, rating) " +
                "VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, title);
            statement.setString(2, description);
            statement.setString(3, releaseDate);
            statement.setInt(4, durationMinutes);
            statement.setDouble(5, rating);
            int rowsInserted = statement.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("Movie added successfully!");
            }
        }
    }
}
