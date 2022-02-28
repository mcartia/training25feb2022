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
    PeopleGenerator peopleGenerator;

    @Autowired
    PeopleRepository peopleRepository;

    @Autowired
    PeopleService peopleService;

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

        System.out.println(main.peopleRepository.getFake());

        /*--------------------------*/
        ((AnnotationConfigApplicationContext) ctx).close();
        ((AnnotationConfigApplicationContext) ctx).stop();
    }
}
