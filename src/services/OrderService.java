package services;

import models.Order;
import models.Payment;
import models.abstracts.AUser;
import models.enums.PaymentMethod;
import models.interfaces.IBasket;
import models.interfaces.IOrder;
import models.interfaces.IPayment;

import java.util.Collections;
import java.util.List;

public class OrderService {
    private final PaymentService paymentService;
    private final KitchenService kitchenService;

    public OrderService(PaymentService paymentService, KitchenService kitchenService) {
        this.paymentService = paymentService;
        this.kitchenService = kitchenService;
    }

    /**
     * @throws IllegalStateException если пользователь не авторизован или корзина пуста
     */
    public IOrder createOrder(AUser user, IBasket basket) {
        if (basket.getPizzas().isEmpty()) {
            throw new IllegalStateException("Basket is empty");
        }

        return new Order(user, basket);


    }

    public void toPay(IOrder order, PaymentMethod method){
        IPayment payment = new Payment(order, method);

        if (paymentService.processPayment(payment)) {
            order.markPaid();
            // Запускаем процесс готовки сразу после создания заказа
            kitchenService.acceptOrder(order);  // Статус -> "Готовится"
            kitchenService.completeOrder(order); // Через 5 сек -> "Готов"
        } else {
            throw new RuntimeException("Payment failed");
        }
    }

    public IOrder findOrderById(String orderId){

        return null;
    }

    public void completeOrder(IOrder order) {
        kitchenService.completeOrder(order); // Помечаем готовым на кухне
        order.issue(); // Меняем статус на "выдан"
    }

    public List<IOrder> getReadyOrders() {
        return kitchenService.getReadyOrders();
    }

    public KitchenService getKitchenService() {
        return kitchenService;
    }
}
