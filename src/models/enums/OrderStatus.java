package models.enums;

public enum OrderStatus {
    RECEIVED("принят"),
    PAID("оплачен"),
    COOKING("готовят"),
    READY("готов"),
    ISSUED("выдан");

    private final String description;

    OrderStatus(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return description;
    }
}
