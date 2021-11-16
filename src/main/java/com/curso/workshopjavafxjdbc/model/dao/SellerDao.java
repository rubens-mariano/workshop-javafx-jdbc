package com.curso.workshopjavafxjdbc.model.dao;

import com.curso.workshopjavafxjdbc.model.entities.Department;
import com.curso.workshopjavafxjdbc.model.entities.Seller;

import java.util.List;

public interface SellerDao {
    void insert(Seller seller);
    void update(Seller seller);
    void deleteById(Integer id);
    Seller findByID(Integer id);
    List<Seller> findAll();
    List<Seller> findByDepartment(Department department);
}
