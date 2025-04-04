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

import com.example.demo.model.SaleItem;
import com.example.demo.response.MessageResponse;
import com.example.demo.service.SaleItemService;

@RestController
@RequestMapping("/SaleItems")
public class SaleItemController {
    @Autowired
    private SaleItemService SaleItemService;

    @PostMapping(value = "/saveSaleItem", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<MessageResponse> saveSaleItem(@RequestBody SaleItem SaleItem) {
        MessageResponse response = SaleItemService.saveSaleItem(SaleItem);
        HttpStatus httpStatus;

        switch (response.getMessage()) {
            case "SaleItem saved successfully":
                httpStatus = HttpStatus.CREATED;
                break;
            case "Invalid SaleItem":
                httpStatus = HttpStatus.BAD_REQUEST;
                break;
            default:
                httpStatus = HttpStatus.NOT_ACCEPTABLE;
                break;
        }

        return new ResponseEntity<>(response, httpStatus);
    }

    @PutMapping(value = "/updateSaleItem", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<MessageResponse> updateSaleItem(@RequestBody SaleItem SaleItem) {
        MessageResponse response = SaleItemService.updateSaleItem(SaleItem);
        HttpStatus httpStatus;

        switch (response.getMessage()) {
            case "SaleItem not exists":
                httpStatus = HttpStatus.NOT_FOUND;
                break;
            case "SaleItem updated successfully":
                httpStatus = HttpStatus.CREATED;
                break;
            case "Invalid SaleItem":
                httpStatus = HttpStatus.BAD_REQUEST;
                break;
            default:
                httpStatus = HttpStatus.NOT_ACCEPTABLE;
                break;
        }

        return new ResponseEntity<>(response, httpStatus);
    }

    @DeleteMapping(value = "/deleteSaleItem", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<MessageResponse> deleteSaleItem(@RequestBody SaleItem SaleItem) {
        MessageResponse response = SaleItemService.deleteSaleItem(SaleItem);
        HttpStatus httpStatus;

        switch (response.getMessage()) {
            case "SaleItem not exists":
                httpStatus = HttpStatus.NOT_FOUND;
                break;
            case "SaleItem deleted successfully":
                httpStatus = HttpStatus.CREATED;
                break;
            case "Invalid SaleItem":
                httpStatus = HttpStatus.BAD_REQUEST;
                break;
            default:
                httpStatus = HttpStatus.NOT_ACCEPTABLE;
                break;
        }

        return new ResponseEntity<>(response, httpStatus);
    }

    @GetMapping(value = "/getAllSaleItems", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<SaleItem>> getAllSaleItems() {
        List<SaleItem> SaleItems = SaleItemService.getAllSaleItems();
        return new ResponseEntity<>(SaleItems, HttpStatus.OK);
    }

    @GetMapping(value = "/getSaleItemById/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<SaleItem> getSaleItemById(@PathVariable Long id) {
        SaleItem SaleItem = SaleItemService.getSaleItemById(id);
        return new ResponseEntity<>(SaleItem, HttpStatus.OK);
    }
}
