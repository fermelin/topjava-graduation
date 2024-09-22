package com.github.fermelin.restaurants.service;

import com.github.fermelin.restaurants.exception.DataConflictException;
import com.github.fermelin.restaurants.model.Dish;
import com.github.fermelin.restaurants.model.Menu;
import com.github.fermelin.restaurants.repository.DishRepository;
import com.github.fermelin.restaurants.repository.MenuRepository;
import com.github.fermelin.restaurants.repository.RestaurantRepository;
import com.github.fermelin.restaurants.to.MenuTo;
import com.github.fermelin.restaurants.util.MenuUtil;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@AllArgsConstructor
public class MenuService {
    private final MenuRepository menuRepository;
    private final DishRepository dishRepository;
    private final RestaurantRepository restaurantRepository;

    @Transactional
    public void update(MenuTo menuTo, int restId, int menuId) {
        Menu menu = menuRepository.checkBelong(menuId, restId);
        List<Dish> createdDishesInMenu = MenuUtil.createDishesInMenu(dishRepository.findAllById(getIds(menuTo,
                                                                                                       restId)));
        List<Dish> dishesInMenu = menu.getDishesInMenu();
        dishesInMenu.clear();
        menuRepository.flush();
        dishesInMenu.addAll(createdDishesInMenu);
    }

    @Transactional
    public Menu create(MenuTo menuTo, int restId, LocalDate menuDate) {
        return menuRepository.save(MenuUtil.createMenu(restaurantRepository.checkExistence(restId),
                                                       dishRepository.findAllById(getIds(menuTo, restId)),
                                                       menuDate));
    }

    public Set<Integer> getIds(MenuTo menuTo, int restId) {
        Set<Integer> dishIds = new HashSet<>(menuTo.getDishIds());
        Set<Integer> idsRepository = dishRepository.getAllIds(restId, dishIds);
        dishIds.removeAll(idsRepository);
        if (dishIds.size() != 0) {
            throw new DataConflictException("Dishes with ids=" + dishIds + " doesn't belong to Restaurant id=" + restId);
        }
        return idsRepository;
    }
}