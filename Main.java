import java.util.Scanner;

class Book {
    String title;
    String author;
    String category;
    int totalCopies;
    int borrowedCopies;

    Book(String title, String author, String category, int totalCopies) {
        this.title = title;
        this.author = author;
        this.category = category;
        this.totalCopies = totalCopies;
        this.borrowedCopies = 0;
    }

    boolean isAvailable() {
        return borrowedCopies < totalCopies;
    }

    void borrowBook() {
        if (isAvailable()) {
            borrowedCopies++;
        }
    }

    void returnBook() {
        if (borrowedCopies > 0) {
            borrowedCopies--;
        }
    }
}

class Library {
    Book[] books = new Book[100];
    int count = 0;
    Scanner sc = new Scanner(System.in);

    void addBook() {
        System.out.print("Enter book title: ");
        String title = sc.nextLine();

        System.out.print("Enter author name: ");
        String author = sc.nextLine();

        System.out.print("Enter category: ");
        String category = sc.nextLine();

        System.out.print("Enter total number of copies: ");
        int copies = sc.nextInt();
        sc.nextLine(); // consume leftover newline

        // Check if book already exists
        for (int i = 0; i < count; i++) {
            if (books[i].title.equalsIgnoreCase(title) && books[i].author.equalsIgnoreCase(author)) {
                books[i].totalCopies += copies;
                System.out.println("Book already exists. Copies updated.");
                return;
            }
        }

        books[count] = new Book(title, author, category, copies);
        count++;
        System.out.println("Book added successfully!");
    }

    void viewBooks() {
        if (count == 0) {
            System.out.println("No books available.");
            return;
        }

        System.out.println("\n---- Library Books ----");
        for (int i = 0; i < count; i++) {
            Book b = books[i];
            System.out.println((i + 1) + ". " + b.title + " by " + b.author +
                    " | Category: " + b.category +
                    " | Available: " + (b.totalCopies - b.borrowedCopies) +
                    "/" + b.totalCopies);
        }
        System.out.println("------------------------");
    }

    void borrowBook() {
        if (count == 0) {
            System.out.println("No books to borrow.");
            return;
        }

        viewBooks();
        System.out.print("Enter book number to borrow: ");
        int num = sc.nextInt();
        sc.nextLine(); // consume newline

        if (num < 1 || num > count) {
            System.out.println("Invalid book number!");
            return;
        }

        Book b = books[num - 1];
        if (b.isAvailable()) {
            b.borrowBook();
            System.out.println("You borrowed \"" + b.title + "\" successfully!");
        } else {
            System.out.println("No copies available to borrow.");
        }
    }

    void returnBook() {
        if (count == 0) {
            System.out.println("No books in library.");
            return;
        }

        viewBooks();
        System.out.print("Enter book number to return: ");
        int num = sc.nextInt();
        sc.nextLine(); // consume newline

        if (num < 1 || num > count) {
            System.out.println("Invalid book number!");
            return;
        }

        Book b = books[num - 1];
        if (b.borrowedCopies > 0) {
            b.returnBook();
            System.out.println("You returned \"" + b.title + "\" successfully!");
        } else {
            System.out.println("All copies are already in the library.");
        }
    }

    void searchBook() {
        System.out.print("Enter keyword to search: ");
        String key = sc.nextLine().toLowerCase();
        boolean found = false;

        System.out.println("\nSearch Results:");
        for (int i = 0; i < count; i++) {
            Book b = books[i];
            if (b.title.toLowerCase().contains(key) || b.author.toLowerCase().contains(key) || b.category.toLowerCase().contains(key)) {
                System.out.println((i + 1) + ". " + b.title + " by " + b.author +
                        " | Category: " + b.category);
                found = true;
            }
        }

        if (!found) {
            System.out.println("No matching books found.");
        }
    }
}

class LibraryManagementSystem {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Library lib = new Library();
        int choice;

        do {
            System.out.println("\n========= LIBRARY MENU =========");
            System.out.println("1. Add Book");
            System.out.println("2. View All Books");
            System.out.println("3. Borrow Book");
            System.out.println("4. Return Book");
            System.out.println("5. Search Book");
            System.out.println("6. Exit");
            System.out.print("Enter your choice: ");
            choice = sc.nextInt();
            sc.nextLine(); // consume newline

            switch (choice) {
                case 1 -> lib.addBook();
                case 2 -> lib.viewBooks();
                case 3 -> lib.borrowBook();
                case 4 -> lib.returnBook();
                case 5 -> lib.searchBook();
                case 6 -> System.out.println("Exiting Library System... Thank you!");
                default -> System.out.println("Invalid choice. Try again!");
            }
        } while (choice != 6);
    }
}
