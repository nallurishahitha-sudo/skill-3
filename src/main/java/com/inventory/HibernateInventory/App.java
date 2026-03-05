package com.inventory.HibernateInventory;

import com.inventory.dao.ProductDAO;
import com.inventory.entity.Product;

public class App {

    public static void main(String[] args) {

        ProductDAO dao = new ProductDAO();

        // Insert Products
        Product p1 = new Product("Laptop", "Gaming Laptop", 75000, 10);
        Product p2 = new Product("Mouse", "Wireless Mouse", 1200, 50);

        dao.saveProduct(p1);
        dao.saveProduct(p2);

        // -------- Skill 3 Operations --------

        // Sorting
        System.out.println("Products Sorted By Price:");
        dao.getProductsSortedByPrice().forEach(p ->
                System.out.println(p.getName() + " - " + p.getPrice()));

        // Pagination
        System.out.println("\nPagination (First 2 Products):");
        dao.getProductsPagination(0,2).forEach(p ->
                System.out.println(p.getName()));

        // Aggregate Functions
        System.out.println("\nProduct Statistics:");
        dao.getProductStatistics();

        // Search using LIKE
        System.out.println("\nSearch Product (lap):");
        dao.searchProduct("lap").forEach(p ->
                System.out.println(p.getName()));
    }
}