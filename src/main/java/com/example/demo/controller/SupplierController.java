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

import com.example.demo.model.Supplier;
import com.example.demo.response.MessageResponse;
import com.example.demo.service.SupplierService;

@RestController
@RequestMapping("/Suppliers")
public class SupplierController {
    @Autowired
    private SupplierService SupplierService;

    @PostMapping(value = "/saveSupplier", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<MessageResponse> saveSupplier(@RequestBody Supplier Supplier) {
        MessageResponse response = SupplierService.saveSupplier(Supplier);
        HttpStatus httpStatus;

        switch (response.getMessage()) {
            case "Supplier saved successfully":
                httpStatus = HttpStatus.CREATED;
                break;
            case "Invalid Supplier":
                httpStatus = HttpStatus.BAD_REQUEST;
                break;
            default:
                httpStatus = HttpStatus.NOT_ACCEPTABLE;
                break;
        }

        return new ResponseEntity<>(response, httpStatus);
    }

    @PutMapping(value = "/updateSupplier", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<MessageResponse> updateSupplier(@RequestBody Supplier Supplier) {
        MessageResponse response = SupplierService.updateSupplier(Supplier);
        HttpStatus httpStatus;

        switch (response.getMessage()) {
            case "Supplier not exists":
                httpStatus = HttpStatus.NOT_FOUND;
                break;
            case "Supplier updated successfully":
                httpStatus = HttpStatus.CREATED;
                break;
            case "Invalid Supplier":
                httpStatus = HttpStatus.BAD_REQUEST;
                break;
            default:
                httpStatus = HttpStatus.NOT_ACCEPTABLE;
                break;
        }

        return new ResponseEntity<>(response, httpStatus);
    }

    @DeleteMapping(value = "/deleteSupplier", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<MessageResponse> deleteSupplier(@RequestBody Supplier Supplier) {
        MessageResponse response = SupplierService.deleteSupplier(Supplier);
        HttpStatus httpStatus;

        switch (response.getMessage()) {
            case "Supplier not exists":
                httpStatus = HttpStatus.NOT_FOUND;
                break;
            case "Supplier deleted successfully":
                httpStatus = HttpStatus.CREATED;
                break;
            case "Invalid Supplier":
                httpStatus = HttpStatus.BAD_REQUEST;
                break;
            default:
                httpStatus = HttpStatus.NOT_ACCEPTABLE;
                break;
        }

        return new ResponseEntity<>(response, httpStatus);
    }

    @GetMapping(value = "/getAllSuppliers", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Supplier>> getAllSuppliers() {
        List<Supplier> Suppliers = SupplierService.getAllSuppliers();
        return new ResponseEntity<>(Suppliers, HttpStatus.OK);
    }

    @GetMapping(value = "/getSupplierById/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Supplier> getSupplierById(@PathVariable Long id) {
        Supplier Supplier = SupplierService.getSupplierById(id);
        return new ResponseEntity<>(Supplier, HttpStatus.OK);
    }
}
