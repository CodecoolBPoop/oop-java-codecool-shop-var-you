package com.codecool.shop.dao;

import com.codecool.shop.config.Props;
import com.codecool.shop.dao.implementation.jdbc.*;
import com.codecool.shop.dao.implementation.mem.ProductCategoryDaoMem;
import com.codecool.shop.dao.implementation.mem.ProductDaoMem;
import com.codecool.shop.dao.implementation.mem.ShoppingCartDaoMem;
import com.codecool.shop.dao.implementation.mem.SupplierDaoMem;

public final class DaoSwitcher {

    private enum daos {
        MEM,
        JDBC
    }


    private daos activeDao;

    private DaoSwitcher(){
        switch (Props.getInstance().getDAOtype()){
            case "MEM": activeDao = daos.MEM;
            break;
            case "JDBC": activeDao = daos.JDBC;
            break;
        }
    }

    private static DaoSwitcher instance = null;

    public static DaoSwitcher getInstance(){
        if (instance == null) {
            instance = new DaoSwitcher();
        }
        return instance;
    }

    public UserDao getUserDao(){
        switch (activeDao) {
            case MEM:
                return null;
            case JDBC:
                return new UserDaoJDBC();
        }
        return new UserDaoJDBC();
    }

    public ProductDao getProductDao(){
        switch (activeDao) {
            case MEM:
                return ProductDaoMem.getInstance();
            case JDBC:
                return new ProductDaoJDBC();
        }
        return ProductDaoMem.getInstance();
    }

    public ProductCategoryDao getProductCategoryDao() {
        switch (activeDao) {
            case MEM:
                return ProductCategoryDaoMem.getInstance();
            case JDBC:
                return new ProductCategoryDaoJDBC();
        }
        return ProductCategoryDaoMem.getInstance();
    }


    public ShoppingCartDao getShopplingCartDao(){
        switch (activeDao) {
            case MEM:
                return ShoppingCartDaoMem.getInstance();
            case JDBC:
                return new ShoppingCartDaoJDBC();
        }
        return ShoppingCartDaoMem.getInstance();
    }

    public SupplierDao getSupplierDao(){
        switch (activeDao) {
            case MEM:
                return SupplierDaoMem.getInstance();
            case JDBC:
                return new SupplierDaoJDBC();
        }
        return SupplierDaoMem.getInstance();
    }


}
