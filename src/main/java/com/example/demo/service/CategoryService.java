package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.Category;
import com.example.demo.repository.CategoryRepository;
import com.example.demo.response.MessageResponse;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRep;

    // save the category
    public MessageResponse saveCategory(Category category) {
        MessageResponse resp = new MessageResponse();
        if (category != null) {
            Category checkCategory = categoryRep.findByName(category.getName());
            if (checkCategory != null) {
                resp.setMessage("Category already exists");
            } else {
                categoryRep.save(category);
                resp.setMessage("Category saved successfully");
            }
        } else {
            resp.setMessage("Invalid Category");
        }
        return resp;
    }

    // update the category
    public MessageResponse updateCategory(Category category) {
        MessageResponse resp = new MessageResponse();
        if (category != null) {
            boolean checkCategoryId = categoryRep.existsById(category.getId());
            if (checkCategoryId == true) {
                categoryRep.save(category);
                resp.setMessage("Category updated successfully");
                ;
            } else {
                resp.setMessage("Category not exists");
            }
        } else {
            resp.setMessage("Invalid Category");
        }
        return resp;
    }

    // update the category
    public MessageResponse deleteCategory(Category category) {
        MessageResponse resp = new MessageResponse();
        if (category != null) {
            boolean checkCategoryId = categoryRep.existsById(category.getId());
            if (checkCategoryId == true) {
                categoryRep.delete(category);
                resp.setMessage("Category deleted successfully");
                ;
            } else {
                resp.setMessage("Category not exists");
            }
        } else {
            resp.setMessage("Invalid Category");
        }
        return resp;
    }

    // get all categories
    public List<Category> getAllCategories() {
        return categoryRep.findAll();
    }

    // get category by id
    public Category getCategoryById(Long id) {
        return categoryRep.findById(id).orElse(null);
    }

}
