package com.curso.workshopjavafxjdbc.model.dao.impl;

import com.curso.demodaojdbc.db.DB;
import com.curso.demodaojdbc.db.DbException;
import com.curso.workshopjavafxjdbc.model.dao.DepartmentDao;
import com.curso.workshopjavafxjdbc.model.entities.Department;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DepartmentDaoJDBC implements DepartmentDao {
    private final Connection connection;

    public DepartmentDaoJDBC(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void insert(Department department) {
        PreparedStatement preparedStatement = null;

        try {
            preparedStatement = connection.prepareStatement(
                    "INSERT INTO department " +
                            "(name) " +
                            "VALUES (?) ",
                    Statement.RETURN_GENERATED_KEYS
            );

            preparedStatement.setString(1, department.getName());

            int rowsAffected = preparedStatement.executeUpdate();

            if (rowsAffected > 0) {
                ResultSet resultSet = preparedStatement.getGeneratedKeys();
                if (resultSet.next()) {
                    int id = resultSet.getInt(1);
                    department.setId(id);
                }
            } else {
                throw new DbException("Erro insperado! Não foi possível inserir o departamento");
            }
        } catch (SQLException e) {
            throw new DbException("\n" + e.getMessage());
        } finally {
            DB.closeStatement(preparedStatement);
        }
    }

    @Override
    public void update(Department department) {
        PreparedStatement preparedStatement = null;

        try {
            preparedStatement = connection.prepareStatement(
                    "UPDATE department SET " +
                            "name=? " +
                            "WHERE id=?"
            );

            preparedStatement.setString(1, department.getName());
            preparedStatement.setInt(2, department.getId());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        } finally {
            DB.closeStatement(preparedStatement);
        }
    }

    @Override
    public void deleteById(Integer id) {
        PreparedStatement preparedStatement = null;

        try {
            preparedStatement = connection.prepareStatement(
                    "DELETE FROM department WHERE id=?"
            );

            preparedStatement.setInt(1, id);

            int rows = preparedStatement.executeUpdate();
            if (rows == 0) {
                throw new DbException("Erro ao deletar o id = " + id);
            }
        }catch (SQLException e) {
            throw new DbException(e.getMessage());
        } finally {
            DB.closeStatement(preparedStatement);
        }
    }

    @Override
    public Department findById(Integer id) {
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            preparedStatement = connection.prepareStatement(
                    "SELECT department.* " +
                            "FROM department " +
                            "WHERE id=? " +
                            "ORDER BY name "
            );

            preparedStatement.setInt(1, id);
            resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                return implementationDepartment(resultSet);
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
    public List<Department> findAll() {
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            preparedStatement = connection.prepareStatement(
                    "SELECT department.* " +
                            "FROM department " +
                            "ORDER BY name "
            );
            resultSet = preparedStatement.executeQuery();
            List<Department> list = new ArrayList<>();

            while (resultSet.next()) {
                Department department = implementationDepartment(resultSet);
                list.add(department);
            }
            return list;
        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        } finally {
            DB.closeStatement(preparedStatement);
            DB.closeResult(resultSet);
        }
    }

    public Department implementationDepartment(ResultSet resultSet) throws SQLException {
        Department department = new Department();
        department.setId(resultSet.getInt("id"));
        department.setName(resultSet.getString("name"));
        return department;
    }
}
