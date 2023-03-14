package com.smartlockerbackend.generator;

import com.smartlockerbackend.domain.User;
import org.springframework.stereotype.Component;

@Component
public class UserGenerator {

    User demoUser = new User(1, "Kiemoon");

    public User generateUserForDemo() {
        return demoUser;
    }
}
