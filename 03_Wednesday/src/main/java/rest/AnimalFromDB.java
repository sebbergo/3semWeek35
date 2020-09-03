/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rest;

import com.google.gson.Gson;
import entity.Animal;
import java.util.List;
import java.util.Random;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;

/**
 * REST Web Service
 *
 * @author sebas
 */
@Path("animals_db")
public class AnimalFromDB {

    @Context
    private UriInfo context;

    private static EntityManagerFactory emf = Persistence.createEntityManagerFactory("pu");

    /**
     * Creates a new instance of AnimalFromDB
     */
    public AnimalFromDB() {
    }

    @Path("animals")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getAnimals() {
        EntityManager em = emf.createEntityManager();
        try {
            TypedQuery<Animal> query = em.createQuery("SELECT a FROM Animal a", Animal.class);
            List<Animal> animals = query.getResultList();
            return new Gson().toJson(animals);
        } finally {
            em.close();
        }
    }
    
    @Path("animalbyid/{id}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getAnimalByID(@PathParam("id") int id) {
        EntityManager em = emf.createEntityManager();
        try {
            TypedQuery query = em.createQuery("SELECT a FROM Animal a WHERE a.id = '"+ id +"'", Animal.class);
            Object animal = query.getSingleResult();
            return new Gson().toJson(animal);
        } finally {
            em.close();
        }
    }
    
    @Path("animalbytype/{type}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getAnimalByType(@PathParam("type") String type) {
        EntityManager em = emf.createEntityManager();
        try {
            TypedQuery query = em.createQuery("SELECT a FROM Animal a WHERE a.type = '"+ type +"'", Animal.class);
            Object animal = query.getSingleResult();
            return new Gson().toJson(animal);
        } finally {
            em.close();
        }
    }
    
    @Path("animalrandom")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getRandomAnimal() {
        EntityManager em = emf.createEntityManager();
        Random random = new Random();
        try {
            TypedQuery<Animal> query = em.createQuery("SELECT a FROM Animal a", Animal.class);
            
            int countID = query.getResultList().size() + 1;

            int randomID = random.nextInt(countID - 1) + 1;
            
            
            TypedQuery query2 = em.createQuery("SELECT a FROM Animal a WHERE a.id = '"+ randomID +"'", Animal.class);
            Object animal = query2.getSingleResult();
            return new Gson().toJson(animal);
        } finally {
            em.close();
        }
    }

}
