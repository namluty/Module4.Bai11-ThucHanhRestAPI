package com.apple.service.product;

import com.apple.model.Product;

import java.util.Optional;

public interface IProductService {
    Iterable<Product> findAll();

    Optional<Product> findById(Long id);

    void deleteById(Long id);

    Product save(Product product);

    Iterable<Product> findAllByNameContaining(String name);
}
