package models.interfaces;

import models.Pizza;

import java.util.List;

public interface IMenu {
    List<IPizza> getAvailablePizzas();
}
