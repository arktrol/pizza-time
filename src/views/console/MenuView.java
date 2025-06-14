package views.console;

import controllers.MenuController;
import models.interfaces.IPizza;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class MenuView {
    private final MenuController menuController;

    public MenuView(MenuController menuController) {
        this.menuController = menuController;
    }

    public void showMenu() {
        System.out.println("\n=== Меню ===");
        menuController.getAvailablePizzas().forEach(pizza ->
                System.out.printf("%s - %.2f руб.\n", pizza.getName(), pizza.getPrice())
        );
    }

    public IPizza selectPizza() {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\n=== Выбор пиццы ===");
            System.out.println("0. Назад");

            // Нумерованный список пицц
            List<IPizza> pizzas = menuController.getAvailablePizzas();
            for (int i = 0; i < pizzas.size(); i++) {
                System.out.printf("%d. %s - %.2f руб.\n",
                        i + 1,
                        pizzas.get(i).getName(),
                        pizzas.get(i).getPrice());
            }

            System.out.print("Выберите номер пиццы: ");

            try {
                int choice = scanner.nextInt();
                scanner.nextLine(); // Очистка буфера

                if (choice == 0) return null;
                if (choice > 0 && choice <= pizzas.size()) {
                    return pizzas.get(choice - 1);
                }
                System.out.println("⚠ Неверный номер!");

            } catch (InputMismatchException e) {
                System.out.println("⚠ Введите число!");
                scanner.nextLine(); // Очистка некорректного ввода
            }
        }
    }
}
