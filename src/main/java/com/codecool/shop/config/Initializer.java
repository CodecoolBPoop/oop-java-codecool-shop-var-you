package com.codecool.shop.config;

import com.codecool.shop.dao.ProductCategoryDao;
import com.codecool.shop.dao.ProductDao;
import com.codecool.shop.dao.SupplierDao;
import com.codecool.shop.dao.implementation.ProductCategoryDaoMem;
import com.codecool.shop.dao.implementation.ProductDaoMem;
import com.codecool.shop.dao.implementation.SupplierDaoMem;
import com.codecool.shop.model.Product;
import com.codecool.shop.model.ProductCategory;
import com.codecool.shop.model.Supplier;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class Initializer implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        ProductDao productDataStore = ProductDaoMem.getInstance();
        ProductCategoryDao productCategoryDataStore = ProductCategoryDaoMem.getInstance();
        SupplierDao supplierDataStore = SupplierDaoMem.getInstance();

        //setting up a new supplier
        Supplier amazon = new Supplier("Amazon", "Digital content and services");
        supplierDataStore.add(amazon);
        Supplier lenovo = new Supplier("Lenovo", "Computers");
        supplierDataStore.add(lenovo);
        Supplier chineseStore = new Supplier("Chinese store", "Stuff you'll need");
        supplierDataStore.add(chineseStore);
        Supplier pharmacy = new Supplier("Pharmacy", "Being sick or sick of it all is not a shame.");
        supplierDataStore.add(pharmacy);
        Supplier spar = new Supplier("Spar", "At least it's close");

        //setting up a new product category
        ProductCategory tablet = new ProductCategory("Tablet", "Hardware", "A tablet computer, commonly shortened to tablet, is a thin, flat mobile computer with a touchscreen display.");
        productCategoryDataStore.add(tablet);
        ProductCategory medicine = new ProductCategory("Medicine", "Health", "Medicines will (try to) keep you healthy");
        productCategoryDataStore.add(medicine);
        ProductCategory other = new ProductCategory("Other", "Other", "Stuff");
        productCategoryDataStore.add(other);

        //setting up products and printing it
        productDataStore.add(new Product("Amazon Fire", 49.9f, "USD", "Fantastic price. Large content ecosystem. Good parental controls. Helpful technical support.", tablet, amazon));
        productDataStore.add(new Product("Lenovo IdeaPad Miix 700", 479, "USD", "Keyboard cover is included. Fanless Core m5 processor. Full-size USB ports. Adjustable kickstand.", tablet, lenovo));
        productDataStore.add(new Product("Amazon Fire HD 8", 89, "USD", "Amazon's latest Fire HD 8 tablet is a great value for media consumption.", tablet, amazon));
        productDataStore.add(new Product("Power strip", 1500, "HUF", "You don't have to steal from ProgBasics anymore.", other, chineseStore));
        productDataStore.add(new Product("Blanket", 1000, "HUF", "It's really cold in here. It's not just you.", other, chineseStore));
        productDataStore.add(new Product("Bucket", 1000, "HUF", "For your tears.", other, chineseStore));
        productDataStore.add(new Product("Plush", 300, "HUF", "", other, chineseStore));
        productDataStore.add(new Product("Duck", 300, "HUF", "", other, chineseStore));
        productDataStore.add(new Product("500 Ft", 635, "HUF", "You can buy other stuff with this guy.", other, spar));
        productDataStore.add(new Product("Milk", 300, "HUF", "", other, spar));
        productDataStore.add(new Product("Coffee", 2500, "HUF", "", other, spar));
        productDataStore.add(new Product("Eyeglasses",6500, "HUF", "", medicine, pharmacy));
        productDataStore.add(new Product("Zoloft", 3500, "HUF", "When Prozac No Longer Helps", medicine, pharmacy));
        productDataStore.add(new Product("Brintellix", 2000, "HUF", "You'll love this beautiful combination of insomnia, chest pain and hypomania", medicine, pharmacy));
        productDataStore.add(new Product("Remeron", 3000, "HUF", "You'll get fat and sleepy", medicine, pharmacy));
        productDataStore.add(new Product("Xanax", 1000, "HUF", "You can't really overdose with this dose", medicine, pharmacy));
        productDataStore.add(new Product("Frontin", 900, "HUF", "Same as Xanax but much more Nemzeti", medicine, pharmacy));
        productDataStore.add(new Product("Dormicum", 1000, "HUF","For those times when you're in your bed, and your head's just stuck in an infinite loop", medicine, pharmacy));










    }
}
