package com.inventory.dao;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import java.util.List;

import com.inventory.entity.Product;
import com.inventory.util.HibernateUtil;

public class ProductDAO {

    // Save Product
    public void saveProduct(Product product) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();

        session.save(product);

        tx.commit();
        session.close();
    }

    // Get Product by ID
    public Product getProduct(int id) {
        Session session = HibernateUtil.getSessionFactory().openSession();

        Product product = session.get(Product.class, id);

        session.close();
        return product;
    }

    // Update Product
    public void updateProduct(int id, double price, int quantity) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();

        Product product = session.get(Product.class, id);

        if (product != null) {
            product.setPrice(price);
            product.setQuantity(quantity);
            session.update(product);
        } else {
            System.out.println("Product not found!");
        }

        tx.commit();
        session.close();
    }

    // Delete Product
    public void deleteProduct(int id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();

        Product product = session.get(Product.class, id);

        if (product != null) {
            session.delete(product);
        } else {
            System.out.println("Product not found!");
        }

        tx.commit();
        session.close();
    }

    // Skill 3 - Sorting (ORDER BY)
    public List<Product> getProductsSortedByPrice() {

        Session session = HibernateUtil.getSessionFactory().openSession();

        String hql = "FROM Product p ORDER BY p.price ASC";
        Query<Product> query = session.createQuery(hql, Product.class);

        List<Product> products = query.list();

        session.close();
        return products;
    }

    // Skill 3 - Pagination
    public List<Product> getProductsPagination(int start, int limit) {

        Session session = HibernateUtil.getSessionFactory().openSession();

        Query<Product> query = session.createQuery("FROM Product", Product.class);

        query.setFirstResult(start);
        query.setMaxResults(limit);

        List<Product> products = query.list();

        session.close();
        return products;
    }

    // Skill 3 - Aggregate Functions
    public void getProductStatistics() {

        Session session = HibernateUtil.getSessionFactory().openSession();

        Long count = (Long) session.createQuery("SELECT COUNT(p) FROM Product p").uniqueResult();
        Double avg = (Double) session.createQuery("SELECT AVG(p.price) FROM Product p").uniqueResult();
        Double max = (Double) session.createQuery("SELECT MAX(p.price) FROM Product p").uniqueResult();
        Double min = (Double) session.createQuery("SELECT MIN(p.price) FROM Product p").uniqueResult();

        System.out.println("\nProduct Statistics");
        System.out.println("------------------");
        System.out.println("Total Products: " + count);
        System.out.println("Average Price: " + avg);
        System.out.println("Maximum Price: " + max);
        System.out.println("Minimum Price: " + min);

        session.close();
    }

    // Skill 3 - Search using LIKE
    public List<Product> searchProduct(String keyword) {

        Session session = HibernateUtil.getSessionFactory().openSession();

        Query<Product> query = session.createQuery(
                "FROM Product p WHERE p.name LIKE :name", Product.class);

        query.setParameter("name", "%" + keyword + "%");

        List<Product> products = query.list();

        session.close();
        return products;
    }
}