package it.training.spring.dayone;

import it.training.spring.dayone.aop.MyAudit;
import org.slf4j.Logger;
import org.springframework.beans.factory.BeanNameAware;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.UUID;


@Component
@Qualifier("testBean")
public class MyBean implements BeanNameAware {

    boolean isManaged;

    @Autowired
    @Qualifier("myBean")
    MyBean self;

    @Autowired
    private String hello;
    String springName;

    @Autowired
    private Logger myLogger;

    public MyBean() {
        super();
        this.isManaged = false;
        //myLogger.info("costruttore...");
    }

    @MyAudit
    public int sum(int a, int b) {
        System.out.println("dentro sum()...");
        return a+b;
    }

    @MyAudit
    public int sub(int a, int b) {
        System.out.println("dentro sub()...");
        return a-b;
    }

    //@PostConstruct
    public void postConstruct() {
        myLogger.info("MyBean::postConstruct( "+springName+" )");
    }

    //@PreDestroy
    public void preDestroy() {
        myLogger.info("MyBean::preDestroy( "+springName+" )");
    }

    public String rndValue() {
        return UUID.randomUUID().toString();
    }

    public String getHello() {
        return hello;
    }

    public void setHello(String hello) {
        System.out.println("MyBean.setHello( "+hello+" )...");
        this.hello = hello;
    }

    @Override
    public String toString() {
        return "MyBean{" +
                "hello='" + hello + '\'' +
                '}';
    }

    @Override
    public void setBeanName(String name) {
        this.isManaged = true;
    }

    public boolean isManaged() {
        return springName!=null;
    }

    //@Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("MyBean::afterPropertiesSet( "+springName+" )");
        myLogger.info("Valore di hello: "+hello);
    }

    //@Override
    public void destroy() throws Exception {
        myLogger.info("MyBean::destroy( "+springName+" )");
    }

    public void destroy2() throws Exception {
        myLogger.info("MyBean::destroy2( "+springName+" )");
    }

    public String notCached(int seed) {
        System.out.println("Generating random value...");
        try {
        Thread.sleep(5000);
        } catch (Exception e) {}
        return UUID.randomUUID().toString();
    }

    @Cacheable(value = "myCache")
    public String cached(int seed) {
        return notCached(seed);
    }

    public String cachedWrapper(int seed) {
        if (this.isManaged) return self.cached(seed);
        else return cached(seed);
    }

}
