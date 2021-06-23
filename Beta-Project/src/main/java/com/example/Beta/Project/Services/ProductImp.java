package com.example.Beta.Project.Services;

import com.example.Beta.Project.DAO.ProductDao;
import com.example.Beta.Project.Interfaces.IProduct;
import com.example.Beta.Project.Models.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ProductImp implements IProduct {

    @Autowired
    private ProductDao repo;

    @Override
    @Transactional
    public Product save(Product product) {
        return repo.save(product);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Product> getAll() {
        return (List<Product>) repo.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Product getOneById(String id) {
        return repo.findById(id).orElse(null);
    }
}
