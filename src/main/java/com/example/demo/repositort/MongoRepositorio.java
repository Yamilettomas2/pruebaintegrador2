package com.example.demo.repositort;

import com.example.demo.entity.UserEntidad;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MongoRepositorio extends MongoRepository<UserEntidad, String> {

    Optional<UserEntidad> findByEmail(String email);

}
