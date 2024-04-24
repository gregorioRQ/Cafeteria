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
import com.mycompany.integrador_02.logica.Cafe;
import com.mycompany.integrador_02.logica.Barista;
import com.mycompany.integrador_02.logica.Pedido;
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
public class ProductoJpaController implements Serializable {

    public ProductoJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public ProductoJpaController() {
        emf = Persistence.createEntityManagerFactory("int02JPAPU");
    }
    

    public void create(Producto producto) {
        if (producto.getPedidos() == null) {
            producto.setPedidos(new ArrayList<Pedido>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Cafe unCafe = producto.getUnCafe();
            if (unCafe != null) {
                unCafe = em.getReference(unCafe.getClass(), unCafe.getId());
                producto.setUnCafe(unCafe);
            }
            Barista unBarista = producto.getUnBarista();
            if (unBarista != null) {
                unBarista = em.getReference(unBarista.getClass(), unBarista.getId());
                producto.setUnBarista(unBarista);
            }
            List<Pedido> attachedPedidos = new ArrayList<Pedido>();
            for (Pedido pedidosPedidoToAttach : producto.getPedidos()) {
                pedidosPedidoToAttach = em.getReference(pedidosPedidoToAttach.getClass(), pedidosPedidoToAttach.getId());
                attachedPedidos.add(pedidosPedidoToAttach);
            }
            producto.setPedidos(attachedPedidos);
            em.persist(producto);
            if (unCafe != null) {
                Producto oldUnProductoOfUnCafe = unCafe.getUnProducto();
                if (oldUnProductoOfUnCafe != null) {
                    oldUnProductoOfUnCafe.setUnCafe(null);
                    oldUnProductoOfUnCafe = em.merge(oldUnProductoOfUnCafe);
                }
                unCafe.setUnProducto(producto);
                unCafe = em.merge(unCafe);
            }
            if (unBarista != null) {
                unBarista.getProductos().add(producto);
                unBarista = em.merge(unBarista);
            }
            for (Pedido pedidosPedido : producto.getPedidos()) {
                pedidosPedido.getProductos().add(producto);
                pedidosPedido = em.merge(pedidosPedido);
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
            Cafe unCafeOld = persistentProducto.getUnCafe();
            Cafe unCafeNew = producto.getUnCafe();
            Barista unBaristaOld = persistentProducto.getUnBarista();
            Barista unBaristaNew = producto.getUnBarista();
            List<Pedido> pedidosOld = persistentProducto.getPedidos();
            List<Pedido> pedidosNew = producto.getPedidos();
            if (unCafeNew != null) {
                unCafeNew = em.getReference(unCafeNew.getClass(), unCafeNew.getId());
                producto.setUnCafe(unCafeNew);
            }
            if (unBaristaNew != null) {
                unBaristaNew = em.getReference(unBaristaNew.getClass(), unBaristaNew.getId());
                producto.setUnBarista(unBaristaNew);
            }
            List<Pedido> attachedPedidosNew = new ArrayList<Pedido>();
            for (Pedido pedidosNewPedidoToAttach : pedidosNew) {
                pedidosNewPedidoToAttach = em.getReference(pedidosNewPedidoToAttach.getClass(), pedidosNewPedidoToAttach.getId());
                attachedPedidosNew.add(pedidosNewPedidoToAttach);
            }
            pedidosNew = attachedPedidosNew;
            producto.setPedidos(pedidosNew);
            producto = em.merge(producto);
            if (unCafeOld != null && !unCafeOld.equals(unCafeNew)) {
                unCafeOld.setUnProducto(null);
                unCafeOld = em.merge(unCafeOld);
            }
            if (unCafeNew != null && !unCafeNew.equals(unCafeOld)) {
                Producto oldUnProductoOfUnCafe = unCafeNew.getUnProducto();
                if (oldUnProductoOfUnCafe != null) {
                    oldUnProductoOfUnCafe.setUnCafe(null);
                    oldUnProductoOfUnCafe = em.merge(oldUnProductoOfUnCafe);
                }
                unCafeNew.setUnProducto(producto);
                unCafeNew = em.merge(unCafeNew);
            }
            if (unBaristaOld != null && !unBaristaOld.equals(unBaristaNew)) {
                unBaristaOld.getProductos().remove(producto);
                unBaristaOld = em.merge(unBaristaOld);
            }
            if (unBaristaNew != null && !unBaristaNew.equals(unBaristaOld)) {
                unBaristaNew.getProductos().add(producto);
                unBaristaNew = em.merge(unBaristaNew);
            }
            for (Pedido pedidosOldPedido : pedidosOld) {
                if (!pedidosNew.contains(pedidosOldPedido)) {
                    pedidosOldPedido.getProductos().remove(producto);
                    pedidosOldPedido = em.merge(pedidosOldPedido);
                }
            }
            for (Pedido pedidosNewPedido : pedidosNew) {
                if (!pedidosOld.contains(pedidosNewPedido)) {
                    pedidosNewPedido.getProductos().add(producto);
                    pedidosNewPedido = em.merge(pedidosNewPedido);
                }
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
            Cafe unCafe = producto.getUnCafe();
            if (unCafe != null) {
                unCafe.setUnProducto(null);
                unCafe = em.merge(unCafe);
            }
            Barista unBarista = producto.getUnBarista();
            if (unBarista != null) {
                unBarista.getProductos().remove(producto);
                unBarista = em.merge(unBarista);
            }
            List<Pedido> pedidos = producto.getPedidos();
            for (Pedido pedidosPedido : pedidos) {
                pedidosPedido.getProductos().remove(producto);
                pedidosPedido = em.merge(pedidosPedido);
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
