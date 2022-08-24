package ru.msaggik.spring.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.msaggik.spring.models.Item;
import ru.msaggik.spring.models.Person;

import java.util.List;

@Repository
public interface ItemsRepository extends JpaRepository<Item, Integer> {
    // кастомные методы:
    // 1) метод поиска товара по его владельцу
    // (данный метод аналогичен методу person.getItems())
    List<Item> findByOwner(Person owner);
    // 2) метод поиска товара по его названию
    List<Item> findByItemName(String itemName);

}
