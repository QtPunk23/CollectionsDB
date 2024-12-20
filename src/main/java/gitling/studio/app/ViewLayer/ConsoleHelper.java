package gitling.studio.app.ViewLayer;



import gitling.studio.app.RepositoryLayer.CategoryRepository;
import gitling.studio.app.RepositoryLayer.DiscRepository;
import gitling.studio.app.RepositoryLayer.MediaTypeRepository;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Scanner;

public class ConsoleHelper {
    private final DiscRepository discRepository;
    private final CategoryRepository categoryRepository;
    private final MediaTypeRepository mediaTypeRepository;
    private final Scanner scanner;

    public ConsoleHelper(Connection connection) {
        this.discRepository = new DiscRepository(connection);
        this.categoryRepository = new CategoryRepository(connection);
        this.mediaTypeRepository = new MediaTypeRepository(connection);
        this.scanner = new Scanner(System.in);
    }

    public void start() {
        while (true) {
            System.out.println("Выберите действие:");
            System.out.println("1. Работа с дисками");
            System.out.println("2. Работа с категориями");
            System.out.println("3. Работа с типами носителей");
            System.out.println("0. Выход");
            int choice = scanner.nextInt();
            scanner.nextLine(); // consume newline

            switch (choice) {
                case 1 -> discMenu();
                case 2 -> categoryMenu();
                case 3 -> mediaTypeMenu();
                case 0 -> {
                    System.out.println("Завершение работы.");
                    return;
                }
                default -> System.out.println("Неверный выбор, попробуйте снова.");
            }
        }
    }

    private void discMenu() {
        while (true) {
            System.out.println("Работа с дисками:");
            System.out.println("1. Добавить диск");
            System.out.println("2. Просмотреть диски");
            System.out.println("3. Обновить диск");
            System.out.println("4. Удалить диск");
            System.out.println("0. Назад");
            int choice = scanner.nextInt();
            scanner.nextLine(); // consume newline

            switch (choice) {
                case 1 -> createDisc();
                case 2 -> readDiscs();
                case 3 -> updateDisc();
                case 4 -> deleteDisc();
                case 0 -> {
                    return;
                }
                default -> System.out.println("Неверный выбор, попробуйте снова.");
            }
        }
    }

    private void createDisc() {
        System.out.println("Введите название диска:");
        String title = scanner.nextLine();
        System.out.println("Введите ID типа носителя:");
        long mediaTypeId = scanner.nextLong();
        scanner.nextLine(); // consume newline
        System.out.println("Введите описание:");
        String description = scanner.nextLine();
        try {
            discRepository.createDisc(title, mediaTypeId, description);
            System.out.println("Диск добавлен.");
        } catch (SQLException e) {
            System.out.println("Ошибка при добавлении диска: " + e.getMessage());
        }
    }

    private void readDiscs() {
        try {
            discRepository.readDiscs().forEach(System.out::println);
        } catch (SQLException e) {
            System.out.println("Ошибка при чтении дисков: " + e.getMessage());
        }
    }

    private void updateDisc() {
        System.out.println("Введите ID диска для обновления:");
        long discId = scanner.nextLong();
        scanner.nextLine(); // consume newline
        System.out.println("Введите новое название:");
        String newTitle = scanner.nextLine();
        System.out.println("Введите новый ID типа носителя:");
        long newMediaTypeId = scanner.nextLong();
        scanner.nextLine(); // consume newline
        System.out.println("Введите новое описание:");
        String newDescription = scanner.nextLine();
        try {
            discRepository.updateDisc(discId, newTitle, newMediaTypeId, newDescription);
            System.out.println("Диск обновлен.");
        } catch (SQLException e) {
            System.out.println("Ошибка при обновлении диска: " + e.getMessage());
        }
    }

    private void deleteDisc() {
        System.out.println("Введите ID диска для удаления:");
        long discId = scanner.nextLong();
        scanner.nextLine(); // consume newline
        try {
            discRepository.deleteDisc(discId);
            System.out.println("Диск удален.");
        } catch (SQLException e) {
            System.out.println("Ошибка при удалении диска: " + e.getMessage());
        }
    }

