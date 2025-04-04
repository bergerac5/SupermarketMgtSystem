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

import com.example.demo.model.Sale;
import com.example.demo.response.MessageResponse;
import com.example.demo.service.SaleService;

@RestController
@RequestMapping("/sales")
public class SaleController {

    @Autowired
    private SaleService SaleService;

    @PostMapping(value = "/saveSale", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<MessageResponse> saveSale(@RequestBody Sale Sale) {
        MessageResponse response = SaleService.saveSale(Sale);
        HttpStatus httpStatus;

        switch (response.getMessage()) {
            case "Sale saved successfully":
                httpStatus = HttpStatus.CREATED;
                break;
            case "Invalid Sale":
                httpStatus = HttpStatus.BAD_REQUEST;
                break;
            default:
                httpStatus = HttpStatus.NOT_ACCEPTABLE;
                break;
        }

        return new ResponseEntity<>(response, httpStatus);
    }

    @PutMapping(value = "/updateSale", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<MessageResponse> updateSale(@RequestBody Sale Sale) {
        MessageResponse response = SaleService.updateSale(Sale);
        HttpStatus httpStatus;

        switch (response.getMessage()) {
            case "Sale not exists":
                httpStatus = HttpStatus.NOT_FOUND;
                break;
            case "Sale updated successfully":
                httpStatus = HttpStatus.CREATED;
                break;
            case "Invalid Sale":
                httpStatus = HttpStatus.BAD_REQUEST;
                break;
            default:
                httpStatus = HttpStatus.NOT_ACCEPTABLE;
                break;
        }

        return new ResponseEntity<>(response, httpStatus);
    }

    @DeleteMapping(value = "/deleteSale", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<MessageResponse> deleteSale(@RequestBody Sale Sale) {
        MessageResponse response = SaleService.deleteSale(Sale);
        HttpStatus httpStatus;

        switch (response.getMessage()) {
            case "Sale not exists":
                httpStatus = HttpStatus.NOT_FOUND;
                break;
            case "Sale deleted successfully":
                httpStatus = HttpStatus.CREATED;
                break;
            case "Invalid Sale":
                httpStatus = HttpStatus.BAD_REQUEST;
                break;
            default:
                httpStatus = HttpStatus.NOT_ACCEPTABLE;
                break;
        }

        return new ResponseEntity<>(response, httpStatus);
    }

    @GetMapping(value = "/getAllSales", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Sale>> getAllSales() {
        List<Sale> Sales = SaleService.getAllSales();
        return new ResponseEntity<>(Sales, HttpStatus.OK);
    }

    @GetMapping(value = "/getSaleById/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Sale> getSaleById(@PathVariable Long id) {
        Sale Sale = SaleService.getSaleById(id);
        return new ResponseEntity<>(Sale, HttpStatus.OK);
    }
}
