package org.example;

import DB.ConnectionManager;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class Data {
    private Scanner scanner = new Scanner(System.in);

    public boolean getAnswer() {
        System.out.println("do you want to add book in library?");
        String answer = scanner.nextLine();

        return answer.equalsIgnoreCase("yes");
    }

    public void insertBook() throws SQLException {
        System.out.println("Please enter the title of the book: ");
        String title = scanner.nextLine();

        insertAuthor();

        System.out.println("Please enter the author`s id: ");
        int authorId = scanner.nextInt();

        System.out.println("Please enter the year of the book ' " + title + " ': ");
        int year = scanner.nextInt();

        System.out.println("please think up the code for the book ' " + title + " ': ");
        String isbn = scanner.nextLine();


        String sqlBook = """
                INSERT INTO books (title, author_id, isbn, year) values (?, ?, ?, ?)              
                """;

        try (Connection connection = ConnectionManager.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sqlBook)) {
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


        public void insertAuthor () throws SQLException {
            System.out.println("Enter names author: ");
            String author = scanner.nextLine();

            String sqlAuthor = """
                    INSERT INTO authors (name) VALUES(?)
                    """;

            try (Connection connection = ConnectionManager.getConnection()) {
                try (PreparedStatement preparedStatement = connection.prepareStatement(sqlAuthor)) {
                    preparedStatement.setString(1, author);
                    int affectedRows = preparedStatement.executeUpdate();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }

                public void inputChoice () {
                    while (true) {
                        Data data = new Data();
                        System.out.println("Select one of the options: " +
                                           "1 - Delete book, " +
                                           "2 - Find book by title, " +
                                           "3 - Find book by author, " +
                                           "4 - Show all information about books, " +
                                           "5 - add a book, " +
                                           "6 - Exit");
                        int number = scanner.nextInt();
                        scanner.nextLine();

                        switch (number) {
                            case 1:
                                System.out.println("Write book's ISBN for removal:");
                                String booksIsbn = scanner.nextLine();
                                boolean isRemoved = library.removeBook(booksIsbn);
                                if (isRemoved) {
                                    System.out.println("Book deleted.");
                                } else {
                                    System.out.println("No such book.");
                                }
                                library.displayAllBooks();
                                break;

                            case 2:
                                System.out.println("Write book's title:");
                                String booksTitle = scanner.nextLine();
                                List<Book> findBookByTitle = library.findBooksByTitle(booksTitle);
                                if (findBookByTitle.isEmpty()) {
                                    System.out.println("No such book.");
                                } else {
                                    System.out.println("Book: " + findBookByTitle);
                                }
                                break;

                            case 3:
                                System.out.println("Write book's author:");
                                String booksAuthor = scanner.nextLine();
                                List<Book> findBookByAuthor = library.findBooksByAuthor(booksAuthor);
                                if (findBookByAuthor.isEmpty()) {
                                    System.out.println("No such book.");
                                } else {
                                    System.out.println("Book: " + findBookByAuthor);
                                }
                                break;

                            case 4:
                                library.displayAllBooks();
                                break;

                            case 5:

                                break;

                            case 6:
                                System.out.println("Exiting...");
                                scanner.close();
                                return;

                            default:
                                System.out.println("Invalid option. Please try again.");
                                break;
                        }
                    }
                }
            }
        }
}