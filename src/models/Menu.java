package models;

import models.interfaces.IMenu;
import models.interfaces.IPizza;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Menu implements IMenu {
    private List<IPizza> availablePizzas = new ArrayList<>();

    public Menu(List<IPizza> availablePizzas) {
        this.availablePizzas = Objects.requireNonNull(availablePizzas, "Список пицц = null") ;
    }

    public List<IPizza> getAvailablePizzas() {
        return new ArrayList<>(this.availablePizzas);
    }

    @Override
    public String toString() {
        return "Menu{" +
                "availablePizzas=" + availablePizzas +
                '}';
    }
}
