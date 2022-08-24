package ru.msaggik.spring.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.msaggik.spring.models.Item;
import ru.msaggik.spring.models.Person;
import ru.msaggik.spring.repositories.ItemsRepository;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class ItemService {
    private final ItemsRepository itemsRepository;

    @Autowired
    public ItemService(ItemsRepository itemsRepository) {
        this.itemsRepository = itemsRepository;
    }
    // поиск товара по его названию
    public List<Item> findByItemName(String itemName) {
        return itemsRepository.findByItemName(itemName);
    }
    // поиск товара по его владельцу
    public List<Item> findByOwner(Person owner) {
        return itemsRepository.findByOwner(owner);
    }
}
