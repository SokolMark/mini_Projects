package org.example;

import DB.ConnectionManager;

import java.sql.*;
import java.util.Scanner;

public class Library {

    private static Scanner scanner = new Scanner(System.in);

    public static void addBook() throws SQLException {

        System.out.println("Please enter the title of the book: ");
        String title = scanner.nextLine();

        Integer authorId = getAuthorID();

        System.out.println("Please enter the year of the book ' " + title + " ': ");
        int year = scanner.nextInt();
        scanner.nextLine();

        System.out.println("please think up the code for the book ' " + title + " ': ");
        String isbn = scanner.nextLine();

        String sqlBook = """
                INSERT INTO books (title, author_id, year, isbn) values (?, ?, ?, ?)              
                """;

        try (Connection connection = ConnectionManager.getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(sqlBook)) {
            preparedStatement.setString(1, title);
            preparedStatement.setInt(2, authorId);
            preparedStatement.setInt(3, year);
            preparedStatement.setString(4, isbn);

            int affectedRows = preparedStatement.executeUpdate();
            System.out.println("Book added successfully!");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private static Integer findAuthorIdByName(String name) {
        String sql = """
                SELECT id FROM authors WHERE name = ?
                """;

        try (Connection connection = ConnectionManager.getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, name);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return resultSet.getInt("id");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    public static Integer getAuthorID() throws SQLException {
        System.out.println("Enter names author: ");
        String author = scanner.nextLine();

        Integer authorId = findAuthorIdByName(author);

        if (authorId == null) {
            insertAuthor(author);
            return findAuthorIdByName(author);
        } else {
            return authorId;
        }
    }

    public static void insertAuthor(String name) throws SQLException {
        String sqlAuthor = """
                INSERT INTO authors (name) VALUES (?) 
                """;

        try (Connection connection = ConnectionManager.getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(sqlAuthor)) {
            preparedStatement.setString(1, name);
            int affectedRows = preparedStatement.executeUpdate();
        } catch (SQLException e) {
            if (!e.getMessage().contains("duplicate key value")) {
                throw new RuntimeException(e);
            }
        }

    }

    public static void removeBook() {
        String sqlRemove = """   
                DELETE FROM books
                WHERE isbn = ?;
                """;

        System.out.println("Enter book isbn: ");
        String inputISBN = scanner.nextLine();

        try (Connection connection = ConnectionManager.getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(sqlRemove)) {
            preparedStatement.setString(1, inputISBN);
            int affectedRows = preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void findBooksByTitle() {
        String sqlBookTitle = """
                SELECT books.title, books.year, books.isbn, authors.name
                FROM books 
                JOIN authors ON books.author_id = authors.id
                WHERE title = ?
                """;

        System.out.println("Enter book title: ");
        String inputTitle = scanner.nextLine();

        try (Connection connection = ConnectionManager.getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(sqlBookTitle)) {
            preparedStatement.setString(1, inputTitle);
            ResultSet resultSet = preparedStatement.executeQuery();


            if (resultSet.next()) {
                System.out.println("--------------Book found------------------");
                String title = resultSet.getString("title");
                int year = resultSet.getInt("year");
                String isbn = resultSet.getString("isbn");
                String name = resultSet.getString("name");

                System.out.printf("Назва: %s\nАвтор: %s\nРік: %d\nISBN: %s\n------------------------------------------\n", title, name, year, isbn);
            } else System.out.println("Book not found");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void findBookByAuthor() {
        String sqlBookAuthor = """
                SELECT books.title, books.year, books.isbn, authors.name
                FROM books 
                JOIN authors ON books.author_id = authors.id
                WHERE name = ?
                """;

        System.out.println("Enter author`s name: ");
        String inputName = scanner.nextLine();

        try (Connection connection = ConnectionManager.getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(sqlBookAuthor)) {
            preparedStatement.setString(1, inputName);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                System.out.println("----------------------------------------");
                String title = resultSet.getString("title");
                int year = resultSet.getInt("year");
                String isbn = resultSet.getString("isbn");
                String name = resultSet.getString("name");

                System.out.printf("Назва: %s\nАвтор: %s\nРік: %d\nISBN: %s\n----------------------------------------\n", title, name, year, isbn);
            }
        } catch (SQLException e) {
            System.out.println("Book not found");
            throw new RuntimeException(e);
        }

    }

    public static void displayAllBooks() {

        String sqlBook = """
                SELECT books.title, books.year, books.isbn, authors.name AS author
                FROM books 
                JOIN authors 
                ON books.author_id = authors.id;
                """;

        try (Connection connection = ConnectionManager.getConnection(); Statement statement = connection.createStatement()) {
            ResultSet rs = statement.executeQuery(sqlBook);

            System.out.println("----------------------------------------");
            while (rs.next()) {
                String title = rs.getString("title");
                int year = rs.getInt("year");
                String isbn = rs.getString("isbn");
                String author = rs.getString("author");

                System.out.printf("Назва: %s\nАвтор: %s\nРік: %d\nISBN: %s\n----------------------------------------\n", title, author, year, isbn);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
