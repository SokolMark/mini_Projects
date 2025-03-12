package Library;

import java.util.List;
import java.util.Scanner;

public class Main {

    private static boolean getAnswear(Scanner scanner) {
        System.out.println("do you want to add book in library?");
        String answear = scanner.nextLine();

        return answear.equalsIgnoreCase("yes");
    }

    private static String isbnInput(Scanner scanner) {
        System.out.print("Сome up with your ISBN code for the book: ");
        return scanner.nextLine();

    }

    private static String authorInput(Scanner scanner) {
        System.out.print("Enter book`s author: ");

        return scanner.nextLine();
    }

    private static String titleInput(Scanner scanner) {
        System.out.print("Enter book`s title: ");

        return scanner.nextLine();
    }

    private static int yearInput(Scanner scanner) {
        System.out.print("Enter book`s year: ");

        return scanner.nextInt();
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        Library library = new Library();

        boolean isAnswearYes = getAnswear(scanner);

        if (isAnswearYes) {
            String title = titleInput(scanner);
            String author = authorInput(scanner);
            String isbn = isbnInput(scanner);
            int year = yearInput(scanner);

            library.addBook(new Book(title, author, year, isbn));

            library.displayAllBooks();

        }

        while (true) {
            System.out.println("Select one of the options: " +
                    "1 - Delete book, " +
                    "2 - Find book by title, " +
                    "3 - Find book by author, " +
                    "4 - Show all information about books, " +
                    "5 - add a book, " +
                    "6 - Exit");
            int number = scanner.nextInt();
            scanner.nextLine(); // Очистити буфер

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
                    String title = titleInput(scanner);
                    String author = authorInput(scanner);
                    String isbn = isbnInput(scanner);
                    int year = yearInput(scanner);

                    library.addBook(new Book(title, author, year, isbn));

                    library.displayAllBooks();
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
