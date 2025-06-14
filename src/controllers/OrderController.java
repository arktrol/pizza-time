package controllers;

import models.Basket;
import models.abstracts.AUser;
import models.enums.OrderStatus;
import models.enums.PaymentMethod;
import models.interfaces.IBasket;
import models.interfaces.IOrder;
import models.interfaces.IPizza;
import services.OrderService;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class OrderController {
    private final OrderService orderService;
    private final IBasket currentBasket;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
        this.currentBasket = new Basket();
    }

    public void addToBasket(IPizza pizza) {
        currentBasket.addPizza(pizza);
    }

    public IBasket getCurrentBasket() {
        return currentBasket;
    }

    public OrderService getOrderService() { // todo инкапсуляция
        return orderService;
    }

    /**
     * @return созданный заказ или null при ошибке
     */
    public IOrder checkout(AUser user) {
        try {

            return orderService.createOrder(user, currentBasket);
        } catch (Exception e) {
            System.err.println("Ошибка оформления: " + e.getMessage());
            return null;
        }
    }
    public  void pay(IOrder order, PaymentMethod method){
        if(order == null || method == null) throw new IllegalStateException("Неверные параметры");
        try{
            this.orderService.toPay(order, method);
        } catch (Exception e){
            System.err.println("Ошибка оплаты: " + e.getMessage());
        }
    }

    public boolean receiveOrder(IOrder order) {
        try {
            if (Objects.equals(order.getStatus(), OrderStatus.READY.toString())) {
                order.issue(); // Меняем статус на "Выдан"
                return true;
            }
            throw new IllegalStateException("Заказ еще не готов!");
        } catch (Exception e) {
            System.err.println("Ошибка: " + e.getMessage());
            return false;
        }
    }

    public List<IOrder> getReadyOrders() {
        return orderService.getReadyOrders();
    }
}
