package com.curso.workshopjavafxjdbc.model.services;

import com.curso.workshopjavafxjdbc.model.dao.DaoFactory;
import com.curso.workshopjavafxjdbc.model.dao.DepartmentDao;
import com.curso.workshopjavafxjdbc.model.entities.Department;

import java.util.ArrayList;
import java.util.List;

public class DepartmentService {
    private final DepartmentDao departmentDao = DaoFactory.createDepartmentDao();

    public List<Department> findAll() {
        return departmentDao.findAll();
    }
}
