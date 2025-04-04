package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.Sale;
import com.example.demo.repository.SaleRepository;
import com.example.demo.response.MessageResponse;

@Service
public class SaleService {

    @Autowired
    private SaleRepository SaleRep;

    // save the Sale
    public MessageResponse saveSale(Sale Sale) {
        MessageResponse resp = new MessageResponse();
        if (Sale != null) {

            SaleRep.save(Sale);
            resp.setMessage("Sale saved successfully");

        } else {
            resp.setMessage("Invalid Sale");
        }
        return resp;
    }

    // update the Sale
    public MessageResponse updateSale(Sale Sale) {
        MessageResponse resp = new MessageResponse();
        if (Sale != null) {
            boolean checkSaleId = SaleRep.existsById(Sale.getId());
            if (checkSaleId == true) {
                SaleRep.save(Sale);
                resp.setMessage("Sale updated successfully");
                ;
            } else {
                resp.setMessage("Sale not exists");
            }
        } else {
            resp.setMessage("Invalid Sale");
        }
        return resp;
    }

    // delete the Sale
    public MessageResponse deleteSale(Sale Sale) {
        MessageResponse resp = new MessageResponse();
        if (Sale != null) {
            boolean checkSaleId = SaleRep.existsById(Sale.getId());
            if (checkSaleId == true) {
                SaleRep.delete(Sale);
                resp.setMessage("Sale deleted successfully");

            } else {
                resp.setMessage("Sale not exists");
            }
        } else {
            resp.setMessage("Invalid Sale");
        }
        return resp;
    }

    // get all Sales
    public List<Sale> getAllSales() {
        return SaleRep.findAll();
    }

    // get Sale by id
    public Sale getSaleById(Long id) {
        return SaleRep.findById(id).orElse(null);
    }

}
