package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Product;
import com.example.demo.response.MessageResponse;
import com.example.demo.service.ProductService;

@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private ProductService ProductService;

    @PostMapping(value = "/saveProduct", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<MessageResponse> saveProduct(@RequestBody Product Product) {
        MessageResponse response = ProductService.saveProduct(Product);
        HttpStatus httpStatus;

        switch (response.getMessage()) {
            case "Product saved successfully":
                httpStatus = HttpStatus.CREATED;
                break;
            case "Invalid Product":
                httpStatus = HttpStatus.BAD_REQUEST;
                break;
            default:
                httpStatus = HttpStatus.NOT_ACCEPTABLE;
                break;
        }

        return new ResponseEntity<>(response, httpStatus);
    }

    @PutMapping(value = "/updateProduct", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<MessageResponse> updateProduct(@RequestBody Product Product) {
        MessageResponse response = ProductService.updateProduct(Product);
        HttpStatus httpStatus;

        switch (response.getMessage()) {
            case "Product not exists":
                httpStatus = HttpStatus.NOT_FOUND;
                break;
            case "Product updated successfully":
                httpStatus = HttpStatus.CREATED;
                break;
            case "Invalid Product":
                httpStatus = HttpStatus.BAD_REQUEST;
                break;
            default:
                httpStatus = HttpStatus.NOT_ACCEPTABLE;
                break;
        }

        return new ResponseEntity<>(response, httpStatus);
    }

    @DeleteMapping(value = "/deleteProduct", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<MessageResponse> deleteProduct(@RequestBody Product Product) {
        MessageResponse response = ProductService.deleteProduct(Product);
        HttpStatus httpStatus;

        switch (response.getMessage()) {
            case "Product not exists":
                httpStatus = HttpStatus.NOT_FOUND;
                break;
            case "Product deleted successfully":
                httpStatus = HttpStatus.CREATED;
                break;
            case "Invalid Product":
                httpStatus = HttpStatus.BAD_REQUEST;
                break;
            default:
                httpStatus = HttpStatus.NOT_ACCEPTABLE;
                break;
        }

        return new ResponseEntity<>(response, httpStatus);
    }

    @GetMapping(value = "/getAllProducts", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Product>> getAllProducts() {
        List<Product> Products = ProductService.getAllProducts();
        return new ResponseEntity<>(Products, HttpStatus.OK);
    }

    @GetMapping(value = "/getProductById/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Product> getProductById(@PathVariable Long id) {
        Product Product = ProductService.getProductById(id);
        return new ResponseEntity<>(Product, HttpStatus.OK);
    }
}
