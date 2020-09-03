/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dbfacade;

import entity.Customer;
import java.util.Date;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

/**
 *
 * @author sebas
 */
public class CustomerFacade {

    private static EntityManagerFactory emf;
    private static CustomerFacade instance;
    static Date date = java.util.Calendar.getInstance().getTime();

    public static void main(String[] args) {
        emf = Persistence.createEntityManagerFactory("pu");
        CustomerFacade facade = CustomerFacade.getCustomerFacade(emf);
        /*
        //Create customers
        Customer c1 = facade.addCustomer("Sebastian", "Hansen", date);
        Customer c2 = facade.addCustomer("August", "Gade", date);
        Customer c3 = facade.addCustomer("Oliver", "Jørgensen", date);
         */

        //Find number of customers
        //facade.allCustomers();
        //Get the number of customers
        //facade.getNumberOfCustomer();
        //Find customer by ID
        //facade.findByID(1);
        //Find customer by lastname
        facade.findByLastName("Hansen");

    }

    private CustomerFacade() {
    }

    public static CustomerFacade getCustomerFacade(EntityManagerFactory _emf) {
        if (instance == null) {
            emf = _emf;
            instance = new CustomerFacade();
        }
        return instance;
    }

    public List<Customer> getNumberOfCustomer() {
        EntityManager em = emf.createEntityManager();
        try {
            TypedQuery query
                    = em.createQuery("SELECT COUNT(customer) FROM Customer customer", Customer.class);
            System.out.println(query.getResultList().toString());
            return query.getResultList();
        } finally {
            em.close();
        }
    }

    public Customer addCustomer(String firstName, String lastName, Date date) {
        Customer customer = new Customer(firstName, lastName, date);
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(customer);
            em.getTransaction().commit();
            return customer;
        } finally {
            em.close();
        }
    }

    public Customer findByID(int id) {
        EntityManager em = emf.createEntityManager();
        try {
            Customer customer = em.find(Customer.class, id);
            System.out.println(customer.toString());
            return customer;
        } finally {
            em.close();
        }
    }

    public List<Customer> findByLastName(String lastName) {
        EntityManager em = emf.createEntityManager();
        try {
            TypedQuery<Customer> query
                    = em.createQuery("SELECT customer FROM Customer customer WHERE customer.lastName = '"+ lastName +"'", Customer.class);
            System.out.println(query.getResultList().toString());
            return query.getResultList();
        } finally {
            em.close();
        }
    }

    public List<Customer> allCustomers() {
        EntityManager em = emf.createEntityManager();
        try {
            TypedQuery<Customer> query
                    = em.createQuery("SELECT customer FROM Customer customer", Customer.class);
            System.out.println(query.getResultList().toString());
            return query.getResultList();
        } finally {
            em.close();
        }
    }

}
