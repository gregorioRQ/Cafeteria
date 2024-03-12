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
import com.mycompany.integrador_02.logica.Barista;
import com.mycompany.integrador_02.logica.Pedido;
import java.util.ArrayList;
import java.util.List;
import com.mycompany.integrador_02.logica.Ingrediente;
import com.mycompany.integrador_02.logica.Producto;
import com.mycompany.integrador_02.persistencia.exceptions.NonexistentEntityException;
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
        if (producto.getIngredientes() == null) {
            producto.setIngredientes(new ArrayList<Ingrediente>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Barista barista = producto.getBarista();
            if (barista != null) {
                barista = em.getReference(barista.getClass(), barista.getId());
                producto.setBarista(barista);
            }
            List<Pedido> attachedPedidos = new ArrayList<Pedido>();
            for (Pedido pedidosPedidoToAttach : producto.getPedidos()) {
                pedidosPedidoToAttach = em.getReference(pedidosPedidoToAttach.getClass(), pedidosPedidoToAttach.getId());
                attachedPedidos.add(pedidosPedidoToAttach);
            }
            producto.setPedidos(attachedPedidos);
            List<Ingrediente> attachedIngredientes = new ArrayList<Ingrediente>();
            for (Ingrediente ingredientesIngredienteToAttach : producto.getIngredientes()) {
                ingredientesIngredienteToAttach = em.getReference(ingredientesIngredienteToAttach.getClass(), ingredientesIngredienteToAttach.getId());
                attachedIngredientes.add(ingredientesIngredienteToAttach);
            }
            producto.setIngredientes(attachedIngredientes);
            em.persist(producto);
            if (barista != null) {
                barista.getProductos().add(producto);
                barista = em.merge(barista);
            }
            for (Pedido pedidosPedido : producto.getPedidos()) {
                pedidosPedido.getProductos().add(producto);
                pedidosPedido = em.merge(pedidosPedido);
            }
            for (Ingrediente ingredientesIngrediente : producto.getIngredientes()) {
                Producto oldProductoOfIngredientesIngrediente = ingredientesIngrediente.getProducto();
                ingredientesIngrediente.setProducto(producto);
                ingredientesIngrediente = em.merge(ingredientesIngrediente);
                if (oldProductoOfIngredientesIngrediente != null) {
                    oldProductoOfIngredientesIngrediente.getIngredientes().remove(ingredientesIngrediente);
                    oldProductoOfIngredientesIngrediente = em.merge(oldProductoOfIngredientesIngrediente);
                }
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
            Barista baristaOld = persistentProducto.getBarista();
            Barista baristaNew = producto.getBarista();
            List<Pedido> pedidosOld = persistentProducto.getPedidos();
            List<Pedido> pedidosNew = producto.getPedidos();
            List<Ingrediente> ingredientesOld = persistentProducto.getIngredientes();
            List<Ingrediente> ingredientesNew = producto.getIngredientes();
            if (baristaNew != null) {
                baristaNew = em.getReference(baristaNew.getClass(), baristaNew.getId());
                producto.setBarista(baristaNew);
            }
            List<Pedido> attachedPedidosNew = new ArrayList<Pedido>();
            for (Pedido pedidosNewPedidoToAttach : pedidosNew) {
                pedidosNewPedidoToAttach = em.getReference(pedidosNewPedidoToAttach.getClass(), pedidosNewPedidoToAttach.getId());
                attachedPedidosNew.add(pedidosNewPedidoToAttach);
            }
            pedidosNew = attachedPedidosNew;
            producto.setPedidos(pedidosNew);
            List<Ingrediente> attachedIngredientesNew = new ArrayList<Ingrediente>();
            for (Ingrediente ingredientesNewIngredienteToAttach : ingredientesNew) {
                ingredientesNewIngredienteToAttach = em.getReference(ingredientesNewIngredienteToAttach.getClass(), ingredientesNewIngredienteToAttach.getId());
                attachedIngredientesNew.add(ingredientesNewIngredienteToAttach);
            }
            ingredientesNew = attachedIngredientesNew;
            producto.setIngredientes(ingredientesNew);
            producto = em.merge(producto);
            if (baristaOld != null && !baristaOld.equals(baristaNew)) {
                baristaOld.getProductos().remove(producto);
                baristaOld = em.merge(baristaOld);
            }
            if (baristaNew != null && !baristaNew.equals(baristaOld)) {
                baristaNew.getProductos().add(producto);
                baristaNew = em.merge(baristaNew);
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
            for (Ingrediente ingredientesOldIngrediente : ingredientesOld) {
                if (!ingredientesNew.contains(ingredientesOldIngrediente)) {
                    ingredientesOldIngrediente.setProducto(null);
                    ingredientesOldIngrediente = em.merge(ingredientesOldIngrediente);
                }
            }
            for (Ingrediente ingredientesNewIngrediente : ingredientesNew) {
                if (!ingredientesOld.contains(ingredientesNewIngrediente)) {
                    Producto oldProductoOfIngredientesNewIngrediente = ingredientesNewIngrediente.getProducto();
                    ingredientesNewIngrediente.setProducto(producto);
                    ingredientesNewIngrediente = em.merge(ingredientesNewIngrediente);
                    if (oldProductoOfIngredientesNewIngrediente != null && !oldProductoOfIngredientesNewIngrediente.equals(producto)) {
                        oldProductoOfIngredientesNewIngrediente.getIngredientes().remove(ingredientesNewIngrediente);
                        oldProductoOfIngredientesNewIngrediente = em.merge(oldProductoOfIngredientesNewIngrediente);
                    }
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
            Barista barista = producto.getBarista();
            if (barista != null) {
                barista.getProductos().remove(producto);
                barista = em.merge(barista);
            }
            List<Pedido> pedidos = producto.getPedidos();
            for (Pedido pedidosPedido : pedidos) {
                pedidosPedido.getProductos().remove(producto);
                pedidosPedido = em.merge(pedidosPedido);
            }
            List<Ingrediente> ingredientes = producto.getIngredientes();
            for (Ingrediente ingredientesIngrediente : ingredientes) {
                ingredientesIngrediente.setProducto(null);
                ingredientesIngrediente = em.merge(ingredientesIngrediente);
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
