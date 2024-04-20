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
import com.mycompany.integrador_02.logica.Camarero;
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
public class PedidoJpaController implements Serializable {

    public PedidoJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }

    public PedidoJpaController() {
        emf = Persistence.createEntityManagerFactory("int02JPAPU");
    }
    
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Pedido pedido) {
        if (pedido.getProductos() == null) {
            pedido.setProductos(new ArrayList<Producto>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Camarero unCamarero = pedido.getUnCamarero();
            if (unCamarero != null) {
                unCamarero = em.getReference(unCamarero.getClass(), unCamarero.getId());
                pedido.setUnCamarero(unCamarero);
            }
            List<Producto> attachedProductos = new ArrayList<Producto>();
            for (Producto productosProductoToAttach : pedido.getProductos()) {
                productosProductoToAttach = em.getReference(productosProductoToAttach.getClass(), productosProductoToAttach.getId());
                attachedProductos.add(productosProductoToAttach);
            }
            pedido.setProductos(attachedProductos);
            em.persist(pedido);
            if (unCamarero != null) {
                unCamarero.getPedidos().add(pedido);
                unCamarero = em.merge(unCamarero);
            }
            for (Producto productosProducto : pedido.getProductos()) {
                Pedido oldUnPedidoOfProductosProducto = productosProducto.getUnPedido();
                productosProducto.setUnPedido(pedido);
                productosProducto = em.merge(productosProducto);
                if (oldUnPedidoOfProductosProducto != null) {
                    oldUnPedidoOfProductosProducto.getProductos().remove(productosProducto);
                    oldUnPedidoOfProductosProducto = em.merge(oldUnPedidoOfProductosProducto);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Pedido pedido) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Pedido persistentPedido = em.find(Pedido.class, pedido.getId());
            Camarero unCamareroOld = persistentPedido.getUnCamarero();
            Camarero unCamareroNew = pedido.getUnCamarero();
            List<Producto> productosOld = persistentPedido.getProductos();
            List<Producto> productosNew = pedido.getProductos();
            if (unCamareroNew != null) {
                unCamareroNew = em.getReference(unCamareroNew.getClass(), unCamareroNew.getId());
                pedido.setUnCamarero(unCamareroNew);
            }
            List<Producto> attachedProductosNew = new ArrayList<Producto>();
            for (Producto productosNewProductoToAttach : productosNew) {
                productosNewProductoToAttach = em.getReference(productosNewProductoToAttach.getClass(), productosNewProductoToAttach.getId());
                attachedProductosNew.add(productosNewProductoToAttach);
            }
            productosNew = attachedProductosNew;
            pedido.setProductos(productosNew);
            pedido = em.merge(pedido);
            if (unCamareroOld != null && !unCamareroOld.equals(unCamareroNew)) {
                unCamareroOld.getPedidos().remove(pedido);
                unCamareroOld = em.merge(unCamareroOld);
            }
            if (unCamareroNew != null && !unCamareroNew.equals(unCamareroOld)) {
                unCamareroNew.getPedidos().add(pedido);
                unCamareroNew = em.merge(unCamareroNew);
            }
            for (Producto productosOldProducto : productosOld) {
                if (!productosNew.contains(productosOldProducto)) {
                    productosOldProducto.setUnPedido(null);
                    productosOldProducto = em.merge(productosOldProducto);
                }
            }
            for (Producto productosNewProducto : productosNew) {
                if (!productosOld.contains(productosNewProducto)) {
                    Pedido oldUnPedidoOfProductosNewProducto = productosNewProducto.getUnPedido();
                    productosNewProducto.setUnPedido(pedido);
                    productosNewProducto = em.merge(productosNewProducto);
                    if (oldUnPedidoOfProductosNewProducto != null && !oldUnPedidoOfProductosNewProducto.equals(pedido)) {
                        oldUnPedidoOfProductosNewProducto.getProductos().remove(productosNewProducto);
                        oldUnPedidoOfProductosNewProducto = em.merge(oldUnPedidoOfProductosNewProducto);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                int id = pedido.getId();
                if (findPedido(id) == null) {
                    throw new NonexistentEntityException("The pedido with id " + id + " no longer exists.");
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
            Pedido pedido;
            try {
                pedido = em.getReference(Pedido.class, id);
                pedido.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The pedido with id " + id + " no longer exists.", enfe);
            }
            Camarero unCamarero = pedido.getUnCamarero();
            if (unCamarero != null) {
                unCamarero.getPedidos().remove(pedido);
                unCamarero = em.merge(unCamarero);
            }
            List<Producto> productos = pedido.getProductos();
            for (Producto productosProducto : productos) {
                productosProducto.setUnPedido(null);
                productosProducto = em.merge(productosProducto);
            }
            em.remove(pedido);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Pedido> findPedidoEntities() {
        return findPedidoEntities(true, -1, -1);
    }

    public List<Pedido> findPedidoEntities(int maxResults, int firstResult) {
        return findPedidoEntities(false, maxResults, firstResult);
    }

    private List<Pedido> findPedidoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Pedido.class));
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

    public Pedido findPedido(int id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Pedido.class, id);
        } finally {
            em.close();
        }
    }

    public int getPedidoCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Pedido> rt = cq.from(Pedido.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
