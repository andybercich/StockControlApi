package com.ProyectoStock.proyectoStock.Controller;

import com.ProyectoStock.proyectoStock.Services.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin(origins = {"http://127.0.0.1:5500"})
public abstract class BaseController<T, ID, S extends BaseService<T, ID>> {

    @Autowired
    protected S service;

    @PostMapping
    public T create(@RequestBody T entity) throws Exception {

            return service.save(entity);


    }

    @GetMapping("/{id}")
    public T getById(@PathVariable ID id) throws Exception {

            return service.findById(id);


    }

    @GetMapping
    public List<T> getAll() throws Exception {

            return service.findAll();


    }

    @PutMapping("/{id}")
    public T update(@PathVariable ID id, @RequestBody T entity) throws Exception {

            return service.update(id,entity);


    }

    @DeleteMapping("/{id}")
    public List<T> deleteById(@PathVariable ID id) throws Exception {

            return service.deleteById(id);


    }
}

