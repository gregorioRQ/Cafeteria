/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.integrador_02.persistencia;

import com.mycompany.integrador_02.logica.Camarero;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import com.mycompany.integrador_02.logica.Usuario;
import com.mycompany.integrador_02.logica.Pedido;
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
public class CamareroJpaController implements Serializable {

    public CamareroJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }

    public CamareroJpaController() {
        emf = Persistence.createEntityManagerFactory("int02JPAPU");
    }
    
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Camarero camarero) {
        if (camarero.getPedidos() == null) {
            camarero.setPedidos(new ArrayList<Pedido>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Usuario unUsuario = camarero.getUnUsuario();
            if (unUsuario != null) {
                unUsuario = em.getReference(unUsuario.getClass(), unUsuario.getId());
                camarero.setUnUsuario(unUsuario);
            }
            List<Pedido> attachedPedidos = new ArrayList<Pedido>();
            for (Pedido pedidosPedidoToAttach : camarero.getPedidos()) {
                pedidosPedidoToAttach = em.getReference(pedidosPedidoToAttach.getClass(), pedidosPedidoToAttach.getId());
                attachedPedidos.add(pedidosPedidoToAttach);
            }
            camarero.setPedidos(attachedPedidos);
            em.persist(camarero);
            if (unUsuario != null) {
                Camarero oldUnCamareroOfUnUsuario = unUsuario.getUnCamarero();
                if (oldUnCamareroOfUnUsuario != null) {
                    oldUnCamareroOfUnUsuario.setUnUsuario(null);
                    oldUnCamareroOfUnUsuario = em.merge(oldUnCamareroOfUnUsuario);
                }
                unUsuario.setUnCamarero(camarero);
                unUsuario = em.merge(unUsuario);
            }
            for (Pedido pedidosPedido : camarero.getPedidos()) {
                Camarero oldUnCamareroOfPedidosPedido = pedidosPedido.getUnCamarero();
                pedidosPedido.setUnCamarero(camarero);
                pedidosPedido = em.merge(pedidosPedido);
                if (oldUnCamareroOfPedidosPedido != null) {
                    oldUnCamareroOfPedidosPedido.getPedidos().remove(pedidosPedido);
                    oldUnCamareroOfPedidosPedido = em.merge(oldUnCamareroOfPedidosPedido);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Camarero camarero) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Camarero persistentCamarero = em.find(Camarero.class, camarero.getId());
            Usuario unUsuarioOld = persistentCamarero.getUnUsuario();
            Usuario unUsuarioNew = camarero.getUnUsuario();
            List<Pedido> pedidosOld = persistentCamarero.getPedidos();
            List<Pedido> pedidosNew = camarero.getPedidos();
            if (unUsuarioNew != null) {
                unUsuarioNew = em.getReference(unUsuarioNew.getClass(), unUsuarioNew.getId());
                camarero.setUnUsuario(unUsuarioNew);
            }
            List<Pedido> attachedPedidosNew = new ArrayList<Pedido>();
            for (Pedido pedidosNewPedidoToAttach : pedidosNew) {
                pedidosNewPedidoToAttach = em.getReference(pedidosNewPedidoToAttach.getClass(), pedidosNewPedidoToAttach.getId());
                attachedPedidosNew.add(pedidosNewPedidoToAttach);
            }
            pedidosNew = attachedPedidosNew;
            camarero.setPedidos(pedidosNew);
            camarero = em.merge(camarero);
            if (unUsuarioOld != null && !unUsuarioOld.equals(unUsuarioNew)) {
                unUsuarioOld.setUnCamarero(null);
                unUsuarioOld = em.merge(unUsuarioOld);
            }
            if (unUsuarioNew != null && !unUsuarioNew.equals(unUsuarioOld)) {
                Camarero oldUnCamareroOfUnUsuario = unUsuarioNew.getUnCamarero();
                if (oldUnCamareroOfUnUsuario != null) {
                    oldUnCamareroOfUnUsuario.setUnUsuario(null);
                    oldUnCamareroOfUnUsuario = em.merge(oldUnCamareroOfUnUsuario);
                }
                unUsuarioNew.setUnCamarero(camarero);
                unUsuarioNew = em.merge(unUsuarioNew);
            }
            for (Pedido pedidosOldPedido : pedidosOld) {
                if (!pedidosNew.contains(pedidosOldPedido)) {
                    pedidosOldPedido.setUnCamarero(null);
                    pedidosOldPedido = em.merge(pedidosOldPedido);
                }
            }
            for (Pedido pedidosNewPedido : pedidosNew) {
                if (!pedidosOld.contains(pedidosNewPedido)) {
                    Camarero oldUnCamareroOfPedidosNewPedido = pedidosNewPedido.getUnCamarero();
                    pedidosNewPedido.setUnCamarero(camarero);
                    pedidosNewPedido = em.merge(pedidosNewPedido);
                    if (oldUnCamareroOfPedidosNewPedido != null && !oldUnCamareroOfPedidosNewPedido.equals(camarero)) {
                        oldUnCamareroOfPedidosNewPedido.getPedidos().remove(pedidosNewPedido);
                        oldUnCamareroOfPedidosNewPedido = em.merge(oldUnCamareroOfPedidosNewPedido);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                int id = camarero.getId();
                if (findCamarero(id) == null) {
                    throw new NonexistentEntityException("The camarero with id " + id + " no longer exists.");
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
            Camarero camarero;
            try {
                camarero = em.getReference(Camarero.class, id);
                camarero.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The camarero with id " + id + " no longer exists.", enfe);
            }
            Usuario unUsuario = camarero.getUnUsuario();
            if (unUsuario != null) {
                unUsuario.setUnCamarero(null);
                unUsuario = em.merge(unUsuario);
            }
            List<Pedido> pedidos = camarero.getPedidos();
            for (Pedido pedidosPedido : pedidos) {
                pedidosPedido.setUnCamarero(null);
                pedidosPedido = em.merge(pedidosPedido);
            }
            em.remove(camarero);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Camarero> findCamareroEntities() {
        return findCamareroEntities(true, -1, -1);
    }

    public List<Camarero> findCamareroEntities(int maxResults, int firstResult) {
        return findCamareroEntities(false, maxResults, firstResult);
    }

    private List<Camarero> findCamareroEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Camarero.class));
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

    public Camarero findCamarero(int id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Camarero.class, id);
        } finally {
            em.close();
        }
    }

    public int getCamareroCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Camarero> rt = cq.from(Camarero.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
