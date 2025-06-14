import controllers.AuthController;
import controllers.MenuController;
import controllers.OrderController;
import models.interfaces.IMenu;
import models.*;
import services.AuthService;
import services.KitchenService;
import services.OrderService;
import services.PaymentService;
import views.console.AuthView;
import views.console.MainView;
import views.console.MenuView;
import views.console.OrderView;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        // 1. Инициализация сервисов
        AuthService authService = new AuthService();
        PaymentService paymentService = new PaymentService();
        KitchenService kitchenService = new KitchenService();
        OrderService orderService = new OrderService(paymentService, kitchenService);

        // 2. Создаем тестовые данные
        IMenu menu = new Menu(List.of(
                new Pizza("Маргарита", 550),
                new Pizza("Пепперони", 650)
        ));
        // Контроллеры
        AuthController authController = new AuthController(authService);
        MenuController menuController = new MenuController(menu);
        OrderController orderController = new OrderController(orderService);

        authController.register("Толя", "123", "777");

        // View
        AuthView authView = new AuthView(authController);
        MenuView menuView = new MenuView(menuController);
        OrderView orderView = new OrderView(orderController);
        MainView mainView = new MainView(authView, menuView, orderView);

        // Запуск
        mainView.run();
    }
}