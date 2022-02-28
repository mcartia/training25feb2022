package it.training.spring.dayone;

import it.training.spring.dayone.model.People;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface PeopleRepository extends CrudRepository<People, Long>, PeopleRepositoryCustom {

    /*@Query("select * from PEOPLE where email=:searchKey")
    List<People> customSearchByEmail(@Param("searchKey") String email);*/

    List<People> findByEmail(String email);
}
