package com.curso.workshopjavafxjdbc.model.dao;

import com.curso.demodaojdbc.db.DB;
import com.curso.workshopjavafxjdbc.model.dao.impl.DepartmentDaoJDBC;
import com.curso.workshopjavafxjdbc.model.dao.impl.SellerDaoJDBC;

public class DaoFactory {
    public static SellerDao createSellerDao() {
        return new SellerDaoJDBC(DB.getConnection());
    }

    public static DepartmentDao createDepartmentDao() {
        return new DepartmentDaoJDBC(DB.getConnection());
    }
}
