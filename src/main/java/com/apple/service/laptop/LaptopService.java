package com.apple.service.laptop;

import com.apple.model.Laptop;
import com.apple.model.Product;
import com.apple.repository.ILaptopRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class LaptopService implements ILaptopService {
    @Autowired
    private ILaptopRepository laptopRepository;

    @Override
    public Iterable<Laptop> findAll() {
        return laptopRepository.findAll();
    }

    @Override
    public Optional<Laptop> findById(Long id) {
        return laptopRepository.findById(id);
    }

    @Override
    public void deleteById(Long id) {
        laptopRepository.deleteById(id);
    }

    @Override
    public Laptop save(Laptop laptop) {
        return laptopRepository.save(laptop);
    }

    @Override
    public Iterable<Laptop> findAllByNameContaining(String name) {
        return laptopRepository.findAllByNameContaining(name);
    }
}
