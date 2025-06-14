package views.console;

import controllers.AuthController;
import models.abstracts.AUser;

import java.util.Scanner;

public class AuthView {
    private final AuthController authController;
    private final Scanner scanner;

    public AuthView(AuthController authController) {
        this.authController = authController;
        this.scanner = new Scanner(System.in);
    }

    public AUser showAuthMenu() {
        while (true) {
            System.out.println("\n=== Авторизация ===");
            System.out.println("1. Войти");
            System.out.println("2. Зарегистрироваться");
            System.out.print("Выберите действие: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Сброс буфера

            switch (choice) {
                case 1 -> {
                    System.out.print("Телефон: ");
                    String phone = scanner.nextLine();
                    System.out.print("Пароль: ");
                    String password = scanner.nextLine();
                    return authController.login(phone, password);
                }
                case 2 -> {
                    System.out.print("Имя: ");
                    String name = scanner.nextLine();
                    System.out.print("Телефон: ");
                    String phone = scanner.nextLine();
                    System.out.print("Пароль: ");
                    String password = scanner.nextLine();
                    return authController.register(name, phone, password);
                }
                default -> System.out.println("⚠ Неверный выбор!");
            }
        }
    }
}
