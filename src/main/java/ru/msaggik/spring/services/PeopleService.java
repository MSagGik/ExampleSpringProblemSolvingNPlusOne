package ru.msaggik.spring.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.msaggik.spring.models.Person;
import ru.msaggik.spring.repositories.PeopleRepository;

import java.util.List;
import java.util.Optional;


@Service
@Transactional(readOnly = true) // внутри класса режим чтения можно изменять на обычный режим изменения
public class PeopleService {
    // внедрение репозитория
    private final PeopleRepository peopleRepository;
    @Autowired
    public PeopleService(PeopleRepository peopleRepository) {
        this.peopleRepository = peopleRepository;
    }

    // метод findAll() возвращает все сущности из таблицы БД
    public List<Person> findAll() {
        return peopleRepository.findAll();
    }
    // возврат из БД данных одного пользователя по его id
    public Person findOne(int id) {
        Optional<Person> foundPerson = peopleRepository.findById(id);

        return foundPerson.orElse(null); // если запрашиваемых данных нет, то возвращается null
    }

    // сохранение данных в БД
    @Transactional
    public void save(Person person) {
        peopleRepository.save(person);
    }
    // обновление данных в БД
    @Transactional
    public void update(int id, Person updatedPerson) {
        updatedPerson.setId(id);
        peopleRepository.save(updatedPerson);
    }
    // удаление из БД пользователя по id
    @Transactional
    public void delete(int id) {
        peopleRepository.deleteById(id);
    }

    public void test() {
        System.out.println("Testing here with debug. Inside Hibernate Transaction");
    }
}
