package models.interfaces;

import models.abstracts.AUser;

public interface IOrder {
    void setPayment(IPayment payment);
    void processPayment();
    void markPaid();
    void startCooking();
    void markReady();
    void issue();
    boolean isPaid();
    AUser getUser();
    String getId();
    IBasket getBasket();
    String getStatus();
}
