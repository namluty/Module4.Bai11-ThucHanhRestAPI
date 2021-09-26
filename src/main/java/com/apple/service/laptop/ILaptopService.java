package com.apple.service.laptop;

import com.apple.model.Laptop;
import com.apple.model.Product;

import java.util.Optional;

public interface ILaptopService {
    Iterable<Laptop> findAll();

    Optional<Laptop> findById(Long id);

    void deleteById(Long id);

    Laptop save(Laptop laptop);

    Iterable<Laptop> findAllByNameContaining(String name);
}
