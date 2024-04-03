/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.integrador_02.persistencia;

import com.mycompany.integrador_02.logica.Barista;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import com.mycompany.integrador_02.logica.Producto;
import com.mycompany.integrador_02.persistencia.exceptions.NonexistentEntityException;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author rafae
 */
public class BaristaJpaController implements Serializable {

    public BaristaJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }

    public BaristaJpaController() {
         emf = Persistence.createEntityManagerFactory("int02JPAPU");
    }
    
    
    
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Barista barista) {
        if (barista.getProductos() == null) {
            barista.setProductos(new ArrayList<Producto>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<Producto> attachedProductos = new ArrayList<Producto>();
            for (Producto productosProductoToAttach : barista.getProductos()) {
                productosProductoToAttach = em.getReference(productosProductoToAttach.getClass(), productosProductoToAttach.getId());
                attachedProductos.add(productosProductoToAttach);
            }
            barista.setProductos(attachedProductos);
            em.persist(barista);
            for (Producto productosProducto : barista.getProductos()) {
                Barista oldBaristaOfProductosProducto = productosProducto.getBarista();
                productosProducto.setBarista(barista);
                productosProducto = em.merge(productosProducto);
                if (oldBaristaOfProductosProducto != null) {
                    oldBaristaOfProductosProducto.getProductos().remove(productosProducto);
                    oldBaristaOfProductosProducto = em.merge(oldBaristaOfProductosProducto);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Barista barista) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Barista persistentBarista = em.find(Barista.class, barista.getId());
            List<Producto> productosOld = persistentBarista.getProductos();
            List<Producto> productosNew = barista.getProductos();
            List<Producto> attachedProductosNew = new ArrayList<Producto>();
            for (Producto productosNewProductoToAttach : productosNew) {
                productosNewProductoToAttach = em.getReference(productosNewProductoToAttach.getClass(), productosNewProductoToAttach.getId());
                attachedProductosNew.add(productosNewProductoToAttach);
            }
            productosNew = attachedProductosNew;
            barista.setProductos(productosNew);
            barista = em.merge(barista);
            for (Producto productosOldProducto : productosOld) {
                if (!productosNew.contains(productosOldProducto)) {
                    productosOldProducto.setBarista(null);
                    productosOldProducto = em.merge(productosOldProducto);
                }
            }
            for (Producto productosNewProducto : productosNew) {
                if (!productosOld.contains(productosNewProducto)) {
                    Barista oldBaristaOfProductosNewProducto = productosNewProducto.getBarista();
                    productosNewProducto.setBarista(barista);
                    productosNewProducto = em.merge(productosNewProducto);
                    if (oldBaristaOfProductosNewProducto != null && !oldBaristaOfProductosNewProducto.equals(barista)) {
                        oldBaristaOfProductosNewProducto.getProductos().remove(productosNewProducto);
                        oldBaristaOfProductosNewProducto = em.merge(oldBaristaOfProductosNewProducto);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                int id = barista.getId();
                if (findBarista(id) == null) {
                    throw new NonexistentEntityException("The barista with id " + id + " no longer exists.");
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
            Barista barista;
            try {
                barista = em.getReference(Barista.class, id);
                barista.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The barista with id " + id + " no longer exists.", enfe);
            }
            List<Producto> productos = barista.getProductos();
            for (Producto productosProducto : productos) {
                productosProducto.setBarista(null);
                productosProducto = em.merge(productosProducto);
            }
            em.remove(barista);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Barista> findBaristaEntities() {
        return findBaristaEntities(true, -1, -1);
    }

    public List<Barista> findBaristaEntities(int maxResults, int firstResult) {
        return findBaristaEntities(false, maxResults, firstResult);
    }

    private List<Barista> findBaristaEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Barista.class));
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

    public Barista findBarista(int id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Barista.class, id);
        } finally {
            em.close();
        }
    }

    public int getBaristaCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Barista> rt = cq.from(Barista.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
