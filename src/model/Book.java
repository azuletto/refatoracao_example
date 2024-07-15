package src.model;

public class Book {
    private int id;
    private String title;
    private String author;
    private String category;
    private String publication;
    private boolean isIssued;

    // Getters and setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }
    public String getAuthor() { return author; }
    public void setAuthor(String author) { this.author = author; }
    public String getCategory() { return category; }
    public void setCategory(String category) { this.category = category; }
    public String getPublication() { return publication; }
    public void setPublication(String publication) { this.publication = publication; }
    public boolean isIssued() { return isIssued; }
    public void setIssued(boolean isIssued) { this.isIssued = isIssued; }
}