    private void categoryMenu() {
        while (true) {
            System.out.println("Работа с категориями:");
            System.out.println("1. Добавить категорию");
            System.out.println("2. Просмотреть категории");
            System.out.println("3. Обновить категорию");
            System.out.println("4. Удалить категорию");
            System.out.println("0. Назад");
            int choice = scanner.nextInt();
            scanner.nextLine(); // consume newline

            switch (choice) {
                case 1 -> createCategory();
                case 2 -> readCategories();
                case 3 -> updateCategory();
                case 4 -> deleteCategory();
                case 0 -> {
                    return;
                }
                default -> System.out.println("Неверный выбор, попробуйте снова.");
            }
        }
    }

    private void createCategory() {
        System.out.println("Введите название категории:");
        String name = scanner.nextLine();
        try {
            categoryRepository.createCategory(name);
            System.out.println("Категория добавлена.");
        } catch (SQLException e) {
            System.out.println("Ошибка при добавлении категории: " + e.getMessage());
        }
    }

    private void readCategories() {
        try {
            categoryRepository.readCategories().forEach(System.out::println);
        } catch (SQLException e) {
            System.out.println("Ошибка при чтении категорий: " + e.getMessage());
        }
    }

    private void updateCategory() {
        System.out.println("Введите ID категории для обновления:");
        long categoryId = scanner.nextLong();
        scanner.nextLine(); // consume newline
        System.out.println("Введите новое название категории:");
        String newName = scanner.nextLine();
        try {
            categoryRepository.updateCategory(categoryId, newName);
            System.out.println("Категория обновлена.");
        } catch (SQLException e) {
            System.out.println("Ошибка при обновлении категории: " + e.getMessage());
        }
    }

    private void deleteCategory() {
        System.out.println("Введите ID категории для удаления:");
        long categoryId = scanner.nextLong();
        scanner.nextLine(); // consume newline
        try {
            categoryRepository.deleteCategory(categoryId);
            System.out.println("Категория удалена.");
        } catch (SQLException e) {
            System.out.println("Ошибка при удалении категории: " + e.getMessage());
        }
    }


    private void mediaTypeMenu() {
        while (true) {
            System.out.println("Работа с типами носителей:");
            System.out.println("1. Добавить тип носителя");
            System.out.println("2. Просмотреть типы носителей");
            System.out.println("3. Обновить тип носителя");
            System.out.println("4. Удалить тип носителя");
            System.out.println("0. Назад");
            int choice = scanner.nextInt();
            scanner.nextLine(); // consume newline

            switch (choice) {
                case 1 -> createMediaType();
                case 2 -> readMediaTypes();
                case 3 -> updateMediaType();
                case 4 -> deleteMediaType();
                case 0 -> {
                    return;
                }
                default -> System.out.println("Неверный выбор, попробуйте снова.");
            }
        }
    }

    private void createMediaType() {
        System.out.println("Введите название типа носителя:");
        String name = scanner.nextLine();
        try {
            mediaTypeRepository.createMediaType(name);
            System.out.println("Тип носителя добавлен.");
        } catch (SQLException e) {
            System.out.println("Ошибка при добавлении типа носителя: " + e.getMessage());
        }
    }

    private void readMediaTypes() {
        try {
            mediaTypeRepository.readMediaTypes().forEach(System.out::println);
        } catch (SQLException e) {
            System.out.println("Ошибка при чтении типов носителей: " + e.getMessage());
        }
    }

    private void updateMediaType() {
        System.out.println("Введите ID типа носителя для обновления:");
        long mediaTypeId = scanner.nextLong();
        scanner.nextLine(); // consume newline
        System.out.println("Введите новое название типа носителя:");
        String newName = scanner.nextLine();
        try {
            mediaTypeRepository.updateMediaType(mediaTypeId, newName);
            System.out.println("Тип носителя обновлен.");
        } catch (SQLException e) {
            System.out.println("Ошибка при обновлении типа носителя: " + e.getMessage());
        }
    }

    private void deleteMediaType() {
        System.out.println("Введите ID типа носителя для удаления:");
        long mediaTypeId = scanner.nextLong();
        scanner.nextLine(); // consume newline
        try {
            mediaTypeRepository.deleteMediaType(mediaTypeId);
            System.out.println("Тип носителя удален.");
        } catch (SQLException e) {
            System.out.println("Ошибка при удалении типа носителя: " + e.getMessage());
        }
    }

}
