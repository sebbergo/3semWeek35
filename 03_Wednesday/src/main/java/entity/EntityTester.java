/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.util.Random;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

/**
 *
 * @author sebas
 */
public class EntityTester {

    private static EntityManagerFactory emf = Persistence.createEntityManagerFactory("pu");

    public static void main(String[] args) {
        /*
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("pu");
        EntityManager em = emf.createEntityManager();
        Animal a1 = new Animal("Lion", "Rawr");
        Animal a2 = new Animal("Seagull", "Irhh");
        try {
            em.getTransaction().begin();
            em.persist(a1);
            em.persist(a2);
            em.getTransaction().commit();
            //Verify that books are managed and has been given a database id
            System.out.println(a1.getId());
            System.out.println(a2.getId());

        } finally {
            em.close();
        }
    }
         */

        EntityManager em = emf.createEntityManager();
        Random random = new Random();
        TypedQuery<Animal> query = em.createQuery("SELECT a FROM Animal a", Animal.class);

        int countID = query.getResultList().size() + 1;

        int randomID = random.nextInt(countID - 1) + 1;
        System.out.println(randomID);
    }
}
