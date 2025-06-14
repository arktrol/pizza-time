package models.interfaces;

public interface IPayment {
    void markPaid();
    boolean isPaid();
    double getAmount();
    String getPaymentMethod();
}
