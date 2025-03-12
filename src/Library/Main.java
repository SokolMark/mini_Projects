package Library;

import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        Data data = new Data();
        Library library = new Library();

        boolean isAnswerYes = data.getAnswer(scanner);

        if (isAnswerYes) {
            data.getBook(library, scanner);
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
                    data.getBook(library, scanner);

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
