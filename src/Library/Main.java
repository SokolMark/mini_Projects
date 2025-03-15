package Library;

public class Main {
    public static void main(String[] args) {
        Data data = new Data();
        Library library = new Library();

        boolean isAnswerYes = data.getAnswer();

        if (isAnswerYes) { // user can add a first book (if user will write "yes")
            library.addBook(data.getBook(library));
            library.displayAllBooks();
        }

        data.inputChoice(library); // user can interaction with menu
    }
}
