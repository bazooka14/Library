package core;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BookManager {
    private static final List<String> library = new ArrayList<>();
    private static final Map<String, ArrayList<String>> users = new HashMap<>();

    public static void addBook(String book) {
        if (!library.contains(book)) {
            library.add(book);
        } else {
            Terminal.writeln("Такая книга уже есть");
        }
    }

    public static void removeBook(String book) {
        if (library.contains(book)) {
            library.remove(book);
        } else {
            Terminal.writeln("Такой книги нет. Проверьте ввод.");
        }
    }

    public static void searchBook(String query) {
        for (String book : library) {
            if (book.toLowerCase().contains(query.toLowerCase())) {
                Terminal.writeln(book);
            }
        }
    }

    public static void displayAllBooks() {
        for (String book : library) {
            Terminal.writeln(book);
        }
    }

    private static boolean isBookAvailable(String book) {
        return library.contains(book);
    }

    public static void addUser(String username) {
        if (!getUserByName(username)) {
            users.put(username, new ArrayList<>());
        } else {
            Terminal.writeln("Такой пользователь уже существует.");
        }
    }

    public static void removeUser(String username) {
        if (getUserByName(username)) {
            users.remove(username);
        } else {
            Terminal.writeln("Такого пользователя не существует.");
        }
    }

    public static void giveBookToUser(String username, String book) {
        if (isBookAvailable(book) && getUserByName(username)) {
            users.get(username).add(book);
        } else {
            Terminal.writeln("Нет такого пользователя или книги. Проверьте ввод.");
        }
    }

    public static void displayUserBooks(String username) {
        if (getUserByName(username)) {
            for (String book : users.get(username)) {
                Terminal.writeln(book);
            }
        } else {
            Terminal.writeln("Нет такого пользователя. Проверьте ввод.");
        }
    }

    private static boolean getUserByName(String username) {
        return users.containsKey(username);
    }
}
