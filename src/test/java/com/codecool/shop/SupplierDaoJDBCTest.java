package com.codecool.shop;

import com.codecool.shop.dao.implementation.jdbc.SupplierDaoJDBC;
import com.codecool.shop.model.Supplier;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;


public class SupplierDaoJDBCTest {

    Supplier testSupplier = new Supplier("testsupplier", "Only for testing, do not see this in production enviroment");
    SupplierDaoJDBC testDaoJDBC = new SupplierDaoJDBC();


    private Supplier findSupplierByName(String name, List<Supplier> listOfSuppliers){
        for (Supplier supplier : listOfSuppliers) {
            if (supplier.getName().equals(name)) {
                return supplier;
            }
        }
        return null;
    }

    @Test
    public void testIfAddAdds() {
        testDaoJDBC.add(testSupplier);
        assertEquals(testSupplier.getName(), findSupplierByName(testSupplier.getName(), testDaoJDBC.getAll()).getName());
    }

    }
