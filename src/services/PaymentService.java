package services;

import models.interfaces.IPayment;

public class PaymentService {
    /**
     * @return true, если платеж успешен
     * @throws IllegalStateException если заказ уже оплачен
     */
    public boolean processPayment(IPayment payment) {
        if (payment == null) throw new IllegalArgumentException("Payment cannot be null");
        if (payment.isPaid()) throw new IllegalStateException("Payment already processed");

        // Здесь может быть интеграция с платежным шлюзом
        boolean paymentSuccess = simulatePayment(payment.getAmount());

        if (paymentSuccess) {
            payment.markPaid();
            return true;
        }
        return false;
    }

    // Тестовая заглушка
    private boolean simulatePayment(double amount) {
        return amount > 0; // Все платежи > 0 проходят
    }
}
