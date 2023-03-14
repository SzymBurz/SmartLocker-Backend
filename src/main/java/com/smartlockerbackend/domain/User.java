package com.smartlockerbackend.domain;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "users")
public class User {
    @Id
    int id;
    String name;

    public User(int id, String name) {
        this.id = id;
        this.name = name;
    }
}
