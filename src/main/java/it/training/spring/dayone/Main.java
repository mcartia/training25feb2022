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
import java.util.List;
import java.util.Map;

public class Main {

    @Autowired
    NamedParameterJdbcTemplate jdbcTemplate;

    @Autowired
    DataSource dataSource;

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

        String insertSql1 = "insert into people(firstName, lastName, email) values('Mario','Cartia','mario.cartia@gmail.com')";
        String insertSql2 = "insert into people(firstName, lastName, email) values('Giuseppe','Rossi','g.rossi@gmail.com')";
        main.jdbcTemplate.update(insertSql1, (Map<String, ?>) null);
        main.jdbcTemplate.update(insertSql2, (Map<String, ?>) null);

        String selectAll = "select * from people";
        /*SqlRowSet rs = main.jdbcTemplate.queryForRowSet(selectAll, (Map<String, ?>) null);

        while (rs.next()) {
            System.out.println("+ "+rs.getLong("id")
                    +","+rs.getString("firstName")
                    +","+rs.getString("lastName")+","
                    +rs.getString("email"));
        }*/
        List<People> peoples = main.jdbcTemplate.query(selectAll, (Map<String, ?>) null, new PeopleMapper());

        for (People p : peoples) {
            System.out.println("> "+p);
        }

        /*--------------------------*/
        ((AnnotationConfigApplicationContext) ctx).close();
        ((AnnotationConfigApplicationContext) ctx).stop();
    }
}
