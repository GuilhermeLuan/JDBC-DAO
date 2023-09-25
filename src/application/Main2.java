package application;

import model.dao.DaoFactory;
import model.dao.DepartmentDao;
import model.entities.Department;
import model.entities.Seller;

import java.util.List;

public class Main2 {
    public static void main(String[] args) {
        DepartmentDao departmentDao = DaoFactory.createDepartmentDao();

        System.out.println("---- Test findById ----");
        System.out.println(departmentDao.findById(1));

        System.out.println("---- Test findAll ----");
        List<Department> list2 = departmentDao.findAll();
        for (Department obj :
                list2) {
            System.out.println(obj);
        }

        System.out.println("---- Test Insert ----");
        Department newDepartment = new Department(null, "Games");
        departmentDao.insert(newDepartment);
        System.out.println("Inserted! New id = " + newDepartment.getId());

    }
}
