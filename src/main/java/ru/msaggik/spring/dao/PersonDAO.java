package ru.msaggik.spring.dao;

import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import ru.msaggik.spring.models.Person;

import javax.persistence.EntityManager;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Component
public class PersonDAO {

    private final EntityManager entityManager;

    @Autowired
    public PersonDAO(EntityManager entityManager) {
        this.entityManager = entityManager;
    }


    @Transactional(readOnly = true)
    public void testNPlusOne() {
        // получение Hibernate сессии
        Session session = entityManager.unwrap(Session.class);

//        // 1 запрос получения данных всех пользователей:
//        List<Person> people = session.createQuery("select p from Person p", Person.class)
//                .getResultList();
//        // N запросов к БД (вывод в консоль товаров всех пользователей)
//        for(Person person: people)
//            System.out.println("Person " + person.getName() + " has: " + person.getItems());
        // Решение (1 запрос к БД)
        // SQL: A LEFT JOIN B -> Результирующая объединённая таблица
//        // Вариант 1 (с повторениями пользователей)
//        List<Person> people = session.createQuery("select p from Person p LEFT JOIN FETCH p.items")
//                .getResultList();
        // Вариант 2 (без повторения пользователей)
        Set<Person> people = new HashSet<Person>(session.createQuery("select p from Person p LEFT JOIN FETCH p.items")
                .getResultList());
        for(Person person: people)
            System.out.println("Person " + person.getName() + " has: " + person.getItems());
    }
}
