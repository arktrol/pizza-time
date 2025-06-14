package views.console;

import models.abstracts.AUser;
import models.interfaces.IPizza;

import java.util.Scanner;

public class MainView {
    private final AuthView authView;
    private final MenuView menuView;
    private final OrderView orderView;

    public MainView(AuthView authView, MenuView menuView, OrderView orderView) {
        this.authView = authView;
        this.menuView = menuView;
        this.orderView = orderView;
    }

//    private void addAdminMenu() {
//        System.out.println("\n=== Административное меню ===");
//        System.out.println("5. Список готовых заказов");
//        System.out.println("6. Выдать заказ");
//    }
    private void showMainMenu(){
        System.out.println("\n=== Главное меню ===");
        System.out.println("1. Показать меню");
        System.out.println("2. Добавить в корзину");
        System.out.println("3. Корзина");
        System.out.println("4. Оформить заказ");
        System.out.println("5. Список готовых заказов");
        System.out.println("0. Выход");
        System.out.print("Выберите действие: ");
    }

    public void run() {
        System.out.println("🍕 Добро пожаловать в пиццерию!");
        AUser user = authView.showAuthMenu();
        if (user == null) return;

        while (true) {
            showMainMenu();

            Scanner scanner = new Scanner(System.in);
            int choice = scanner.nextInt();

//            addAdminMenu();
            switch (choice) {
                case 1 -> menuView.showMenu();
                case 2 -> {
                    IPizza pizza = menuView.selectPizza();
                    if (pizza != null) {
                        orderView.addPizza(pizza);
                        System.out.println("✅ Пицца добавлена в корзину!");
                    } else {
                        System.out.println("⚠ Пицца не найдена!");
                    }
                }
                case 3 -> orderView.showBasket();
                case 4 -> orderView.checkout(user);
                case 5 -> orderView.handleReadyOrder(orderView.getLastOrder());
                case 0 -> {
                    System.out.println("До свидания!");
                    return;
                }
                default -> System.out.println("⚠ Неверный выбор!");
            }
        }
    }
}
