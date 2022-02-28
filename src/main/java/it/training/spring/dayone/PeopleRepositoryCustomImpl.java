package it.training.spring.dayone;

import com.github.javafaker.Faker;
import it.training.spring.dayone.model.People;

public class PeopleRepositoryCustomImpl implements PeopleRepositoryCustom {

    @Override
    public People getFake() {
        Faker faker = new Faker();
        People p = new People();

        String firstName = faker.name().firstName();
        String lastName = faker.name().lastName();
        String email = (firstName+"."+lastName+"@"+faker.internet().domainName()).toLowerCase();

        p.setFirstName(firstName);
        p.setLastName(lastName);
        p.setEmail(email);

        return p;
    }
}
