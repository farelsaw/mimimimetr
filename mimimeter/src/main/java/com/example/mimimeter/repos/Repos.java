package com.example.mimimeter.repos;

import com.example.mimimeter.models.Cats;
import org.springframework.data.repository.CrudRepository;

public interface Repos extends CrudRepository<Cats, Long> {
}
