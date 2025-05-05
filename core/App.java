package core;

import java.util.*;

public class App {
    public static void run() {
        Scanner scanner = new Scanner(System.in);
        List<String> library = new ArrayList<>();
        Map<String, ArrayList<String>> users = new HashMap<>();

        while (true) {
            Terminal.writeln("\n0. Выход");
            Terminal.writeln("1. Добавление книги");
            Terminal.writeln("2. Удаление книги");
            Terminal.writeln("3. Поиск книги");
            Terminal.writeln("4. Вывести все книги");
            Terminal.writeln("5. Добавление пользователя");
            Terminal.writeln("6. Выдача книги");
            Terminal.writeln("7. Возврат книги");
            Terminal.writeln("8. Просмотр книг пользователя");
            Terminal.writeln("9. Удаление пользователя");
            Terminal.writeln("10. Загрузить информацию в файл");
            Terminal.writeln("11. Считать информацию из файла");
            Terminal.write("\nВыберите действие: ");
            int action = -1;
            if (scanner.hasNextInt()) {
                action = scanner.nextInt();
            } else {
                scanner.nextLine();
            }
            scanner.nextLine();

            switch (action) {
                case 0:
                    return;
                case 1: {
                    String title = userInput(scanner, "Введите название книги, которую хотите добавить");
                    BookManager.addBook(title);
                    break;
                }
                case 2: {
                    String title = userInput(scanner, "Введите название книги, которую хотите удалить");
                    BookManager.removeBook(title);
                    break;
                }
                case 3: {
                    String query = userInput(scanner, "Введите запрос для поиска");
                    BookManager.searchBook(query);
                    break;
                }
                case 4: {
                    BookManager.displayAllBooks();
                    break;
                }
                case 5: {
                    String username = userInput(scanner, "Введите имя пользователя, которого хотите добавить");
                    BookManager.addUser(username);
                    break;
                }
                case 6: {
                    String username = userInput(scanner, "Введите имя пользователя");
                    String title = userInput(scanner, "Введите название книги, которую хотите выдать");
                    BookManager.borrowBook(username, title);
                    break;
                }
                case 7: {
                    String username = userInput(scanner, "Введите имя пользователя");
                    String title = userInput(scanner, "Введите название книги, которую хотите выдать");
                    BookManager.returnBook(username, title);
                    break;
                }
                case 8: {
                    String username = userInput(scanner, "Введите имя пользователя");
                    BookManager.displayUserBooks(username);
                    break;
                }
                case 9: {
                    String username = userInput(scanner, "Введите имя пользователя");
                    BookManager.removeUser(username);
                    break;
                }
                case 10: {
                    String filename = userInput(scanner, "Введите название файла");
                    BookManager.saveToFile(filename);
                    break;
                }
                case 11: {
                    String filename = userInput(scanner, "Введите название файла");
                    BookManager.loadFromFile(filename);
                    break;
                }
                default:
                    Terminal.writeln("Нет такого действия. Проверьте ввод.");
            }
        }
    }

    public static String userInput(Scanner scanner, String output) {
        System.out.printf("%s: ", output);
        return scanner.nextLine();
    }
}
