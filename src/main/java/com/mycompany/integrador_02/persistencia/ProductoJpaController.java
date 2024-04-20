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
import com.mycompany.integrador_02.logica.Pedido;
import com.mycompany.integrador_02.logica.Barista;
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
public class ProductoJpaController implements Serializable {

    public ProductoJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public ProductoJpaController() {
        emf = Persistence.createEntityManagerFactory("int02JPAPU");
    }

    
    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Producto producto) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Pedido unPedido = producto.getUnPedido();
            if (unPedido != null) {
                unPedido = em.getReference(unPedido.getClass(), unPedido.getId());
                producto.setUnPedido(unPedido);
            }
            Barista unBarista = producto.getUnBarista();
            if (unBarista != null) {
                unBarista = em.getReference(unBarista.getClass(), unBarista.getId());
                producto.setUnBarista(unBarista);
            }
            em.persist(producto);
            if (unPedido != null) {
                unPedido.getProductos().add(producto);
                unPedido = em.merge(unPedido);
            }
            if (unBarista != null) {
                unBarista.getProductos().add(producto);
                unBarista = em.merge(unBarista);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Producto producto) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Producto persistentProducto = em.find(Producto.class, producto.getId());
            Pedido unPedidoOld = persistentProducto.getUnPedido();
            Pedido unPedidoNew = producto.getUnPedido();
            Barista unBaristaOld = persistentProducto.getUnBarista();
            Barista unBaristaNew = producto.getUnBarista();
            if (unPedidoNew != null) {
                unPedidoNew = em.getReference(unPedidoNew.getClass(), unPedidoNew.getId());
                producto.setUnPedido(unPedidoNew);
            }
            if (unBaristaNew != null) {
                unBaristaNew = em.getReference(unBaristaNew.getClass(), unBaristaNew.getId());
                producto.setUnBarista(unBaristaNew);
            }
            producto = em.merge(producto);
            if (unPedidoOld != null && !unPedidoOld.equals(unPedidoNew)) {
                unPedidoOld.getProductos().remove(producto);
                unPedidoOld = em.merge(unPedidoOld);
            }
            if (unPedidoNew != null && !unPedidoNew.equals(unPedidoOld)) {
                unPedidoNew.getProductos().add(producto);
                unPedidoNew = em.merge(unPedidoNew);
            }
            if (unBaristaOld != null && !unBaristaOld.equals(unBaristaNew)) {
                unBaristaOld.getProductos().remove(producto);
                unBaristaOld = em.merge(unBaristaOld);
            }
            if (unBaristaNew != null && !unBaristaNew.equals(unBaristaOld)) {
                unBaristaNew.getProductos().add(producto);
                unBaristaNew = em.merge(unBaristaNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                int id = producto.getId();
                if (findProducto(id) == null) {
                    throw new NonexistentEntityException("The producto with id " + id + " no longer exists.");
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
            Producto producto;
            try {
                producto = em.getReference(Producto.class, id);
                producto.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The producto with id " + id + " no longer exists.", enfe);
            }
            Pedido unPedido = producto.getUnPedido();
            if (unPedido != null) {
                unPedido.getProductos().remove(producto);
                unPedido = em.merge(unPedido);
            }
            Barista unBarista = producto.getUnBarista();
            if (unBarista != null) {
                unBarista.getProductos().remove(producto);
                unBarista = em.merge(unBarista);
            }
            em.remove(producto);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Producto> findProductoEntities() {
        return findProductoEntities(true, -1, -1);
    }

    public List<Producto> findProductoEntities(int maxResults, int firstResult) {
        return findProductoEntities(false, maxResults, firstResult);
    }

    private List<Producto> findProductoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Producto.class));
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

    public Producto findProducto(int id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Producto.class, id);
        } finally {
            em.close();
        }
    }

    public int getProductoCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Producto> rt = cq.from(Producto.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
