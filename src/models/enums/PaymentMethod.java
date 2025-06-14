package models.enums;

public enum PaymentMethod {
    CASH("CASH"),
    CARD("CARD"),
    PROMO("PROMO");

    private final String description;

    PaymentMethod(String description){
        this.description = description;
    }

    @Override
    public String toString() {
        return this.description;
    }
}
