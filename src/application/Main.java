package application;

import db.DB;
import model.dao.DaoFactory;
import model.dao.DepartmentDao;
import model.dao.SellerDao;
import model.dao.impl.SellerDaoJDBC;
import model.entities.Department;
import model.entities.Seller;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        System.out.println("---- Test findById ----");
        SellerDao sellerDao = DaoFactory.createSellerDao();
        System.out.println(sellerDao.findById(5));

        System.out.println("---- Test findByIdDepartment ----");
        Department department = new Department(2, null);
        List<Seller> list = sellerDao.findByDepartment(department);
        for (Seller obj :
                list) {
            System.out.println(obj);
        }
    }
}