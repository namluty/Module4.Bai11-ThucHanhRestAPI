package com.apple.controller;

import com.apple.dto.ResponseMessage;
import com.apple.model.Product;
import com.apple.service.product.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
//@CrossOrigin(origins = "*")
@RequestMapping("products")
public class ProductController {
    @Autowired
    private IProductService productService;

    @GetMapping
    //ShowList
    public ResponseEntity<?> getListSProduct() {
        return new ResponseEntity<>(productService.findAll(), HttpStatus.OK);
    }

    @PostMapping
    //Create
    public ResponseEntity<?> createProduct(@RequestBody Product product) {
        if (product.getName().trim().isEmpty()) {
            return new ResponseEntity<>(new ResponseMessage("NO"), HttpStatus.OK);
        }
        productService.save(product);
        return new ResponseEntity<>(new ResponseMessage("Create Success!"), HttpStatus.OK);
    }

    @PutMapping("{id}")
    //Edit
    public ResponseEntity<?> updateSmartPhone(@PathVariable Long id, @RequestBody Product product) {
        Optional<Product> product1 = productService.findById(id);
        if (!product1.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        product1.get().setName(product.getName());
        product1.get().setDescription(product.getDescription());
        productService.save(product);
        return new ResponseEntity<>(new ResponseMessage("Update Success!"), HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    //Delete
    public ResponseEntity<?> deleteProduct(@PathVariable Long id) {
        Optional<Product> product = productService.findById(id);
        if (!product.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        productService.deleteById(product.get().getId());
        return new ResponseEntity<>(new ResponseMessage("Delete Success!"), HttpStatus.OK);
    }

    @GetMapping("search")
    //Search
    public ResponseEntity<?> findAllByName(@RequestParam("name") String name) {
        return new ResponseEntity<>(productService.findAllByNameContaining(name), HttpStatus.OK);
    }

    @GetMapping("{id}")
    //Detail
    public ResponseEntity<?> detailProduct(@PathVariable Long id) {
        Optional<Product> product = productService.findById(id);
        if (!product.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(product.get(), HttpStatus.OK);
    }
}
