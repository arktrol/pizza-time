package models;

import models.enums.PaymentMethod;
import models.interfaces.IOrder;
import models.interfaces.IPayment;

import java.util.Objects;

public class Payment implements IPayment {
    private final IOrder order;
    private double amount;
    private boolean isPaid;
    private PaymentMethod paymentMethod; // "CASH", "CARD" и т.д.

    public Payment(IOrder order, PaymentMethod method)  {
        this.order = Objects.requireNonNull(order);
        this.amount = this.order.getBasket().calcTotalPrice();
        try {
            this.paymentMethod = method;
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Invalid payment method: " + paymentMethod);
        }
        this.isPaid = false;
    }

    public void markPaid() {
        if (isPaid) {
            throw new IllegalStateException("Платеж уже проведен!");
        }
        // Здесь могла бы быть интеграция с платежной системой
        this.isPaid = true;
        System.out.println("Оплачено " + amount + " руб. (" + paymentMethod + ")"); // todo перенести во view
    }

    // Геттеры
    public boolean isPaid() { return isPaid; }
    public double getAmount() { return amount; }
    public String getPaymentMethod() { return paymentMethod.toString(); }
}
