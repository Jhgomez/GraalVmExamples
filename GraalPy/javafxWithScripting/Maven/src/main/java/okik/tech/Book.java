package okik.tech;

public class Book {
    private final String isbn;
    private final String name;
    private final String author;
    private final String genre;
    private int ranking;

    public Book(String isbn, String name, String author, String genre, int ranking) {
        this.isbn = isbn;
        this.name = name;
        this.author = author;
        this.genre = genre;
        this.ranking = ranking;
    }

    public String getName() {
        return name;
    }

    public String getGenre() {
        return genre;
    }

    public int getRanking() {
        return ranking;
    }

    public void setRanking(int ranking) {
        this.ranking = ranking;
    }

    @Override
    public String toString() {
        return "Book [isbn=" + isbn + ", name=" + name + "]";
    }
}
