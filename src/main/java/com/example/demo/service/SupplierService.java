package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.Supplier;
import com.example.demo.repository.SupplierRepository;
import com.example.demo.response.MessageResponse;

@Service
public class SupplierService {
    @Autowired
    private SupplierRepository SupplierRep;

    // save the Supplier
    public MessageResponse saveSupplier(Supplier Supplier) {
        MessageResponse resp = new MessageResponse();
        if (Supplier != null) {

            SupplierRep.save(Supplier);
            resp.setMessage("Supplier saved successfully");

        } else {
            resp.setMessage("Invalid Supplier");
        }
        return resp;
    }

    // update the Supplier
    public MessageResponse updateSupplier(Supplier Supplier) {
        MessageResponse resp = new MessageResponse();
        if (Supplier != null) {
            boolean checkSupplierId = SupplierRep.existsById(Supplier.getId());
            if (checkSupplierId == true) {
                SupplierRep.save(Supplier);
                resp.setMessage("Supplier updated successfully");
                ;
            } else {
                resp.setMessage("Supplier not exists");
            }
        } else {
            resp.setMessage("Invalid Supplier");
        }
        return resp;
    }

    // delete the Supplier
    public MessageResponse deleteSupplier(Supplier Supplier) {
        MessageResponse resp = new MessageResponse();
        if (Supplier != null) {
            boolean checkSupplierId = SupplierRep.existsById(Supplier.getId());
            if (checkSupplierId == true) {
                SupplierRep.delete(Supplier);
                resp.setMessage("Supplier deleted successfully");

            } else {
                resp.setMessage("Supplier not exists");
            }
        } else {
            resp.setMessage("Invalid Supplier");
        }
        return resp;
    }

    // get all Suppliers
    public List<Supplier> getAllSuppliers() {
        return SupplierRep.findAll();
    }

    // get Supplier by id
    public Supplier getSupplierById(Long id) {
        return SupplierRep.findById(id).orElse(null);
    }
}
