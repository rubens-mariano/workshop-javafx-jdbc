package com.curso.workshopjavafxjdbc.model.dao;

import com.curso.workshopjavafxjdbc.model.entities.Department;

import java.util.List;

public interface DepartmentDao {
    void insert(Department department);
    void update(Department department);
    void deleteById(Integer id);
    Department findById(Integer id);
    List<Department> findAll();
}
