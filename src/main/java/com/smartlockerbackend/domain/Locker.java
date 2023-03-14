package com.smartlockerbackend.domain;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "lockers")
public class Locker {

    @Id
    int id;
    Size size;

    public Locker(int id, Size size) {
        this.id = id;
        this.size = size;
    }
}
