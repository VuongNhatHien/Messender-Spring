package com.example.spring_backend.shared;

import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public abstract class BaseService<T, ID> {

    protected final JpaRepository<T, ID> repository;

    // Create or Update
    public T create(T entity) {
        return repository.save(entity);
    }

    // Read (Single Entity)
    public Optional<T> findById(ID id) {
        return repository.findById(id);
    }

    // Read (All Entities)
    public List<T> findAll() {
        return repository.findAll();
    }

    // Delete
    public void deleteById(ID id) {
        repository.deleteById(id);
    }

    // Check if Entity Exists
    public boolean existsById(ID id) {
        return repository.existsById(id);
    }
}