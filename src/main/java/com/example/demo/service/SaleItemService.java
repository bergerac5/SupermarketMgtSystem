package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.SaleItem;
import com.example.demo.repository.SaleItemRepository;
import com.example.demo.response.MessageResponse;

@Service
public class SaleItemService {
    @Autowired
    private SaleItemRepository SaleItemRep;

    // save the SaleItem
    public MessageResponse saveSaleItem(SaleItem SaleItem) {
        MessageResponse resp = new MessageResponse();
        if (SaleItem != null) {

            SaleItemRep.save(SaleItem);
            resp.setMessage("SaleItem saved successfully");

        } else {
            resp.setMessage("Invalid SaleItem");
        }
        return resp;
    }

    // update the SaleItem
    public MessageResponse updateSaleItem(SaleItem SaleItem) {
        MessageResponse resp = new MessageResponse();
        if (SaleItem != null) {
            boolean checkSaleItemId = SaleItemRep.existsById(SaleItem.getId());
            if (checkSaleItemId == true) {
                SaleItemRep.save(SaleItem);
                resp.setMessage("SaleItem updated successfully");
                ;
            } else {
                resp.setMessage("SaleItem not exists");
            }
        } else {
            resp.setMessage("Invalid SaleItem");
        }
        return resp;
    }

    // delete the SaleItem
    public MessageResponse deleteSaleItem(SaleItem SaleItem) {
        MessageResponse resp = new MessageResponse();
        if (SaleItem != null) {
            boolean checkSaleItemId = SaleItemRep.existsById(SaleItem.getId());
            if (checkSaleItemId == true) {
                SaleItemRep.delete(SaleItem);
                resp.setMessage("SaleItem deleted successfully");

            } else {
                resp.setMessage("SaleItem not exists");
            }
        } else {
            resp.setMessage("Invalid SaleItem");
        }
        return resp;
    }

    // get all SaleItems
    public List<SaleItem> getAllSaleItems() {
        return SaleItemRep.findAll();
    }

    // get SaleItem by id
    public SaleItem getSaleItemById(Long id) {
        return SaleItemRep.findById(id).orElse(null);
    }
}
