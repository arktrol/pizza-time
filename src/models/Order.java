package models;

import models.enums.OrderStatus;
import models.abstracts.AUser;
import models.interfaces.IBasket;
import models.interfaces.IOrder;
import models.interfaces.IPayment;

import java.util.Objects;
import java.util.Random;

public class Order implements IOrder {
    private final String id;
    private final AUser user;
    private final IBasket basket;
    private OrderStatus status;
    private IPayment payment;
    private boolean isPaid;

    public Order(AUser user, IBasket basket) {
        this.user = Objects.requireNonNull(user);
        this.basket = Objects.requireNonNull(basket);
        this.status = OrderStatus.RECEIVED;
        this.isPaid = false;

        long currentTimeMillis = System.currentTimeMillis();
        int randomNum = new Random().nextInt(1000); // Случайное число от 0 до 999
        this.id = currentTimeMillis + "-" + randomNum;
    }

    public void setPayment(IPayment payment) {
        this.payment = payment;
    }

    public void processPayment() {
        if (payment == null) throw new IllegalStateException("Платеж не инициализирован");
        payment.markPaid();
        this.status = OrderStatus.PAID;
        this.isPaid = this.payment.isPaid();
    }

    public void markPaid(){
        this.isPaid = true;
        this.status = OrderStatus.PAID;
    }

    public void startCooking() {
        if(!isPaid) {
            throw new IllegalStateException("Заказ не оплачен!");
        }
        if (status != OrderStatus.PAID) {
            throw new IllegalStateException("Заказ уже в процессе!");
        }

        status = OrderStatus.COOKING;
    }

    public void markReady() {
        if (status != OrderStatus.COOKING) {
            throw new IllegalStateException("Заказ не готовится!");
        }
        status = OrderStatus.READY;
    }

    public void issue(){
        if(status != OrderStatus.READY){
            throw new IllegalStateException("Заказ еще не готов!");
        }
        status = OrderStatus.ISSUED;
    }

    public boolean isPaid(){
        return this.isPaid;
    }

    public String getId() {
        return id;
    }

    public AUser getUser() {
        return user;
    }

    public IBasket getBasket() {
        return new Basket(this.basket);
    }

    public String getStatus() {
        return status.toString();
    }

    @Override
    public String toString() {
        return "Order{" +
                "user=" + user +
                ", basket=" + basket +
                ", status='" + status + '\'' +
                '}';
    }
}
