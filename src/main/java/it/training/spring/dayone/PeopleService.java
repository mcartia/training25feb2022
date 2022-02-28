package it.training.spring.dayone;

import it.training.spring.dayone.model.People;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Random;

@Component
@Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED)
public class PeopleService {

    @Autowired
    PeopleGenerator peopleGenerator;

    @Autowired
    PeopleRepository peopleRepository;

    @Autowired
    PeopleService selfProxy;

    public void initDb(int n) {
        List<People> pList = peopleGenerator.buildPeopleList(n);
        for (People p : pList) {
            System.out.println("Sto salvando "+p);
            peopleRepository.save(p);

            if (new Random().nextInt(100) > 80) {
                int a = n/0;
            }
        }
    }

    public void initDbTx(int n) {
        // join existing tx
        selfProxy.initDb(n);
    }

    public void initDbTxWrapper(int n) {
        // start new tx
        selfProxy.initDbTx(n);
    }

}
