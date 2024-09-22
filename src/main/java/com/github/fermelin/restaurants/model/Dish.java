package com.github.fermelin.restaurants.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;
import java.util.Set;

@Entity
@Table(name = "dish", uniqueConstraints = {@UniqueConstraint(name = "dish_unique_rest_id_name_idx", columnNames = {"rest_id", "name"})})
@Getter
@Setter
@NoArgsConstructor
@ToString
public class Dish extends NamedEntity {

    @ManyToMany(mappedBy = "dishesInMenu")
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    Set<Menu> menus;

    @Column(name = "price", nullable = false)
    @NotNull
    @PositiveOrZero
    private int price;
    @Column(name = "rest_id", nullable = false)
    @JsonIgnore
    private int restId;

    public Dish(Dish dish) {
        this(dish.getId(), dish.getName(), dish.getPrice(), dish.getRestId());
    }

    public Dish(Integer id, String name, int price, int restId) {
        super(id, name);
        this.price = price;
        this.restId = restId;
    }
}
