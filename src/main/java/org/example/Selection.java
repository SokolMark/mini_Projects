package org.example;

import java.sql.SQLException;
import java.util.Scanner;

public class Selection {

    private static Scanner scanner = new Scanner(System.in);

    public static void inputChoice() throws SQLException {
        while (true) {
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
                case 1 -> Library.removeBook();

                case 2 -> Library.findBooksByTitle();

                case 3 -> Library.findBookByAuthor();

                case 4 -> Library.displayAllBooks();

                case 5 -> Library.addBook();

                case 6 -> {
                    System.out.println("Exiting...");
                    scanner.close();
                    return;
                }
                default -> System.out.println("Invalid option. Please try again.");
            }
        }
    }
}
