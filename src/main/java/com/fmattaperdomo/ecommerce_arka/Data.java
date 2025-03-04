package com.fmattaperdomo.ecommerce_arka;

import com.fmattaperdomo.ecommerce_arka.entities.Category;
import com.fmattaperdomo.ecommerce_arka.entities.Product;
import com.fmattaperdomo.ecommerce_arka.entities.User;

public class Data {

    public static Product createproduct001(){
        Category category001 = new Category(1L, "Category001");
        User user001 = new User(1L, "user1","user1@gmail.com","password1");
        return new Product(1L, "Product 001", "", "Description 001", 100, 100000, 0, 100000,category001,user001);
    }
    public static Product createproduct002(){
        Category category001 = new Category(1L, "Category001");
        User user001 = new User(1L, "user1","user1@gmail.com","password1");
        return new Product(2L, "Product 002", "", "Description 002", 200, 200000, 0, 200000,category001,user001);
    }
    public static Category category001(){
        return  new Category(1L, "Category001");
    }

}
