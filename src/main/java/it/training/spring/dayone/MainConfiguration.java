package it.training.spring.dayone;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.concurrent.ConcurrentMapCacheManager;
import org.springframework.context.annotation.*;

import java.util.UUID;

@Configuration
@ComponentScans({
        @ComponentScan("it.training.spring.dayone"),
        @ComponentScan("it.training.spring.dayone.aop"),
})
@PropertySource("classpath:my.properties")
@EnableAspectJAutoProxy
@EnableCaching
public class MainConfiguration {

    @Value("${db.url}")
    String dbUrl;
    @Value("${db.user}")
    String dbUsername;
    @Value("${db.pass}")
    String dbPassword;

    @Bean
    public String hello() {
        return "Hello, Spring!";
    }

    @Bean
    @Scope(ConfigurableBeanFactory.SCOPE_SINGLETON) // default
    @Lazy(true)
    public String pippo() {
        System.out.println("Inizializzo bean \"pippo\"...");
        return "Hello, Pippo!";
    }

    @Bean
    public MyBean myBean1() {
        return new MyBean();
    }

    @Bean
    public MyBean myBean2() {
        return new MyBean();
    }

    @Bean
    public Main main() {
        return new Main();
    }

    @Bean
    @Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
    public String scopeTest() {
        return UUID.randomUUID().toString();
    }

    @Bean
    public Logger myLogger() {
        return LoggerFactory.getLogger("My LOGGER");
    }

    @Bean
    public CacheManager cacheManager() {
        return new ConcurrentMapCacheManager("myCache");
    }

}
