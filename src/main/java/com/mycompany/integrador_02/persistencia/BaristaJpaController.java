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
import com.mycompany.integrador_02.logica.Usuario;
import com.mycompany.integrador_02.logica.Producto;
import java.util.ArrayList;
import java.util.List;
import com.mycompany.integrador_02.logica.Cafe;
import com.mycompany.integrador_02.persistencia.exceptions.NonexistentEntityException;
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
        if (barista.getVariedadesDeCafe() == null) {
            barista.setVariedadesDeCafe(new ArrayList<Cafe>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Usuario unUsuario = barista.getUnUsuario();
            if (unUsuario != null) {
                unUsuario = em.getReference(unUsuario.getClass(), unUsuario.getId());
                barista.setUnUsuario(unUsuario);
            }
            List<Producto> attachedProductos = new ArrayList<Producto>();
            for (Producto productosProductoToAttach : barista.getProductos()) {
                productosProductoToAttach = em.getReference(productosProductoToAttach.getClass(), productosProductoToAttach.getId());
                attachedProductos.add(productosProductoToAttach);
            }
            barista.setProductos(attachedProductos);
            List<Cafe> attachedVariedadesDeCafe = new ArrayList<Cafe>();
            for (Cafe variedadesDeCafeCafeToAttach : barista.getVariedadesDeCafe()) {
                variedadesDeCafeCafeToAttach = em.getReference(variedadesDeCafeCafeToAttach.getClass(), variedadesDeCafeCafeToAttach.getId());
                attachedVariedadesDeCafe.add(variedadesDeCafeCafeToAttach);
            }
            barista.setVariedadesDeCafe(attachedVariedadesDeCafe);
            em.persist(barista);
            if (unUsuario != null) {
                Barista oldUnBaristaOfUnUsuario = unUsuario.getUnBarista();
                if (oldUnBaristaOfUnUsuario != null) {
                    oldUnBaristaOfUnUsuario.setUnUsuario(null);
                    oldUnBaristaOfUnUsuario = em.merge(oldUnBaristaOfUnUsuario);
                }
                unUsuario.setUnBarista(barista);
                unUsuario = em.merge(unUsuario);
            }
            for (Producto productosProducto : barista.getProductos()) {
                Barista oldUnBaristaOfProductosProducto = productosProducto.getUnBarista();
                productosProducto.setUnBarista(barista);
                productosProducto = em.merge(productosProducto);
                if (oldUnBaristaOfProductosProducto != null) {
                    oldUnBaristaOfProductosProducto.getProductos().remove(productosProducto);
                    oldUnBaristaOfProductosProducto = em.merge(oldUnBaristaOfProductosProducto);
                }
            }
            for (Cafe variedadesDeCafeCafe : barista.getVariedadesDeCafe()) {
                Barista oldUnBaristaOfVariedadesDeCafeCafe = variedadesDeCafeCafe.getUnBarista();
                variedadesDeCafeCafe.setUnBarista(barista);
                variedadesDeCafeCafe = em.merge(variedadesDeCafeCafe);
                if (oldUnBaristaOfVariedadesDeCafeCafe != null) {
                    oldUnBaristaOfVariedadesDeCafeCafe.getVariedadesDeCafe().remove(variedadesDeCafeCafe);
                    oldUnBaristaOfVariedadesDeCafeCafe = em.merge(oldUnBaristaOfVariedadesDeCafeCafe);
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
            Usuario unUsuarioOld = persistentBarista.getUnUsuario();
            Usuario unUsuarioNew = barista.getUnUsuario();
            List<Producto> productosOld = persistentBarista.getProductos();
            List<Producto> productosNew = barista.getProductos();
            List<Cafe> variedadesDeCafeOld = persistentBarista.getVariedadesDeCafe();
            List<Cafe> variedadesDeCafeNew = barista.getVariedadesDeCafe();
            if (unUsuarioNew != null) {
                unUsuarioNew = em.getReference(unUsuarioNew.getClass(), unUsuarioNew.getId());
                barista.setUnUsuario(unUsuarioNew);
            }
            List<Producto> attachedProductosNew = new ArrayList<Producto>();
            for (Producto productosNewProductoToAttach : productosNew) {
                productosNewProductoToAttach = em.getReference(productosNewProductoToAttach.getClass(), productosNewProductoToAttach.getId());
                attachedProductosNew.add(productosNewProductoToAttach);
            }
            productosNew = attachedProductosNew;
            barista.setProductos(productosNew);
            List<Cafe> attachedVariedadesDeCafeNew = new ArrayList<Cafe>();
            for (Cafe variedadesDeCafeNewCafeToAttach : variedadesDeCafeNew) {
                variedadesDeCafeNewCafeToAttach = em.getReference(variedadesDeCafeNewCafeToAttach.getClass(), variedadesDeCafeNewCafeToAttach.getId());
                attachedVariedadesDeCafeNew.add(variedadesDeCafeNewCafeToAttach);
            }
            variedadesDeCafeNew = attachedVariedadesDeCafeNew;
            barista.setVariedadesDeCafe(variedadesDeCafeNew);
            barista = em.merge(barista);
            if (unUsuarioOld != null && !unUsuarioOld.equals(unUsuarioNew)) {
                unUsuarioOld.setUnBarista(null);
                unUsuarioOld = em.merge(unUsuarioOld);
            }
            if (unUsuarioNew != null && !unUsuarioNew.equals(unUsuarioOld)) {
                Barista oldUnBaristaOfUnUsuario = unUsuarioNew.getUnBarista();
                if (oldUnBaristaOfUnUsuario != null) {
                    oldUnBaristaOfUnUsuario.setUnUsuario(null);
                    oldUnBaristaOfUnUsuario = em.merge(oldUnBaristaOfUnUsuario);
                }
                unUsuarioNew.setUnBarista(barista);
                unUsuarioNew = em.merge(unUsuarioNew);
            }
            for (Producto productosOldProducto : productosOld) {
                if (!productosNew.contains(productosOldProducto)) {
                    productosOldProducto.setUnBarista(null);
                    productosOldProducto = em.merge(productosOldProducto);
                }
            }
            for (Producto productosNewProducto : productosNew) {
                if (!productosOld.contains(productosNewProducto)) {
                    Barista oldUnBaristaOfProductosNewProducto = productosNewProducto.getUnBarista();
                    productosNewProducto.setUnBarista(barista);
                    productosNewProducto = em.merge(productosNewProducto);
                    if (oldUnBaristaOfProductosNewProducto != null && !oldUnBaristaOfProductosNewProducto.equals(barista)) {
                        oldUnBaristaOfProductosNewProducto.getProductos().remove(productosNewProducto);
                        oldUnBaristaOfProductosNewProducto = em.merge(oldUnBaristaOfProductosNewProducto);
                    }
                }
            }
            for (Cafe variedadesDeCafeOldCafe : variedadesDeCafeOld) {
                if (!variedadesDeCafeNew.contains(variedadesDeCafeOldCafe)) {
                    variedadesDeCafeOldCafe.setUnBarista(null);
                    variedadesDeCafeOldCafe = em.merge(variedadesDeCafeOldCafe);
                }
            }
            for (Cafe variedadesDeCafeNewCafe : variedadesDeCafeNew) {
                if (!variedadesDeCafeOld.contains(variedadesDeCafeNewCafe)) {
                    Barista oldUnBaristaOfVariedadesDeCafeNewCafe = variedadesDeCafeNewCafe.getUnBarista();
                    variedadesDeCafeNewCafe.setUnBarista(barista);
                    variedadesDeCafeNewCafe = em.merge(variedadesDeCafeNewCafe);
                    if (oldUnBaristaOfVariedadesDeCafeNewCafe != null && !oldUnBaristaOfVariedadesDeCafeNewCafe.equals(barista)) {
                        oldUnBaristaOfVariedadesDeCafeNewCafe.getVariedadesDeCafe().remove(variedadesDeCafeNewCafe);
                        oldUnBaristaOfVariedadesDeCafeNewCafe = em.merge(oldUnBaristaOfVariedadesDeCafeNewCafe);
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
            Usuario unUsuario = barista.getUnUsuario();
            if (unUsuario != null) {
                unUsuario.setUnBarista(null);
                unUsuario = em.merge(unUsuario);
            }
            List<Producto> productos = barista.getProductos();
            for (Producto productosProducto : productos) {
                productosProducto.setUnBarista(null);
                productosProducto = em.merge(productosProducto);
            }
            List<Cafe> variedadesDeCafe = barista.getVariedadesDeCafe();
            for (Cafe variedadesDeCafeCafe : variedadesDeCafe) {
                variedadesDeCafeCafe.setUnBarista(null);
                variedadesDeCafeCafe = em.merge(variedadesDeCafeCafe);
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
