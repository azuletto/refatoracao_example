package src.controller;

import src.model.IssueBook;
import java.util.ArrayList;
import java.util.List;

public class IssueBookController {
    private List<IssueBook> issuedBooks = new ArrayList<>();

    public void issueBook(IssueBook issueBook) {
        issuedBooks.add(issueBook);
    }

    public void returnBook(int id) {
        issuedBooks.removeIf(issueBook -> issueBook.getId() == id);
    }

    public IssueBook getIssuedBook(int id) {
        for (IssueBook issueBook : issuedBooks) {
            if (issueBook.getId() == id) {
                return issueBook;
            }
        }
        return null;
    }

    public List<IssueBook> getAllIssuedBooks() {
        return issuedBooks;
    }
}
