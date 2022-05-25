package com.github.jjcdutra.aws_project01.controller;

import com.github.jjcdutra.aws_project01.enums.EventType;
import com.github.jjcdutra.aws_project01.model.Product;
import com.github.jjcdutra.aws_project01.repository.ProductRepository;
import com.github.jjcdutra.aws_project01.service.ProductPublish;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/product")
public class ProductController {

    private ProductRepository productRepository;
    private ProductPublish productPublish;

    public ProductController(ProductRepository productRepository, ProductPublish productPublish) {
        this.productRepository = productRepository;
        this.productPublish = productPublish;
    }

    @GetMapping
    public Iterable<Product> findAll() {
        productPublish.publishProductEvent(new Product(), EventType.PRODUCT_CREATED, "busca");
        return productRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> findById(@PathVariable Long id) {
        Optional<Product> optProduct = productRepository.findById(id);
        if (optProduct.isPresent()) {
            return new ResponseEntity<>(optProduct.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<Product> saveProduct(@RequestBody Product product) {
        Product productCreated = productRepository.save(product);
        productPublish.publishProductEvent(productCreated, EventType.PRODUCT_CREATED, "matilde");
        return new ResponseEntity<>(productCreated, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Product> updateProduct(@RequestBody Product product, @PathVariable Long id) {
        if (productRepository.existsById(id)) {
            product = new Product(id, product.getName(), product.getModel(), product.getCode(), product.getPrice(), product.getColor());

            Product productUpdated = productRepository.save(product);

            productPublish.publishProductEvent(productUpdated, EventType.PRODUCT_UPDATED, "doralice");

            return new ResponseEntity<>(productUpdated, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Product> deleteProduct(@PathVariable Long id) {
        Optional<Product> optProduct = productRepository.findById(id);
        if (optProduct.isPresent()) {
            Product product = optProduct.get();

            productRepository.delete(product);

            productPublish.publishProductEvent(product, EventType.PRODUCT_DELETED, "hannah");

            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/byCode")
    public ResponseEntity<Product> findByCode(@RequestParam String code) {
        Optional<Product> optProduct = productRepository.findByCode(code);
        if (optProduct.isPresent()) {
            return new ResponseEntity<>(optProduct.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
