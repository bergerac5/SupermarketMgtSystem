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

import com.example.demo.model.Purchase;
import com.example.demo.response.MessageResponse;
import com.example.demo.service.PurchaseService;

@RestController
@RequestMapping("/Purchases")
public class PurchaseController {

    @Autowired
    private PurchaseService PurchaseService;

    @PostMapping(value = "/savePurchase", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<MessageResponse> savePurchase(@RequestBody Purchase Purchase) {
        MessageResponse response = PurchaseService.savePurchase(Purchase);
        HttpStatus httpStatus;

        switch (response.getMessage()) {
            case "Purchase saved successfully":
                httpStatus = HttpStatus.CREATED;
                break;
            case "Invalid Purchase":
                httpStatus = HttpStatus.BAD_REQUEST;
                break;
            default:
                httpStatus = HttpStatus.NOT_ACCEPTABLE;
                break;
        }

        return new ResponseEntity<>(response, httpStatus);
    }

    @PutMapping(value = "/updatePurchase", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<MessageResponse> updatePurchase(@RequestBody Purchase Purchase) {
        MessageResponse response = PurchaseService.updatePurchase(Purchase);
        HttpStatus httpStatus;

        switch (response.getMessage()) {
            case "Purchase not exists":
                httpStatus = HttpStatus.NOT_FOUND;
                break;
            case "Purchase updated successfully":
                httpStatus = HttpStatus.CREATED;
                break;
            case "Invalid Purchase":
                httpStatus = HttpStatus.BAD_REQUEST;
                break;
            default:
                httpStatus = HttpStatus.NOT_ACCEPTABLE;
                break;
        }

        return new ResponseEntity<>(response, httpStatus);
    }

    @DeleteMapping(value = "/deletePurchase", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<MessageResponse> deletePurchase(@RequestBody Purchase Purchase) {
        MessageResponse response = PurchaseService.deletePurchase(Purchase);
        HttpStatus httpStatus;

        switch (response.getMessage()) {
            case "Purchase not exists":
                httpStatus = HttpStatus.NOT_FOUND;
                break;
            case "Purchase deleted successfully":
                httpStatus = HttpStatus.CREATED;
                break;
            case "Invalid Purchase":
                httpStatus = HttpStatus.BAD_REQUEST;
                break;
            default:
                httpStatus = HttpStatus.NOT_ACCEPTABLE;
                break;
        }

        return new ResponseEntity<>(response, httpStatus);
    }

    @GetMapping(value = "/getAllPurchases", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Purchase>> getAllPurchases() {
        List<Purchase> Purchases = PurchaseService.getAllPurchases();
        return new ResponseEntity<>(Purchases, HttpStatus.OK);
    }

    @GetMapping(value = "/getPurchaseById/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Purchase> getPurchaseById(@PathVariable Long id) {
        Purchase Purchase = PurchaseService.getPurchaseById(id);
        return new ResponseEntity<>(Purchase, HttpStatus.OK);
    }
}
