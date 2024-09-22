package com.github.fermelin.restaurants.util;

import com.github.fermelin.restaurants.model.Dish;
import com.github.fermelin.restaurants.model.Menu;
import com.github.fermelin.restaurants.model.Restaurant;
import lombok.experimental.UtilityClass;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@UtilityClass
public class MenuUtil {

    public static Menu createMenu(Restaurant restaurant, List<Dish> dishes, LocalDate menuDate) {
        List<Dish> menus = createDishesInMenu(dishes);
        return new Menu(null, menuDate, restaurant, menus);
    }

    public static List<Dish> createDishesInMenu(List<Dish> dishes) {
        List<Dish> menus = new ArrayList<>();
        dishes.forEach(dish -> menus.add(new Dish(dish)));
        return menus;
    }
}
