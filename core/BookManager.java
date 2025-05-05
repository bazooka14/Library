package core;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.StringJoiner;

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

    public static void borrowBook(String username, String book) {
        if (!users.containsKey(username)) {
            Terminal.writeln("Такого пользователя не существует.");
        } else if (!isBookAvailable(book)){
            Terminal.writeln("Этой книги нет.");
        } else if (users.get(username).contains(book)){
            Terminal.writeln("У этого пользователя уже есть эта книга!");
        } else {
            library.put(book, library.get(book) - 1);
            users.get(username).add(book);
        }
    }

    public static void addBookToUser(String username, String book) {
        if (!users.containsKey(username)) {
            Terminal.writeln("Такого пользователя не существует.");
        } else if (users.get(username).contains(book)){
            Terminal.writeln("У этого пользователя уже есть эта книга!");
        } else {
            users.get(username).add(book);
        }
    }

    public static void returnBook(String username, String book) {
        if (!users.containsKey(username)) {
            Terminal.writeln("Такого пользователя не существует.");
        } else if (!users.get(username).contains(book)) {
            Terminal.writeln("У пользователя нет этой книги!");
        } else {
            library.put(book, library.get(book) + 1);
            users.get(username).remove(book);
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

    public static void saveToFile(String filename) {
        File file = new File(filename);
        try {
            FileWriter fileWriter = new FileWriter(file);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            bufferedWriter.write("BOOKS:");
            bufferedWriter.newLine();
            for (Map.Entry<String, Integer> entry : library.entrySet()) {
                bufferedWriter.write(entry.getKey()+": "+entry.getValue());
                bufferedWriter.newLine();
            }

            bufferedWriter.write("USERS:");
            bufferedWriter.newLine();
            for (Map.Entry<String, ArrayList<String>> entry : users.entrySet()) {
                StringJoiner stringJoiner = new StringJoiner(";");
                for (String book : entry.getValue()) {
                    stringJoiner.add(book);
                }
                bufferedWriter.write(entry.getKey()+":"+stringJoiner.toString());
                bufferedWriter.newLine();
            }
            bufferedWriter.close();
            fileWriter.close();
        } catch (IOException e) {
            Terminal.writeln("Ошибка сохранения в файл.");
        }

    }

    public static void loadFromFile(String filename) {
        library.clear();
        users.clear();
        File file = new File(filename);
        try {
            FileReader fileReader = new FileReader(file);
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            String line;
            boolean inBooks = false;
            boolean inUsers = false;

            while ((line = bufferedReader.readLine()) != null) {
                line = line.trim();
                if (line.isEmpty()) {
                    continue;
                }

                if (line.equals("BOOKS:")) {
                    inBooks = true;
                    inUsers = false;
                    continue;
                } else if (line.equals("USERS:")) {
                    inBooks = false;
                    inUsers = true;
                    continue;
                }

                if (inBooks) {
                    String[] parts = line.split(": ");
                    for (int i = 0; i < Integer.parseInt(parts[1]); i++) {
                        addBook(parts[0]);
                    }
                } else if (inUsers) {
                    String[] parts = line.split(":");
                    if (parts.length == 2) {
                        addUser(parts[0]);
                        String[] books = parts[1].split(";");
                        for (String book : books) {
                            addBookToUser(parts[0], book);
                        }
                    }
                }
            }
            bufferedReader.close();
            fileReader.close();
        } catch (FileNotFoundException e) {
            Terminal.writeln("Не удалось загрузить файл.");
        } catch (IOException e) {
            Terminal.writeln("Не удалось прочитать файл.");
        }
    }
}
