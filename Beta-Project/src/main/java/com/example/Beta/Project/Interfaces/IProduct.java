package com.example.Beta.Project.Interfaces;

import com.example.Beta.Project.DAO.ProductDao;
import com.example.Beta.Project.Models.Product;

import java.util.List;

public interface IProduct {
    public Product save(Product product);
    public List<Product> getAll();
    public Product getOneById(String id);
}
