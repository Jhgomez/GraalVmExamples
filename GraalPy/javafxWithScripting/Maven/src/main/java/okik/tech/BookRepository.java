package okik.tech;

import java.util.ArrayList;
import java.util.List;

public class BookRepository {
    public static List<Book> books = new ArrayList<>(
            List.of(
                    new Book("9780060935467", "To Kill a Mockingbird", "Harper Lee", "Fiction", 1),
                    new Book("9781328869333", "1984", "George Orwell", "Dystopian", 2),
                    new Book("9780141439518", "Pride and Prejudice", "Jane Austen", "Romance", 3),
                    new Book("9798351145013", "The Great Gatsby", "F. Scott Fitzgerald", "Classics", 4),
                    new Book("9781853260087", "Moby Dick", "Herman Melville", "Adventure", 5),
                    new Book("9781400079988", "War and Peace", "Leo Tolstoy", "Historical", 6),
                    new Book("9780316769174", "The Catcher in the Rye", "J.D. Salinger", "Fiction", 7),
                    new Book("9780063347533", "The Hobbit", "J.R.R. Tolkien", "Fantasy", 8),
                    new Book("9781338878929", "Harry Potter and the Sorcerer's Stone", "J.K. Rowling", "Fantasy", 9),
                    new Book("9780307387899", "The Road", "Cormac McCarthy", "Post-Apocalyptic", 10),
                    new Book("9780062390622", "The Alchemist", "Paulo Coelho", "Philosophy", 11),
                    new Book("9780679734505", "Crime and Punishment", "Fyodor Dostoevsky", "Crime", 12),
                    new Book("9780345538376", "The Lord of the Rings", "J.R.R. Tolkien", "Fantasy", 13),
                    new Book("9780143131847", "Frankenstein", "Mary Shelley", "Science Fiction", 14),
                    new Book("9781454944218", "Dracula", "Bram Stoker", "Horror", 15),
                    new Book("9780553211405", "Jane Eyre", "Charlotte Bronte", "Classics", 16)
            )
    );
}
