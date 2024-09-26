package com.example.demo.service;

import com.example.demo.dto.UserDto;
import com.example.demo.entity.UserEntidad;
import com.example.demo.repositort.MongoRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class UserService {

    @Autowired
    private MongoRepositorio mongoRepositorio;

    public List<UserDto> getAll(){
        return this.mongoRepositorio.findAll().stream()
                .map(this::toDto)
                .toList();
    }

    public UserDto getById(String id){
        return this.mongoRepositorio.findById(id)
                .map(this::toDto)
                .orElseThrow(() -> new NoSuchElementException("User not found"));
    }

    public UserDto save(UserDto user){
        UserEntidad entity = new UserEntidad();
        entity.setName(user.getName());
        entity.setEmail(user.getEmail());
        UserEntidad entitySaved = this.mongoRepositorio.save(entity);
        UserDto saved = this.toDto(entitySaved);
        return saved;
    }

    public UserDto update(UserDto user, String id){
        UserEntidad entity = this.mongoRepositorio.findById(id).orElse(null);
        entity.setEmail(user.getEmail());
        entity.setName(user.getName());
        UserEntidad entitySaved = this.mongoRepositorio.save(entity);
        UserDto saved = this.toDto(entitySaved);
        return saved;
    }

    public void delete(String id){
        UserEntidad entity = this.mongoRepositorio.findById(id).orElse(null);
        this.mongoRepositorio.delete(entity);
    }

    private UserDto toDto(UserEntidad entity){
        return new UserDto(entity.getId(), entity.getName(), entity.getEmail());
    }
}
