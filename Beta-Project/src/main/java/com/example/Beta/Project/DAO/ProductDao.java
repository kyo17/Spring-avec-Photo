package com.example.Beta.Project.DAO;

import com.example.Beta.Project.Models.Product;
import org.springframework.data.repository.CrudRepository;

public interface ProductDao extends CrudRepository<Product, String> {

}
