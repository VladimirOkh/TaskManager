package ru.okhremenko.taskmanagementsystem.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.okhremenko.taskmanagementsystem.models.Person;
import ru.okhremenko.taskmanagementsystem.repositories.PeopleRepository;
import ru.okhremenko.taskmanagementsystem.security.PersonDetails;


import java.util.Optional;

@Service
public class PersonDetailsService implements UserDetailsService {

    private final PeopleRepository peopleRepository;

    @Autowired
    public PersonDetailsService(PeopleRepository peopleRepository) {
        this.peopleRepository = peopleRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        Optional<Person> person = peopleRepository.findByUsername(s);

        if (person.isEmpty())
            throw new UsernameNotFoundException("User not found!");
        return new PersonDetails(person.get());
    }
}
