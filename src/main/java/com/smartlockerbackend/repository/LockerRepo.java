package com.smartlockerbackend.repository;

import com.smartlockerbackend.domain.Locker;
import com.smartlockerbackend.domain.Size;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LockerRepo extends MongoRepository<Locker, Integer> {
    List<Locker> findBySize(Size size);
}
