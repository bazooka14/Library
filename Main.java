import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<String> library = new ArrayList<>();
        Map<String, ArrayList<String>> users = new HashMap<>();

        while(true) {
            System.out.println("\n0. Выход");
            System.out.println("1. Добавление книги");
            System.out.println("2. Удаление книги");
            System.out.println("3. Поиск книги");
            System.out.println("4. Добавление пользователя");
            System.out.println("5. Выдача книги");
            System.out.println("6. Просмотр книг пользователя");
            System.out.print("\nВыберите действие: ");
            int action = scanner.nextInt();
            scanner.nextLine();

            switch (action) {
                case 0:
                    return;
                case 1: {
                    String title = userInput(scanner, "Введите название книги, которую хотите добавить");
                    addBookToLibrary(library, title);
                    break;
                }
                case 2: {
                    String title = userInput(scanner, "Введите название книги, которую хотите удалить");
                    deleteBookFromLibrary(library, title);
                    break;
                }
                case 3: {
                    String query = userInput(scanner, "Введите запрос для поиска");
                    searchBookInLibrary(library, query);
                    break;
                }
                case 4: {
                    String username = userInput(scanner, "Введите имя пользователя, которого хотите добавить");
                    addUser(users, username);
                    break;
                }
                case 5: {
                    String username = userInput(scanner, "Введите имя пользователя");
                    String title = userInput(scanner, "Введите название книги, которую хотите выдать");
                    giveBookToUser(users, username, library, title);
                    break;
                }
                case 6: {
                    String username = userInput(scanner, "Введите имя пользователя");
                    listAllUserBooks(users, username);
                    break;
                }
            }
        }
    }

    public static String userInput(Scanner scanner, String output) {
        System.out.printf("%s: ", output);
        return scanner.nextLine();
    }

    public static void addBookToLibrary(List<String> library, String title) {
        if (!library.contains(title)) {
            library.add(title);
        } else {
            System.out.println("Такая книга уже есть");
        }
    }

    public static void deleteBookFromLibrary(List<String> library, String title) {
        if (library.contains(title)) {
            library.remove(title);
        } else {
            System.out.println("Такой книги нет. Проверьте ввод.");
        }
    }

    public static void searchBookInLibrary(List<String> library, String query) {
        for (String book : library) {
            if (book.toLowerCase().contains(query.toLowerCase())) {
                System.out.println(book);
            }
        }
    }

    public static void addUser(Map<String, ArrayList<String>> users, String username) {
        if (!users.containsKey(username)) {
            users.put(username, new ArrayList<>());
        } else {
            System.out.println("Такой пользователь уже существует.");
        }
    }

    public static void giveBookToUser(Map<String, ArrayList<String>> users, String username,
                                      List<String> library, String title) {
        if (library.contains(title) && users.containsKey(username)) {
            users.get(username).add(title);
        } else {
            System.out.println("Нет такого пользователя или книги. Проверьте ввод.");
        }
    }

    public static void listAllUserBooks(Map<String, ArrayList<String>> users, String username) {
        if (users.containsKey(username)) {
            System.out.println(users.get(username));
        } else {
            System.out.println("Нет такого пользователя. Проверьте ввод.");
        }
    }
}