package com.inventory.main;

import java.util.List;

import com.inventory.dao.ProductDAO;
import com.inventory.entity.Product;

public class App {

    public static void main(String[] args) {

        ProductDAO dao = new ProductDAO();

        // Insert Products
        dao.saveProduct(new Product("Laptop", "Electronics", 80000, 10));
        dao.saveProduct(new Product("Mouse", "Electronics", 500, 50));
        dao.saveProduct(new Product("Keyboard", "Electronics", 1500, 30));
        dao.saveProduct(new Product("Chair", "Furniture", 3000, 20));
        dao.saveProduct(new Product("Notebook", "Stationery", 50, 100));

        System.out.println("Products inserted successfully");

        // Sorting
        System.out.println("\nProducts Sorted by Price:");
        List<Product> sortedProducts = dao.getProductsSortedByPrice();

        for (Product p : sortedProducts) {
            System.out.println(p.getName() + " - " + p.getPrice());
        }

        // Pagination
        System.out.println("\nPagination (First 3 Products):");
        List<Product> page = dao.getProductsPagination(0, 3);

        for (Product p : page) {
            System.out.println(p.getName() + " - " + p.getPrice());
        }

        // Aggregate Functions
        dao.getProductStatistics();

        // LIKE Search
        System.out.println("\nSearch Results:");
        List<Product> search = dao.searchProduct("Lap");

        for (Product p : search) {
            System.out.println(p.getName());
        }
    }
}