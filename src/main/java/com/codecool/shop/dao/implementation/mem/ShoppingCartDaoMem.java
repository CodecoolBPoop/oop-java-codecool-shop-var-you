package com.codecool.shop.dao.implementation.mem;

import com.codecool.shop.dao.ShoppingCartDao;
import com.codecool.shop.model.Product;

import java.util.ArrayList;
import java.util.List;

public class ShoppingCartDaoMem implements ShoppingCartDao {
  private List<Product> CartItems = new ArrayList<>();
  private static ShoppingCartDao instance = null;
  private float totalPrice = 0;


  private ShoppingCartDaoMem() { }

  public float getTotalPrice() {
    return totalPrice;
  }

  public static ShoppingCartDao getInstance() {
    if (instance == null) {
      instance = new ShoppingCartDaoMem();
    }
    return instance;
  }

  public void add(Product product) {
    CartItems.add(product);
    totalPrice += product.getDefaultPrice();
  }

   public void remove(int id) {
     //totalPrice -= CartItems.(find(id)).getPrice();
     CartItems.remove(find(id));
   }

  @Override
  public Product find(int id) {
    return CartItems.stream().filter(t -> t.getId() == id).findFirst().orElse(null);
  }

  @Override
  public List<Product> getAll() {
    return CartItems;
  }
}
