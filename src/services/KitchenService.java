package services;

import models.abstracts.AUser;
import models.interfaces.IOrder;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.function.Consumer;

public class KitchenService {
    private final List<IOrder> activeOrders = new ArrayList<>();
    private final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
    private final List<IOrder> readyOrders = new ArrayList<>();

    /**
     * Принять заказ в работу
     */
    public void acceptOrder(IOrder order) {
        if (order == null) throw new IllegalArgumentException("Order cannot be null");
        if (activeOrders.contains(order)) throw new IllegalStateException("Order already in progress");

        System.out.println("[Кухня] Начали готовить заказ #" + order.getId());
        order.startCooking();
        activeOrders.add(order);
    }

    /**
     * Завершить приготовление заказа
     */

    public void completeOrder(IOrder order) {
        if (!activeOrders.contains(order)) {
            throw new IllegalStateException("Order not found in active orders");
        }

        // Имитация приготовления
        scheduler.schedule(() -> {
            order.markReady();
            readyOrders.add(order);
            System.out.println("[Кухня] Заказ #" + order.getId() + " готов!");
        }, 5, TimeUnit.SECONDS);
    }

    /**
     * Получить текущие активные заказы
     */
    public List<IOrder> getActiveOrders() {
        return Collections.unmodifiableList(activeOrders);
    }

    public List<IOrder> getReadyOrders() {
        return Collections.unmodifiableList(readyOrders);
    }

    public List<IOrder> getReadyOrdersForUser(AUser user) {
        return readyOrders.stream()
                .filter(order -> order.getUser().equals(user))
                .toList();
    }
}
