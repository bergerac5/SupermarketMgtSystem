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

import com.example.demo.model.Category;
import com.example.demo.response.MessageResponse;
import com.example.demo.service.CategoryService;

@RestController
@RequestMapping("/categories")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @PostMapping(value = "/saveCategory", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<MessageResponse> saveCategory(@RequestBody Category category) {
        MessageResponse response = categoryService.saveCategory(category);
        HttpStatus httpStatus;

        switch (response.getMessage()) {
            case "Category already exists":
                httpStatus = HttpStatus.CONFLICT;
                break;
            case "Category saved successfully":
                httpStatus = HttpStatus.CREATED;
                break;
            case "Invalid Category":
                httpStatus = HttpStatus.BAD_REQUEST;
                break;
            default:
                httpStatus = HttpStatus.NOT_ACCEPTABLE;
                break;
        }

        return new ResponseEntity<>(response, httpStatus);
    }

    @PutMapping(value = "/updateCategory", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<MessageResponse> updateCategory(@RequestBody Category category) {
        MessageResponse response = categoryService.updateCategory(category);
        HttpStatus httpStatus;

        switch (response.getMessage()) {
            case "Category not exists":
                httpStatus = HttpStatus.NOT_FOUND;
                break;
            case "Category updated successfully":
                httpStatus = HttpStatus.CREATED;
                break;
            case "Invalid Category":
                httpStatus = HttpStatus.BAD_REQUEST;
                break;
            default:
                httpStatus = HttpStatus.NOT_ACCEPTABLE;
                break;
        }

        return new ResponseEntity<>(response, httpStatus);
    }

    @DeleteMapping(value = "/deleteCategory", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<MessageResponse> deleteCategory(@RequestBody Category category) {
        MessageResponse response = categoryService.deleteCategory(category);
        HttpStatus httpStatus;

        switch (response.getMessage()) {
            case "Category not exists":
                httpStatus = HttpStatus.NOT_FOUND;
                break;
            case "Category deleted successfully":
                httpStatus = HttpStatus.CREATED;
                break;
            case "Invalid Category":
                httpStatus = HttpStatus.BAD_REQUEST;
                break;
            default:
                httpStatus = HttpStatus.NOT_ACCEPTABLE;
                break;
        }

        return new ResponseEntity<>(response, httpStatus);
    }

    @GetMapping(value = "/getAllCategories", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Category>> getAllCategories() {
        List<Category> categories = categoryService.getAllCategories();
        return new ResponseEntity<>(categories, HttpStatus.OK);
    }

    @GetMapping(value = "/getCategoryById/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Category> getCategoryById(@PathVariable Long id) {
        Category category = categoryService.getCategoryById(id);
        return new ResponseEntity<>(category, HttpStatus.OK);
    }

}
