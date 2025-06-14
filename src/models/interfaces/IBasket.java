package models.interfaces;

import models.Pizza;

import java.util.List;

public interface IBasket {
    void addPizza(IPizza pizza);

    void removePizza(IPizza pizza);

    List<IPizza> getPizzas() ;

    double calcTotalPrice();
}
