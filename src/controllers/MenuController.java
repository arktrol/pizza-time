package controllers;

import models.interfaces.IMenu;
import models.interfaces.IPizza;

import java.util.List;

public class MenuController {
    private final IMenu menu;

    public MenuController(IMenu menu) {
        this.menu = menu;
    }

    public List<IPizza> getAvailablePizzas() {
        return menu.getAvailablePizzas();
    }

    public IPizza findPizzaByName(String name) {
        return menu.getAvailablePizzas().stream()
                .filter(p -> p.getName().equalsIgnoreCase(name))
                .findFirst()
                .orElse(null);
    }
}