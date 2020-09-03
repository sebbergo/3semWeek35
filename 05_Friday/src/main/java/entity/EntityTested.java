/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.util.Date;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author sebas
 */
public class EntityTested {

    public static void main(String[] args) {
        // Open a database connection
        // (create a new database if it doesn't exist yet):
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("pu");
        EntityManager em = emf.createEntityManager();
        
        Date date = java.util.Calendar.getInstance().getTime();

        Customer customer1 = new Customer("Sebastian", "Hansen", date);
        Customer customer2 = new Customer("August", "Gade", date);
        Customer customer3 = new Customer("Casper", "Utke", date);
        Customer customer4 = new Customer("Oliver", "Roat", date);
        
        try {
            // Store 1000 Point objects in the database:
            em.getTransaction().begin();
            
            em.persist(customer1);
            em.persist(customer2);
            em.persist(customer3);
            em.persist(customer4);
            
            em.getTransaction().commit();

        } finally {
            // Close the database connection:
            em.close();
            emf.close();
        }
    }
}
