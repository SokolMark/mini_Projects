package Library;

import java.util.ArrayList;
import java.util.List;

public class Library {

    private final List<Book> books = new ArrayList<>();

    public List<Book> getBooks(){
        return books;
    }

    public void addBook(Book book) {
        books.add(book);
    }

    public boolean removeBook(String isbn) {
        for (int i = 0; i < books.size(); i++) {
            if (books.get(i).getIsbn().equals(isbn)) {
                books.remove(i);
                return true;
            }
        }
        return false;
    }

    public List<Book> findBooksByTitle(String title) {
        List<Book> listBooks = new ArrayList<>();
        for (int i = 0; i < books.size(); i++) {
            if (books.get(i).getTitle().equals(title)) {
                listBooks.add(books.get(i));
            }
        }
        return listBooks;
    }

    public List<Book> findBooksByAuthor(String author) {
        List<Book> listBooks = new ArrayList<>();
        for (int i = 0; i < books.size(); i++) {
            if (books.get(i).getAuthor().equals(author)) {
                listBooks.add(books.get(i));
            }
        }
        return listBooks;
    }

    public void displayAllBooks() {
        if (books.isEmpty()) {
            System.out.println("The library is empty.");
        } else {
            for (Book book : books) {
                System.out.println(book);
            }
        }
    }
}
