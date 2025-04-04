package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.Product;
import com.example.demo.repository.ProductRepository;
import com.example.demo.response.MessageResponse;

@Service
public class ProductService {

    @Autowired
    private ProductRepository ProductRep;

    // save the Product
    public MessageResponse saveProduct(Product Product) {
        MessageResponse resp = new MessageResponse();
        if (Product != null) {

            ProductRep.save(Product);
            resp.setMessage("Product saved successfully");

        } else {
            resp.setMessage("Invalid Product");
        }
        return resp;
    }

    // update the Product
    public MessageResponse updateProduct(Product Product) {
        MessageResponse resp = new MessageResponse();
        if (Product != null) {
            boolean checkProductId = ProductRep.existsById(Product.getId());
            if (checkProductId == true) {
                ProductRep.save(Product);
                resp.setMessage("Product updated successfully");
                ;
            } else {
                resp.setMessage("Product not exists");
            }
        } else {
            resp.setMessage("Invalid Product");
        }
        return resp;
    }

    // delete the Product
    public MessageResponse deleteProduct(Product Product) {
        MessageResponse resp = new MessageResponse();
        if (Product != null) {
            boolean checkProductId = ProductRep.existsById(Product.getId());
            if (checkProductId == true) {
                ProductRep.delete(Product);
                resp.setMessage("Product deleted successfully");

            } else {
                resp.setMessage("Product not exists");
            }
        } else {
            resp.setMessage("Invalid Product");
        }
        return resp;
    }

    // get all Products
    public List<Product> getAllProducts() {
        return ProductRep.findAll();
    }

    // get Product by id
    public Product getProductById(Long id) {
        return ProductRep.findById(id).orElse(null);
    }
}
