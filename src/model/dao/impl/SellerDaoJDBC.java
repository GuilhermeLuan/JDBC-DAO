package model.dao.impl;

import db.DB;
import db.DbExecption;
import model.dao.SellerDao;
import model.entities.Department;
import model.entities.Seller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class SellerDaoJDBC implements SellerDao {
    private Connection connection;

    public SellerDaoJDBC(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void insert(Seller obj) {

    }

    @Override
    public void update(Seller obj) {

    }

    @Override
    public void deleteById(Integer id) {

    }

    @Override
    public Seller findById(Integer id) {
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {

            String sql = "SELECT seller.*,department.Name as DepName\n" +
                    "FROM seller INNER JOIN department\n" +
                    "ON seller.DepartmentId = department.Id\n" +
                    "WHERE seller.Id = ?";

            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()){
                Department department = new Department();
                department.setId(resultSet.getInt("DepartmentId"));
                department.setName(resultSet.getString("DepName"));

                Seller seller = new Seller();
                seller.setId(resultSet.getInt("Id"));
                seller.setName(resultSet.getString("Name"));
                seller.setEmail(resultSet.getString("Email"));
                seller.setBaseSalary(resultSet.getDouble("BaseSalary"));
                seller.setBirthDate(resultSet.getDate("BirthDate"));
                seller.setDepartment(department);
                return seller;
            }

        } catch (SQLException e){
            throw new DbExecption(e.getMessage());
        } finally {
            DB.closeStatement(preparedStatement);
            DB.closeResultSet(resultSet);
        }
        return null;
    }

    @Override
    public List<Seller> findAll() {
        return null;
    }

    @Override
    public List<Seller> findByDepartment(Department department) {
        return null;
    }
}
