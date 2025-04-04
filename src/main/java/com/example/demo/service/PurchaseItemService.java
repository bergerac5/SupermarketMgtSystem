package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.PurchaseItem;
import com.example.demo.repository.PurchaseItemRepository;
import com.example.demo.response.MessageResponse;

@Service
public class PurchaseItemService {

    @Autowired
    private PurchaseItemRepository PurchaseItemRep;

    // save the PurchaseItem
    public MessageResponse savePurchaseItem(PurchaseItem PurchaseItem) {
        MessageResponse resp = new MessageResponse();
        if (PurchaseItem != null) {

            PurchaseItemRep.save(PurchaseItem);
            resp.setMessage("PurchaseItem saved successfully");

        } else {
            resp.setMessage("Invalid PurchaseItem");
        }
        return resp;
    }

    // update the PurchaseItem
    public MessageResponse updatePurchaseItem(PurchaseItem PurchaseItem) {
        MessageResponse resp = new MessageResponse();
        if (PurchaseItem != null) {
            boolean checkPurchaseItemId = PurchaseItemRep.existsById(PurchaseItem.getId());
            if (checkPurchaseItemId == true) {
                PurchaseItemRep.save(PurchaseItem);
                resp.setMessage("PurchaseItem updated successfully");
                ;
            } else {
                resp.setMessage("PurchaseItem not exists");
            }
        } else {
            resp.setMessage("Invalid PurchaseItem");
        }
        return resp;
    }

    // delete the PurchaseItem
    public MessageResponse deletePurchaseItem(PurchaseItem PurchaseItem) {
        MessageResponse resp = new MessageResponse();
        if (PurchaseItem != null) {
            boolean checkPurchaseItemId = PurchaseItemRep.existsById(PurchaseItem.getId());
            if (checkPurchaseItemId == true) {
                PurchaseItemRep.delete(PurchaseItem);
                resp.setMessage("PurchaseItem deleted successfully");

            } else {
                resp.setMessage("PurchaseItem not exists");
            }
        } else {
            resp.setMessage("Invalid PurchaseItem");
        }
        return resp;
    }

    // get all PurchaseItems
    public List<PurchaseItem> getAllPurchaseItems() {
        return PurchaseItemRep.findAll();
    }

    // get PurchaseItem by id
    public PurchaseItem getPurchaseItemById(Long id) {
        return PurchaseItemRep.findById(id).orElse(null);
    }

}
