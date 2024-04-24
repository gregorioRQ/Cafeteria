/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.integrador_02.persistencia;

import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import com.mycompany.integrador_02.logica.Producto;
import com.mycompany.integrador_02.logica.Barista;
import com.mycompany.integrador_02.logica.Cafe;
import com.mycompany.integrador_02.persistencia.exceptions.NonexistentEntityException;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author rafae
 */
public class CafeJpaController implements Serializable {

    public CafeJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public CafeJpaController() {
        emf = Persistence.createEntityManagerFactory("int02JPAPU");
    }

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Cafe cafe) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Producto unProducto = cafe.getUnProducto();
            if (unProducto != null) {
                unProducto = em.getReference(unProducto.getClass(), unProducto.getId());
                cafe.setUnProducto(unProducto);
            }
            Barista unBarista = cafe.getUnBarista();
            if (unBarista != null) {
                unBarista = em.getReference(unBarista.getClass(), unBarista.getId());
                cafe.setUnBarista(unBarista);
            }
            em.persist(cafe);
            if (unProducto != null) {
                Cafe oldUnCafeOfUnProducto = unProducto.getUnCafe();
                if (oldUnCafeOfUnProducto != null) {
                    oldUnCafeOfUnProducto.setUnProducto(null);
                    oldUnCafeOfUnProducto = em.merge(oldUnCafeOfUnProducto);
                }
                unProducto.setUnCafe(cafe);
                unProducto = em.merge(unProducto);
            }
            if (unBarista != null) {
                unBarista.getVariedadesDeCafe().add(cafe);
                unBarista = em.merge(unBarista);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Cafe cafe) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Cafe persistentCafe = em.find(Cafe.class, cafe.getId());
            Producto unProductoOld = persistentCafe.getUnProducto();
            Producto unProductoNew = cafe.getUnProducto();
            Barista unBaristaOld = persistentCafe.getUnBarista();
            Barista unBaristaNew = cafe.getUnBarista();
            if (unProductoNew != null) {
                unProductoNew = em.getReference(unProductoNew.getClass(), unProductoNew.getId());
                cafe.setUnProducto(unProductoNew);
            }
            if (unBaristaNew != null) {
                unBaristaNew = em.getReference(unBaristaNew.getClass(), unBaristaNew.getId());
                cafe.setUnBarista(unBaristaNew);
            }
            cafe = em.merge(cafe);
            if (unProductoOld != null && !unProductoOld.equals(unProductoNew)) {
                unProductoOld.setUnCafe(null);
                unProductoOld = em.merge(unProductoOld);
            }
            if (unProductoNew != null && !unProductoNew.equals(unProductoOld)) {
                Cafe oldUnCafeOfUnProducto = unProductoNew.getUnCafe();
                if (oldUnCafeOfUnProducto != null) {
                    oldUnCafeOfUnProducto.setUnProducto(null);
                    oldUnCafeOfUnProducto = em.merge(oldUnCafeOfUnProducto);
                }
                unProductoNew.setUnCafe(cafe);
                unProductoNew = em.merge(unProductoNew);
            }
            if (unBaristaOld != null && !unBaristaOld.equals(unBaristaNew)) {
                unBaristaOld.getVariedadesDeCafe().remove(cafe);
                unBaristaOld = em.merge(unBaristaOld);
            }
            if (unBaristaNew != null && !unBaristaNew.equals(unBaristaOld)) {
                unBaristaNew.getVariedadesDeCafe().add(cafe);
                unBaristaNew = em.merge(unBaristaNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                int id = cafe.getId();
                if (findCafe(id) == null) {
                    throw new NonexistentEntityException("The cafe with id " + id + " no longer exists.");
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
            Cafe cafe;
            try {
                cafe = em.getReference(Cafe.class, id);
                cafe.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The cafe with id " + id + " no longer exists.", enfe);
            }
            Producto unProducto = cafe.getUnProducto();
            if (unProducto != null) {
                unProducto.setUnCafe(null);
                unProducto = em.merge(unProducto);
            }
            Barista unBarista = cafe.getUnBarista();
            if (unBarista != null) {
                unBarista.getVariedadesDeCafe().remove(cafe);
                unBarista = em.merge(unBarista);
            }
            em.remove(cafe);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Cafe> findCafeEntities() {
        return findCafeEntities(true, -1, -1);
    }

    public List<Cafe> findCafeEntities(int maxResults, int firstResult) {
        return findCafeEntities(false, maxResults, firstResult);
    }

    private List<Cafe> findCafeEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Cafe.class));
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

    public Cafe findCafe(int id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Cafe.class, id);
        } finally {
            em.close();
        }
    }

    public int getCafeCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Cafe> rt = cq.from(Cafe.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
