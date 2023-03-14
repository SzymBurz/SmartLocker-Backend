package com.smartlockerbackend.generator;

import com.smartlockerbackend.domain.Locker;
import com.smartlockerbackend.domain.Size;
import com.smartlockerbackend.repository.LockerRepo;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@NoArgsConstructor
@Component
public class LockerGenerator {

    @Autowired
    LockerRepo lockerRepo;

    public void generateExampleData() {
        for (int i = 0; i < 100; i++) {
            if (i <= 30) {
                lockerRepo.save(new Locker(i, Size.S));
            } else if (i > 30 && i <= 70) {
                lockerRepo.save(new Locker(i, Size.M));
            } else {
                lockerRepo.save(new Locker(i, Size.L));
            }
        }

    }
}
