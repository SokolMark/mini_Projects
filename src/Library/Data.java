package Library;

import java.util.List;
import java.util.Scanner;

public class Data {
    private Scanner scanner = new Scanner(System.in);

    public boolean getAnswer() {
        System.out.println("do you want to add book in library?");
        String answer = scanner.nextLine();

        return answer.equalsIgnoreCase("yes");
    }

    public String isbnInput(Library library) {
        while (true) {
            System.out.print("Сome up with your ISBN code for the book: ");
            String isbn = scanner.nextLine();

            boolean isUnique = true;
            for (Book book : library.getBooks()) {
                if (book.getIsbn().equalsIgnoreCase(isbn)) {
                    System.out.println("Такий ISBN вже існує. Спробуйте ще раз");
                    isUnique = false;
                    break;
                }
            }
            if (isUnique) {
                return isbn;
            }
        }
    }

    public String authorInput() {
        System.out.print("Enter book`s author: ");

        return scanner.nextLine();
    }

    public String titleInput() {
        System.out.print("Enter book`s title: ");

        return scanner.nextLine();
    }

    public int yearInput() {
        System.out.print("Enter book`s year: ");

        return scanner.nextInt();
    }

    public Book getBook(Library library) {

        String title = titleInput();
        String author = authorInput();
        String isbn = isbnInput(library);
        int year = yearInput();

        return new Book(title, author, year, isbn);
    }

    public void inputChoice(Library library) {
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
                    library.addBook(data.getBook(library));

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
