package Library;

public class Book{
    private final String title;
    private final String author;
    private final int year;
    private final String isbn;

    public Book(String title, String author, int year, String isbn) {
        this.title = title;
        this.author = author;
        this.year = year;
        this.isbn = isbn;
    }

    public String getIsbn() {
        return isbn;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    @Override
    public String toString() {
        return "Book: " +
                " title = " + title +
                ", author = " + author +
                ", year = " + year +
                ", isbn = " + isbn;
    }
}