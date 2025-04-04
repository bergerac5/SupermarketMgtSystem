package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.Purchase;
import com.example.demo.repository.PurchaseRepository;
import com.example.demo.response.MessageResponse;

@Service
public class PurchaseService {
    @Autowired
    private PurchaseRepository PurchaseRep;

    // save the Purchase
    public MessageResponse savePurchase(Purchase Purchase) {
        MessageResponse resp = new MessageResponse();
        if (Purchase != null) {

            PurchaseRep.save(Purchase);
            resp.setMessage("Purchase saved successfully");

        } else {
            resp.setMessage("Invalid Purchase");
        }
        return resp;
    }

    // update the Purchase
    public MessageResponse updatePurchase(Purchase Purchase) {
        MessageResponse resp = new MessageResponse();
        if (Purchase != null) {
            boolean checkPurchaseId = PurchaseRep.existsById(Purchase.getId());
            if (checkPurchaseId == true) {
                PurchaseRep.save(Purchase);
                resp.setMessage("Purchase updated successfully");
                ;
            } else {
                resp.setMessage("Purchase not exists");
            }
        } else {
            resp.setMessage("Invalid Purchase");
        }
        return resp;
    }

    // delete the Purchase
    public MessageResponse deletePurchase(Purchase Purchase) {
        MessageResponse resp = new MessageResponse();
        if (Purchase != null) {
            boolean checkPurchaseId = PurchaseRep.existsById(Purchase.getId());
            if (checkPurchaseId == true) {
                PurchaseRep.delete(Purchase);
                resp.setMessage("Purchase deleted successfully");

            } else {
                resp.setMessage("Purchase not exists");
            }
        } else {
            resp.setMessage("Invalid Purchase");
        }
        return resp;
    }

    // get all Purchases
    public List<Purchase> getAllPurchases() {
        return PurchaseRep.findAll();
    }

    // get Purchase by id
    public Purchase getPurchaseById(Long id) {
        return PurchaseRep.findById(id).orElse(null);
    }
}
