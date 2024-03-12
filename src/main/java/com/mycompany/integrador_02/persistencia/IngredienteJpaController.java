/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.integrador_02.persistencia;

import com.mycompany.integrador_02.logica.Ingrediente;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import com.mycompany.integrador_02.logica.Producto;
import com.mycompany.integrador_02.persistencia.exceptions.NonexistentEntityException;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author rafae
 */
public class IngredienteJpaController implements Serializable {

    public IngredienteJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public IngredienteJpaController() {
         emf = Persistence.createEntityManagerFactory("int02JPAPU");
    }

    
    public void create(Ingrediente ingrediente) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Producto producto = ingrediente.getProducto();
            if (producto != null) {
                producto = em.getReference(producto.getClass(), producto.getId());
                ingrediente.setProducto(producto);
            }
            em.persist(ingrediente);
            if (producto != null) {
                producto.getIngredientes().add(ingrediente);
                producto = em.merge(producto);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Ingrediente ingrediente) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Ingrediente persistentIngrediente = em.find(Ingrediente.class, ingrediente.getId());
            Producto productoOld = persistentIngrediente.getProducto();
            Producto productoNew = ingrediente.getProducto();
            if (productoNew != null) {
                productoNew = em.getReference(productoNew.getClass(), productoNew.getId());
                ingrediente.setProducto(productoNew);
            }
            ingrediente = em.merge(ingrediente);
            if (productoOld != null && !productoOld.equals(productoNew)) {
                productoOld.getIngredientes().remove(ingrediente);
                productoOld = em.merge(productoOld);
            }
            if (productoNew != null && !productoNew.equals(productoOld)) {
                productoNew.getIngredientes().add(ingrediente);
                productoNew = em.merge(productoNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                int id = ingrediente.getId();
                if (findIngrediente(id) == null) {
                    throw new NonexistentEntityException("The ingrediente with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(int id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Ingrediente ingrediente;
            try {
                ingrediente = em.getReference(Ingrediente.class, id);
                ingrediente.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The ingrediente with id " + id + " no longer exists.", enfe);
            }
            Producto producto = ingrediente.getProducto();
            if (producto != null) {
                producto.getIngredientes().remove(ingrediente);
                producto = em.merge(producto);
            }
            em.remove(ingrediente);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Ingrediente> findIngredienteEntities() {
        return findIngredienteEntities(true, -1, -1);
    }

    public List<Ingrediente> findIngredienteEntities(int maxResults, int firstResult) {
        return findIngredienteEntities(false, maxResults, firstResult);
    }

    private List<Ingrediente> findIngredienteEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Ingrediente.class));
            Query q = em.createQuery(cq);
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public Ingrediente findIngrediente(int id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Ingrediente.class, id);
        } finally {
            em.close();
        }
    }

    public int getIngredienteCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Ingrediente> rt = cq.from(Ingrediente.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
