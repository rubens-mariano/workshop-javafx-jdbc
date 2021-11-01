package com.curso.workshopjavafxjdbc.model.dao.impl;

import com.curso.demodaojdbc.db.DB;
import com.curso.demodaojdbc.db.DbException;
import com.curso.workshopjavafxjdbc.model.dao.SellerDao;
import com.curso.workshopjavafxjdbc.model.entities.Department;
import com.curso.workshopjavafxjdbc.model.entities.Seller;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SellerDaoJDBC implements SellerDao {

    private Connection connection;

    public SellerDaoJDBC(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void insert(Seller seller) {
        PreparedStatement preparedStatement = null;

        try {
            preparedStatement = connection.prepareStatement(
                    "INSERT INTO seller " +
                            "(name, email, birthdate, basesalary, departmentid) " +
                            "VALUES (?, ?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS
            );
            preparedStatement.setString(1, seller.getName());
            preparedStatement.setString(2, seller.getEmail());
            preparedStatement.setDate(3, new java.sql.Date(seller.getBirthDate().getTime()));
            preparedStatement.setDouble(4, seller.getBaseSalary());
            preparedStatement.setInt(5, seller.getDepartment().getId());

            int rowsAffected = preparedStatement.executeUpdate();

            if (rowsAffected > 0) {
                ResultSet resultSet = preparedStatement.getGeneratedKeys();
                if(resultSet.next()) {
                    int id = resultSet.getInt(1);
                    seller.setId(id);
                }
                DB.closeResult(resultSet);
            } else {
                throw new DbException("Erro inesperado! Nenhuma linha foi afetada");
            }
        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        } finally {
            DB.closeStatement(preparedStatement);
        }
    }

    @Override
    public void update(Seller seller) {
        PreparedStatement preparedStatement = null;

        try {
            preparedStatement = connection.prepareStatement(
                    "UPDATE seller SET " +
                            "name=?, email=?, birthdate=?, basesalary=?, departmentid=? " +
                            "WHERE id=?",
                    Statement.RETURN_GENERATED_KEYS
            );
            preparedStatement.setString(1, seller.getName());
            preparedStatement.setString(2, seller.getEmail());
            preparedStatement.setDate(3, new java.sql.Date(seller.getBirthDate().getTime()));
            preparedStatement.setDouble(4, seller.getBaseSalary());
            preparedStatement.setInt(5, seller.getDepartment().getId());
            preparedStatement.setInt(6, seller.getId());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        }finally {
            DB.closeStatement(preparedStatement);
        }
    }

    @Override
    public void deleteById(Integer id) {
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement(
                    "DELETE FROM seller WHERE id=?"
            );
            preparedStatement.setInt(1, id);

            int rows = preparedStatement.executeUpdate();
            if (rows == 0) {
                throw new DbException("Erro ao deletar o id = " + id);
            }
        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        } finally {
            DB.closeStatement(preparedStatement);
        }

    }

    @Override
    public Seller findByID(Integer id) {
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            preparedStatement = connection.prepareStatement(
                    "SELECT seller.*, department.Name AS DepName " +
                            "FROM seller " +
                            "INNER JOIN department ON seller.departmentid = department.id " +
                            "WHERE seller.id = ?");

            preparedStatement.setInt(1, id);
            resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                Department department = instantiateDepartment(resultSet);
                Seller seller = instantiateSeller(resultSet, department);
                return seller;
            }
            return null;
        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        } finally {
            DB.closeStatement(preparedStatement);
            DB.closeResult(resultSet);
        }

    }

    @Override
    public List<Seller> findAll() {
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            preparedStatement = connection.prepareStatement(
                    "SELECT seller.*, department.name as DepName " +
                            "FROM seller " +
                            "INNER JOIN department ON seller.departmentid = department.id " +
                            "ORDER BY name"
            );

            resultSet = preparedStatement.executeQuery();
            List<Seller> list = new ArrayList<>();
            Map<Integer, Department> map = new HashMap<>();
            while (resultSet.next()) {
                Department department = map.get(resultSet.getInt("departmentid"));

                if (department == null) {
                    department = instantiateDepartment(resultSet);
                    map.put(resultSet.getInt("departmentid"), department);
                }
                Seller seller = instantiateSeller(resultSet, department);
                list.add(seller);
            }
            return list;
        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        }finally {
            DB.closeStatement(preparedStatement);
            DB.closeResult(resultSet);
        }
    }

    @Override
    public List<Seller> findByDepartment(Department department) {
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            preparedStatement = connection.prepareStatement(
                    "SELECT seller.*, department.name as DepName " +
                            "FROM seller " +
                            "INNER JOIN department ON seller.departmentid = department.id " +
                            "WHERE departmentid = ? " +
                            "ORDER BY name");

            preparedStatement.setInt(1, department.getId());
            resultSet = preparedStatement.executeQuery();

            List<Seller> list = new ArrayList<>();

            Map<Integer, Department> map = new HashMap<>();

            while (resultSet.next()) {

                Department department1 = map.get(resultSet.getInt("departmentid"));

                if (department1 == null) {
                    department1 = instantiateDepartment(resultSet);
                    map.put(resultSet.getInt("departmentid"), department1);
                }

                Seller seller = instantiateSeller(resultSet, department1);
                list.add(seller);
            }
            return list;
        } catch (SQLException e) {
            throw  new DbException(e.getMessage());
        } finally {
            DB.closeStatement(preparedStatement);
            DB.closeResult(resultSet);
        }
    }

    private Department instantiateDepartment(ResultSet resultSet) throws SQLException {
        Department department = new Department();
        department.setId(resultSet.getInt("departmentid"));
        department.setName(resultSet.getString("DepName"));
        return department;
    }

    private Seller instantiateSeller(ResultSet resultSet, Department department) throws SQLException {
        Seller seller = new Seller();
        seller.setId(resultSet.getInt("id"));
        seller.setName(resultSet.getString("name"));
        seller.setEmail(resultSet.getString("email"));
        seller.setBaseSalary(resultSet.getDouble("basesalary"));
        seller.setBirthDate(resultSet.getDate("birthdate"));
        seller.setDepartment(department);
        return seller;
    }
}
