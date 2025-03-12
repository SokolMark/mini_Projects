package Library;

import java.util.Scanner;

public class Data {

    public boolean getAnswer(Scanner scanner) {
        System.out.println("do you want to add book in library?");
        String answer = scanner.nextLine();

        return answer.equalsIgnoreCase("yes");
    }

    public String isbnInput(Scanner scanner, Library library) {
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

    public String authorInput(Scanner scanner) {
        System.out.print("Enter book`s author: ");

        return scanner.nextLine();
    }

    public String titleInput(Scanner scanner) {
        System.out.print("Enter book`s title: ");

        return scanner.nextLine();
    }

    public int yearInput(Scanner scanner) {
        System.out.print("Enter book`s year: ");

        return scanner.nextInt();
    }

    public Library getBook(Library library, Scanner scanner) {

        String title = titleInput(scanner);
        String author = authorInput(scanner);
        String isbn = isbnInput(scanner, library);
        int year = yearInput(scanner);

        library.addBook(new Book(title, author, year, isbn));

        return library;
    }

}
