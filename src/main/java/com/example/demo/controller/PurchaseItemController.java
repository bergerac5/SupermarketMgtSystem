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

import com.example.demo.model.PurchaseItem;
import com.example.demo.response.MessageResponse;
import com.example.demo.service.PurchaseItemService;

@RestController
@RequestMapping("/productItems")
public class PurchaseItemController {
    @Autowired
    private PurchaseItemService PurchaseItemService;

    @PostMapping(value = "/savePurchaseItem", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<MessageResponse> savePurchaseItem(@RequestBody PurchaseItem PurchaseItem) {
        MessageResponse response = PurchaseItemService.savePurchaseItem(PurchaseItem);
        HttpStatus httpStatus;

        switch (response.getMessage()) {
            case "PurchaseItem saved successfully":
                httpStatus = HttpStatus.CREATED;
                break;
            case "Invalid PurchaseItem":
                httpStatus = HttpStatus.BAD_REQUEST;
                break;
            default:
                httpStatus = HttpStatus.NOT_ACCEPTABLE;
                break;
        }

        return new ResponseEntity<>(response, httpStatus);
    }

    @PutMapping(value = "/updatePurchaseItem", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<MessageResponse> updatePurchaseItem(@RequestBody PurchaseItem PurchaseItem) {
        MessageResponse response = PurchaseItemService.updatePurchaseItem(PurchaseItem);
        HttpStatus httpStatus;

        switch (response.getMessage()) {
            case "PurchaseItem not exists":
                httpStatus = HttpStatus.NOT_FOUND;
                break;
            case "PurchaseItem updated successfully":
                httpStatus = HttpStatus.CREATED;
                break;
            case "Invalid PurchaseItem":
                httpStatus = HttpStatus.BAD_REQUEST;
                break;
            default:
                httpStatus = HttpStatus.NOT_ACCEPTABLE;
                break;
        }

        return new ResponseEntity<>(response, httpStatus);
    }

    @DeleteMapping(value = "/deletePurchaseItem", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<MessageResponse> deletePurchaseItem(@RequestBody PurchaseItem PurchaseItem) {
        MessageResponse response = PurchaseItemService.deletePurchaseItem(PurchaseItem);
        HttpStatus httpStatus;

        switch (response.getMessage()) {
            case "PurchaseItem not exists":
                httpStatus = HttpStatus.NOT_FOUND;
                break;
            case "PurchaseItem deleted successfully":
                httpStatus = HttpStatus.CREATED;
                break;
            case "Invalid PurchaseItem":
                httpStatus = HttpStatus.BAD_REQUEST;
                break;
            default:
                httpStatus = HttpStatus.NOT_ACCEPTABLE;
                break;
        }

        return new ResponseEntity<>(response, httpStatus);
    }

    @GetMapping(value = "/getAllPurchaseItems", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<PurchaseItem>> getAllPurchaseItems() {
        List<PurchaseItem> PurchaseItems = PurchaseItemService.getAllPurchaseItems();
        return new ResponseEntity<>(PurchaseItems, HttpStatus.OK);
    }

    @GetMapping(value = "/getPurchaseItemById/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<PurchaseItem> getPurchaseItemById(@PathVariable Long id) {
        PurchaseItem PurchaseItem = PurchaseItemService.getPurchaseItemById(id);
        return new ResponseEntity<>(PurchaseItem, HttpStatus.OK);
    }
}
