package it.training.spring.dayone;

import it.training.spring.dayone.jdbc.PeopleMapper;
import it.training.spring.dayone.model.People;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;

import javax.sql.DataSource;
import javax.sql.RowSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Main {

    @Autowired
    NamedParameterJdbcTemplate jdbcTemplate;

    @Autowired
    DataSource dataSource;

    @Autowired
    PeopleRepository peopleRepository;

    public static void main(String[] args) {
        ApplicationContext ctx = new AnnotationConfigApplicationContext(
                MainConfiguration.class,
                DbConfiguration.class
        );

        boolean debug = false;

        if (debug) {
            System.out.println("Container beans:\n---------");
            for (String bean : ctx.getBeanDefinitionNames()) {
                System.out.println("> " + bean);
            }
        }

        Main main = ctx.getBean(Main.class);
        /* OUR CODE HERE
        /*--------------------------*/

        List myPeoples = new ArrayList<People>();
        People p1 = new People(null,"Mario","Cartia","mario.cartia@gmail.com");
        People p2 = new People(null,"Giuseppe","Rossi","g.rossi@gmail.com");
        myPeoples.add(p1);
        myPeoples.add(p2);

        // saving records to DB
        main.peopleRepository.saveAll(myPeoples);

        // fetching data from DB
        List<People> dbPeople = (List) main.peopleRepository.findAll();

        for (People p : dbPeople) {
            System.out.println("> "+p);
        }

        /*--------------------------*/
        ((AnnotationConfigApplicationContext) ctx).close();
        ((AnnotationConfigApplicationContext) ctx).stop();
    }
}
