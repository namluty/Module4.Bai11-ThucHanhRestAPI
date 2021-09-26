package com.apple.controller;

import com.apple.dto.ResponseMessage;
import com.apple.model.Laptop;
import com.apple.service.laptop.ILaptopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.Optional;

@RestController
@RequestMapping("laptops")
@PropertySource("classpath:imageFile.properties")
public class LaptopController {
    @Value("${file-image}")
    private String imageFile;

    @Autowired
    private ILaptopService laptopService;

    @GetMapping
    //ShowList
    public ResponseEntity<?> getListLaptop() {
        return new ResponseEntity<>(laptopService.findAll(), HttpStatus.OK);
    }

    @PostMapping
    //Create
    public ResponseEntity<?> createLaptop(@RequestParam("fileImage") MultipartFile multipart, Laptop laptop) {
        String fileName = multipart.getOriginalFilename();
        try {
            FileCopyUtils.copy(multipart.getBytes(), new File(imageFile + fileName));
        } catch (IOException e) {
            e.printStackTrace();
        }
        laptop.setImage(fileName);
        laptopService.save(laptop);
        return new ResponseEntity<>(new ResponseMessage("Create Laptop Success!"), HttpStatus.OK);
    }

    @PutMapping("{id}")
    //Edit
    public ResponseEntity<?> updateLaptop(@PathVariable Long id, @RequestBody Laptop laptop, @RequestParam("fileImage") MultipartFile multipart) {
        Optional<Laptop> laptop1 = laptopService.findById(id);
        if (!laptop1.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        String fileName = multipart.getOriginalFilename();
        try {
            FileCopyUtils.copy(multipart.getBytes(), new File(imageFile + fileName));
        } catch (IOException e) {
            e.printStackTrace();
        }
        laptop.setImage(fileName);
        laptopService.save(laptop);
        return new ResponseEntity<>(new ResponseMessage("Update Laptop Success!"), HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    //Delete
    public ResponseEntity<?> deleteLaptop(@PathVariable Long id) {
        Optional<Laptop> laptop = laptopService.findById(id);
        if (!laptop.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        laptopService.deleteById(id);
        return new ResponseEntity<>(new ResponseMessage("Delete Laptop Success!"), HttpStatus.OK);
    }

    @GetMapping("search")
    //Search
    public ResponseEntity<?> searchLaptopByName(String name) {
        return new ResponseEntity<>(laptopService.findAllByNameContaining(name), HttpStatus.OK);
    }
}
