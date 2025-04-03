package org.example;

import java.util.ArrayList;
import java.util.List;

public class Library {

    public void removeBook() {

        String sqlRemove = """
                
                DELETE FROM books
                WHERE isbn = ?;
                """;

    }

    public void  findBooksByTitle() {

    }

    public void findBooksByAuthor() {

    }

    public void displayAllBooks() {

    }
}