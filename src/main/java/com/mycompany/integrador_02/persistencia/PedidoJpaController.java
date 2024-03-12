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
import com.mycompany.integrador_02.logica.Mesa;
import com.mycompany.integrador_02.logica.Camarero;
import com.mycompany.integrador_02.logica.Cliente;
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
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public PedidoJpaController() {
         emf = Persistence.createEntityManagerFactory("int02JPAPU");
    }

    
    public void create(Pedido pedido) {
        if (pedido.getProductos() == null) {
            pedido.setProductos(new ArrayList<Producto>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Mesa mesa = pedido.getMesa();
            if (mesa != null) {
                mesa = em.getReference(mesa.getClass(), mesa.getId());
                pedido.setMesa(mesa);
            }
            Camarero camarero = pedido.getCamarero();
            if (camarero != null) {
                camarero = em.getReference(camarero.getClass(), camarero.getId());
                pedido.setCamarero(camarero);
            }
            Cliente cliente = pedido.getCliente();
            if (cliente != null) {
                cliente = em.getReference(cliente.getClass(), cliente.getId());
                pedido.setCliente(cliente);
            }
            List<Producto> attachedProductos = new ArrayList<Producto>();
            for (Producto productosProductoToAttach : pedido.getProductos()) {
                productosProductoToAttach = em.getReference(productosProductoToAttach.getClass(), productosProductoToAttach.getId());
                attachedProductos.add(productosProductoToAttach);
            }
            pedido.setProductos(attachedProductos);
            em.persist(pedido);
            if (mesa != null) {
                mesa.getPedidos().add(pedido);
                mesa = em.merge(mesa);
            }
            if (camarero != null) {
                camarero.getPedidos().add(pedido);
                camarero = em.merge(camarero);
            }
            if (cliente != null) {
                cliente.getPedidos().add(pedido);
                cliente = em.merge(cliente);
            }
            for (Producto productosProducto : pedido.getProductos()) {
                productosProducto.getPedidos().add(pedido);
                productosProducto = em.merge(productosProducto);
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
            Mesa mesaOld = persistentPedido.getMesa();
            Mesa mesaNew = pedido.getMesa();
            Camarero camareroOld = persistentPedido.getCamarero();
            Camarero camareroNew = pedido.getCamarero();
            Cliente clienteOld = persistentPedido.getCliente();
            Cliente clienteNew = pedido.getCliente();
            List<Producto> productosOld = persistentPedido.getProductos();
            List<Producto> productosNew = pedido.getProductos();
            if (mesaNew != null) {
                mesaNew = em.getReference(mesaNew.getClass(), mesaNew.getId());
                pedido.setMesa(mesaNew);
            }
            if (camareroNew != null) {
                camareroNew = em.getReference(camareroNew.getClass(), camareroNew.getId());
                pedido.setCamarero(camareroNew);
            }
            if (clienteNew != null) {
                clienteNew = em.getReference(clienteNew.getClass(), clienteNew.getId());
                pedido.setCliente(clienteNew);
            }
            List<Producto> attachedProductosNew = new ArrayList<Producto>();
            for (Producto productosNewProductoToAttach : productosNew) {
                productosNewProductoToAttach = em.getReference(productosNewProductoToAttach.getClass(), productosNewProductoToAttach.getId());
                attachedProductosNew.add(productosNewProductoToAttach);
            }
            productosNew = attachedProductosNew;
            pedido.setProductos(productosNew);
            pedido = em.merge(pedido);
            if (mesaOld != null && !mesaOld.equals(mesaNew)) {
                mesaOld.getPedidos().remove(pedido);
                mesaOld = em.merge(mesaOld);
            }
            if (mesaNew != null && !mesaNew.equals(mesaOld)) {
                mesaNew.getPedidos().add(pedido);
                mesaNew = em.merge(mesaNew);
            }
            if (camareroOld != null && !camareroOld.equals(camareroNew)) {
                camareroOld.getPedidos().remove(pedido);
                camareroOld = em.merge(camareroOld);
            }
            if (camareroNew != null && !camareroNew.equals(camareroOld)) {
                camareroNew.getPedidos().add(pedido);
                camareroNew = em.merge(camareroNew);
            }
            if (clienteOld != null && !clienteOld.equals(clienteNew)) {
                clienteOld.getPedidos().remove(pedido);
                clienteOld = em.merge(clienteOld);
            }
            if (clienteNew != null && !clienteNew.equals(clienteOld)) {
                clienteNew.getPedidos().add(pedido);
                clienteNew = em.merge(clienteNew);
            }
            for (Producto productosOldProducto : productosOld) {
                if (!productosNew.contains(productosOldProducto)) {
                    productosOldProducto.getPedidos().remove(pedido);
                    productosOldProducto = em.merge(productosOldProducto);
                }
            }
            for (Producto productosNewProducto : productosNew) {
                if (!productosOld.contains(productosNewProducto)) {
                    productosNewProducto.getPedidos().add(pedido);
                    productosNewProducto = em.merge(productosNewProducto);
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
            Mesa mesa = pedido.getMesa();
            if (mesa != null) {
                mesa.getPedidos().remove(pedido);
                mesa = em.merge(mesa);
            }
            Camarero camarero = pedido.getCamarero();
            if (camarero != null) {
                camarero.getPedidos().remove(pedido);
                camarero = em.merge(camarero);
            }
            Cliente cliente = pedido.getCliente();
            if (cliente != null) {
                cliente.getPedidos().remove(pedido);
                cliente = em.merge(cliente);
            }
            List<Producto> productos = pedido.getProductos();
            for (Producto productosProducto : productos) {
                productosProducto.getPedidos().remove(pedido);
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
