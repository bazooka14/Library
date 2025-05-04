package core;

import java.net.Inet4Address;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BookManager {
    private static final Map<String, Integer> library = new HashMap<>();
    private static final Map<String, ArrayList<String>> users = new HashMap<>();

    public static void addBook(String book) {
        library.put(book, library.getOrDefault(book, 0) + 1);
    }

    public static void removeBook(String book) {
        if (library.containsKey(book)) {
            library.remove(book);
        } else {
            Terminal.writeln("Такой книги нет. Проверьте ввод.");
        }
    }

    public static void searchBook(String query) {
        for (Map.Entry<String, Integer> entry: library.entrySet()) {
            if (entry.getKey().toLowerCase().contains(query.toLowerCase())) {
                Terminal.writeln(entry.getKey() + " | Количество: " + entry.getValue());
            }
        }
    }

    public static void displayAllBooks() {
        for (Map.Entry<String, Integer> entry: library.entrySet()) {
            Terminal.writeln(entry.getKey() + " | Количество: " + entry.getValue());
        }
    }

    private static boolean isBookAvailable(String book) {
        return library.containsKey(book) && library.get(book) != 0;
    }

    public static void addUser(String username) {
        if (!users.containsKey(username)) {
            users.put(username, new ArrayList<>());
        } else {
            Terminal.writeln("Такой пользователь уже существует.");
        }
    }

    public static void removeUser(String username) {
        if (users.containsKey(username)) {
            users.remove(username);
        } else {
            Terminal.writeln("Такого пользователя не существует.");
        }
    }

    public static void giveBookToUser(String username, String book) {
        if (library.containsKey(book) && users.containsKey(username)) {
            users.get(username).add(book);
        } else {
            Terminal.writeln("Нет такого пользователя или книги. Проверьте ввод.");
        }
    }

    public static void displayUserBooks(String username) {
        if (users.containsKey(username)) {
            for (String book : users.get(username)) {
                Terminal.writeln(book);
            }
        } else {
            Terminal.writeln("Нет такого пользователя. Проверьте ввод.");
        }
    }
}
