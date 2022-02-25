package it.training.spring.dayone;

import it.training.spring.dayone.model.People;
import org.springframework.data.repository.CrudRepository;

public interface PeopleRepository extends CrudRepository<People, Long> {
}
