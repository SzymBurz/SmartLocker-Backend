package com.smartlockerbackend;

import com.smartlockerbackend.domain.Locker;
import com.smartlockerbackend.generator.LockerGenerator;
import com.smartlockerbackend.repository.LockerRepo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class GeneratorsTestSuite {

    @Autowired
    LockerGenerator lockerGenerator;

    @Autowired
    LockerRepo lockerRepo;

    @Test
    void lockersGeneratorTest(){

        lockerGenerator.generateExampleData();

        for (Locker l: lockerRepo.findAll()) {
            System.out.println(l);
        }

        lockerRepo.deleteAll();


    }
}
