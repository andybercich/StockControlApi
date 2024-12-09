package com.ProyectoStock.proyectoStock.Services;

import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
@Transactional
public abstract class BaseService<T, ID>{

    @Autowired
    protected JpaRepository<T, ID> repository;

    public T save(T entity) throws Exception {
        try {
            return repository.save(entity);
        }catch (Exception e){throw new Exception(e.getMessage());}
    }

    public T findById(ID id) throws Exception {
        try {
            Optional<T> entity = repository.findById(id);
            return entity.orElse(null);
        }catch (Exception e){
            throw new Exception(e.getMessage());
        }

    }

    public List<T> findAll() throws Exception {
        try{
            return repository.findAll();
        }catch (Exception e){throw new Exception(e.getMessage());}

    }

    public T update(ID id, T entity) throws Exception {
        try{
            if (repository.existsById(id)) {
                T existingEntity = repository.findById(id).orElse(null);
                if (existingEntity != null) {
                    final BeanWrapper srcEntity = new BeanWrapperImpl(entity);
                    final BeanWrapper srcExistingEntity = new BeanWrapperImpl(existingEntity);

                    java.beans.PropertyDescriptor[] entityDescriptors = srcEntity.getPropertyDescriptors();
                    java.beans.PropertyDescriptor[] existingEntityDescriptors = srcExistingEntity.getPropertyDescriptors();

                    for (java.beans.PropertyDescriptor entityDescriptor : entityDescriptors) {
                        for (java.beans.PropertyDescriptor existingEntityDescriptor : existingEntityDescriptors) {
                            if (entityDescriptor.getName().equals(existingEntityDescriptor.getName())) {
                                Object value = srcEntity.getPropertyValue(entityDescriptor.getName());
                                if (value != null &&
                                        !(value instanceof Number && ((Number) value).doubleValue() == 0.0) &&
                                        !(value instanceof Boolean && !(boolean) value)) {
                                    // Avoid setting properties like "class"
                                    if (!entityDescriptor.getName().equals("class")) {
                                        srcExistingEntity.setPropertyValue(existingEntityDescriptor.getName(), value);
                                    }
                                }
                                break;
                            }
                        }
                    }

                    return repository.save(existingEntity);
                }
            }
            return null;
        }catch (Exception e){throw new Exception(e.getMessage());}

    }



    public List<T> deleteById(ID id) throws Exception {
        try {
            repository.deleteById(id);
            return repository.findAll();
        }catch (Exception e){
            throw new Exception(e.getMessage());
        }

    }

}

