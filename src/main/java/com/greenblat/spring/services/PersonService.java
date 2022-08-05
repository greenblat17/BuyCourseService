package com.greenblat.spring.services;

import com.greenblat.spring.models.Person;
import com.greenblat.spring.models.enums.Role;
import com.greenblat.spring.repositories.PersonRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class PersonService {

    private final PersonRepository personRepository;
    private final PasswordEncoder passwordEncoder;

    public void savePerson(Person person) {
        person.setActive(true);
        person.getRoles().add(Role.ROLE_USER);
        person.setPassword(passwordEncoder.encode(person.getPassword()));
        personRepository.save(person);

        log.info("Saving new user with email {}", person.getEmail());
    }
}
