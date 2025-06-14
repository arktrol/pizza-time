package models;

import models.interfaces.IBasket;
import models.interfaces.IPizza;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Basket implements IBasket {
    private List<IPizza> selectedPizzas = new ArrayList<>();

    public Basket(){
        this(new ArrayList<>());
    }
    public Basket(IBasket basket){
        this(Objects.requireNonNull(basket.getPizzas(), "Корзина = null") );
    }
    public Basket(List<IPizza> pizzas){
        this.selectedPizzas = new ArrayList<>(Objects.requireNonNull(pizzas,"Список пицц = null"));
    }

    public void addPizza(IPizza pizza){
        this.selectedPizzas.add(Objects.requireNonNull(pizza, "Pizza cannot be null"));
    }

    public void removePizza(IPizza pizza){
        if (!this.selectedPizzas.contains(Objects.requireNonNull(pizza, "Pizza cannot be null"))) {
            throw new IllegalArgumentException("Пиццы нет в списке");
        }
        this.selectedPizzas.remove(Objects.requireNonNull(pizza, "Pizza cannot be null"));
    }

    public List<IPizza> getPizzas() {
        return  new ArrayList<>(this.selectedPizzas);
    }

    public double calcTotalPrice(){
        double temp = 0;
        if(this.selectedPizzas.isEmpty()) return temp;
        for(IPizza pizza : this.selectedPizzas){
            temp += pizza.getPrice();
        }
        return temp;
    }

    @Override
    public String toString() {
        return "Basket{" +
                "pizzas=" + selectedPizzas +
                '}';
    }
}
