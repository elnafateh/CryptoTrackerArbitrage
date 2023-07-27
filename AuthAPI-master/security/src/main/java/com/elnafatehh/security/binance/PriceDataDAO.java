//package com.elnafatehh.security.binance;
//
//import org.springframework.context.annotation.Configuration;
//
//import javax.persistence.EntityManager;
//import javax.persistence.EntityManagerFactory;
//import javax.persistence.Persistence;
//
//public class PriceDataDAO {
//
//    // Define a private field to store the EntityManagerFactory object
//    private EntityManagerFactory entityManagerFactory;
//    // Define a constructor that takes the persistence unit name as a parameter
//    // and creates the EntityManagerFactory object
//    public PriceDataDAO (String persistenceUnitName){
//        entityManagerFactory = Persistence.createEntityManagerFactory(persistenceUnitName);
//    }
//    // Define a method that inserts a PriceData object into the database using JPA
//    public void insertPriceData(PriceData priceData){
//        // Get an EntityManager object from the EntityManagerFactory
//        EntityManager entityManager= entityManagerFactory.createEntityManager();
//        // Begin a transaction with the database
//        entityManager.getTransaction().begin();
//        // Persist the PriceData object using the EntityManager object
//        entityManager.persist(priceData);
//        // Commit the transaction and close the EntityManager
//        entityManager.getTransaction().commit();
//        entityManager.close();
//    }
//    // Define a method that closes the EntityManagerFactory
//    public void close(){
//        entityManagerFactory.close();
//    }
//}
