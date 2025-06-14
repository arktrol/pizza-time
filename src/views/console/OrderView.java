package views.console;

import controllers.OrderController;
import models.abstracts.AUser;
import models.enums.OrderStatus;
import models.enums.PaymentMethod;
import models.interfaces.IOrder;
import models.interfaces.IPizza;

import java.util.List;
import java.util.Objects;
import java.util.Scanner;
import java.util.stream.Collectors;

public class OrderView {
    private IOrder lastOrder;
    private final OrderController orderController;
    private final Scanner scanner;

    public OrderView(OrderController orderController) {
        this.orderController = orderController;
        this.scanner = new Scanner(System.in);
    }

    public void showBasket() {
        System.out.println("\n=== Корзина ===");
        orderController.getCurrentBasket().getPizzas().forEach(pizza ->
                System.out.printf("%s - %.2f руб.\n", pizza.getName(), pizza.getPrice())
        );
        System.out.printf("Итого: %.2f руб.\n", orderController.getCurrentBasket().calcTotalPrice());
    }

    public void addPizza(IPizza pizza){
        this.orderController.addToBasket(pizza);
    }

    public void checkout(AUser user) {
        System.out.println("\nОформление заказа...");
        IOrder order = orderController.checkout(user);
        if (order != null) {
            System.out.printf("Заказ #%s создан!\n", order.getId());
            pay(order);
        }
        this.lastOrder = order;
    }

    private void pay(IOrder order) {
        System.out.println("\n=== Оплата ===");
        System.out.println("1. Картой");
        System.out.println("2. Наличными");
        System.out.print("Выберите способ: ");

        int choice = scanner.nextInt();
        PaymentMethod method = (choice == 1) ? PaymentMethod.CARD : PaymentMethod.CASH;

        orderController.pay(order, method);
        System.out.println("✅ Оплата прошла успешно!");
    }
    public void showReadyOrders(List<IOrder> readyOrders) {
        System.out.println("\n=== Готовые к выдаче заказы ===");
        readyOrders.forEach(order ->
                System.out.printf("#%s - %s\n", order.getId(), order.getStatus())
        );
    }

    public OrderController getOrderController() { // todo инкапсуляция
        return orderController;
    }

    public void handleReadyOrder(IOrder order) {
        if(order == null) {
            System.out.println("Заказов нет");
            return;
        }
            System.out.println("\n🔔 Ваш заказ #" + order.getId() + " готов к выдаче!");
            System.out.println("1. Забрать сейчас");
            System.out.println("2. Забрать позже");
            System.out.print("Выберите действие: ");

            int choice = scanner.nextInt();
            if (choice == 1) {
                if (Objects.equals(order.getStatus(), OrderStatus.READY.toString())) {
                    orderController.receiveOrder(order);
                } else {
                    System.out.println("Заказ еще не готов!");
                }
                receiveOrder(order);
            }
    }

    private void receiveOrder(IOrder order) {
        try {
            orderController.receiveOrder(order);
            System.out.println("✅ Заказ #" + order.getId() + " получен!");
        } catch (IllegalStateException e) {
            System.out.println("⚠ Ошибка: " + e.getMessage());
        }
    }

    public IOrder getLastOrder() {
        return lastOrder;
    }

    //    public void showMyOrders() {
//        List<IOrder> orders = orderController.getReadyOrders();
//        orders.forEach(order -> {
//            System.out.printf("#%s - %s (%s)\n",
//                    order.getId(),
//                    order.getStatus(),
//                    order.getBasket().getPizzas().stream()
//                            .map(IPizza::getName)
//                            .collect(Collectors.joining(", ")));
//        });
//    }
}
