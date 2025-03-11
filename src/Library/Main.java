package Library;

import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        Library library = new Library();

        System.out.print("do you want to add book in library? ");

        System.out.println();

        String yesOrNo = scanner.nextLine();
        if (yesOrNo.equalsIgnoreCase("yes")) {
            System.out.print("Enter book`s title: ");
            String inputTitle = scanner.nextLine();
            System.out.println();

            System.out.print("Enter book`s author: ");
            String inputAuthor = scanner.nextLine();
            System.out.println();

            System.out.print("Enter book`s year: ");
            int inputYear = scanner.nextInt();
            scanner.nextLine();
            System.out.println();

            System.out.print("Сome up with your ISBN code for the book: ");
            String inputIsbn = scanner.nextLine();
            System.out.println();

            library.addBook(new Book(inputTitle, inputAuthor, inputYear, inputIsbn));
            library.displayAllBooks();

            System.out.println("select one of the active " +
                    " 1 - delete book," +
                    " 2 - find book by name," +
                    " 3 - find book by author," +
                    " 4 - show all information about books," +
                    " 5 - exit");
            int number = scanner.nextInt();
            scanner.nextLine();
            switch (number) {
                case 1:
                    System.out.println("Write book`s isbn for remove");
                    String booksIsbn = scanner.nextLine();
                    boolean isRebove = library.removeBook(booksIsbn);
                    if (isRebove) {
                        System.out.println("Book deleted");
                    } else {
                        System.out.println("No such book");
                    }

                    library.displayAllBooks();
                    break;

                case 2:
                    System.out.println("Write book`s title");
                    String booksTitle = scanner.nextLine();
                    List<Book> findBookByTitle = library.findBooksByTitle(booksTitle);
                    if (findBookByTitle.isEmpty()) {
                        System.out.println("No such book");
                    } else {
                        System.out.println("Book: " + findBookByTitle);

                        break;

                    }
                case 3:
                    System.out.println("Write book`s author: ");
                    String booksAuthor = scanner.nextLine();
                    List<Book> findBookByAuthor = library.findBooksByAuthor(booksAuthor);
                    if (findBookByAuthor.isEmpty()) {
                        System.out.println("No such book");
                    } else {
                        System.out.println("Book: " + findBookByAuthor);
                    }
                    break;

                case 4:
                    library.displayAllBooks();
                    break;

                case 5:
                    System.out.println("Exiting...");
                    break;
            }
        }
    }
}

