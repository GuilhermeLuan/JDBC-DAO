package application;

import db.DB;
import model.dao.DaoFactory;
import model.dao.SellerDao;
import model.dao.impl.SellerDaoJDBC;
import model.entities.Seller;

public class Main {
    public static void main(String[] args) {
        SellerDao sellerDao = DaoFactory.createSellerDao();
        System.out.println(sellerDao.findById(5));
    }
}