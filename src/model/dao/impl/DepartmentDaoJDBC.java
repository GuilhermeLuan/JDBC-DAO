package model.dao.impl;

import db.DB;
import db.DbExecption;
import model.dao.DepartmentDao;
import model.entities.Department;
import model.entities.Seller;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DepartmentDaoJDBC implements DepartmentDao {

    private Connection connection;

    public DepartmentDaoJDBC(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void insert(Department obj) {
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement  = connection.prepareStatement("INSERT INTO department\n" +
                    "(Name)\n" +
                    "VALUES\n" +
                    "(?)", Statement.RETURN_GENERATED_KEYS);

            preparedStatement.setString(1,obj.getName());

            int rowsAffected = preparedStatement.executeUpdate();

            if (rowsAffected > 0){
                ResultSet resultSet = preparedStatement.getGeneratedKeys();
                if(resultSet.next()){
                    int id = resultSet.getInt(1);
                    obj.setId(id);
                }
                DB.closeResultSet(resultSet);
            }
            else {
                throw new DbExecption("Unexpected error! No rows affected!");
            }

        } catch (SQLException e) {
            throw new DbExecption(e.getMessage());
        } finally {
            DB.closeStatement(preparedStatement);
        }
    }

    @Override
    public void update(Department obj) {
        PreparedStatement preparedStatement;
        try {
            preparedStatement = connection.prepareStatement(
                    "UPDATE department\n" +
                            "SET Name = ?\n" +
                            "WHERE Id = ?",
                    Statement.RETURN_GENERATED_KEYS
            );

            preparedStatement.setString(1,obj.getName());
            preparedStatement.setInt(2, obj.getId());

            preparedStatement.executeUpdate();


        } catch (SQLException e) {
            throw new DbExecption(e.getMessage());
        }
    }

    @Override
    public void deleteById(Integer id) {

    }

    @Override
    public Department findById(Integer id) {
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            String sql = "SELECT seller.*,department.Name as DepName\n" +
                    "FROM seller INNER JOIN department\n" +
                    "ON seller.DepartmentId = department.Id\n" +
                    "WHERE DepartmentId = ?\n" +
                    "ORDER BY Name";

            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()){
                return instantiateDepartment(resultSet);
            }
            return null;
        } catch (SQLException e) {
            throw new DbExecption(e.getMessage());
        } finally {
            DB.closeStatement(preparedStatement);
            DB.closeResultSet(resultSet);
        }
    }

    private Department instantiateDepartment(ResultSet rs) throws SQLException {
        Department dep = new Department();
        dep.setId(rs.getInt("Id"));
        dep.setName(rs.getString("Name"));
        return dep;
    }

    @Override
    public List<Department> findAll() {
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {

            String sql = "SELECT * FROM department ORDER BY Name";

            preparedStatement = connection.prepareStatement(sql);
            resultSet = preparedStatement.executeQuery();

            List<Department> list = new ArrayList<>();

            while (resultSet.next()){
                Department department = instantiateDepartment(resultSet);
                list.add(department);
            }
            return list;

        } catch (SQLException e){
            throw new DbExecption(e.getMessage());
        } finally {
            DB.closeStatement(preparedStatement);
            DB.closeResultSet(resultSet);
        }
    }
}
