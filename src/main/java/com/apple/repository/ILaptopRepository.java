package com.apple.repository;

import com.apple.model.Laptop;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ILaptopRepository extends JpaRepository<Laptop, Long> {
    Iterable<Laptop> findAllByNameContaining(String name);
}
