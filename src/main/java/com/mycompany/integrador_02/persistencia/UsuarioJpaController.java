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
import com.mycompany.integrador_02.logica.Barista;
import com.mycompany.integrador_02.logica.Administrador;
import com.mycompany.integrador_02.logica.Usuario;
import com.mycompany.integrador_02.persistencia.exceptions.NonexistentEntityException;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author rafae
 */
public class UsuarioJpaController implements Serializable {

    public UsuarioJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }

    public UsuarioJpaController() {
        emf = Persistence.createEntityManagerFactory("int02JPAPU");
    }
    
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Usuario usuario) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Camarero unCamarero = usuario.getUnCamarero();
            if (unCamarero != null) {
                unCamarero = em.getReference(unCamarero.getClass(), unCamarero.getId());
                usuario.setUnCamarero(unCamarero);
            }
            Barista unBarista = usuario.getUnBarista();
            if (unBarista != null) {
                unBarista = em.getReference(unBarista.getClass(), unBarista.getId());
                usuario.setUnBarista(unBarista);
            }
            Administrador unAdmin = usuario.getUnAdmin();
            if (unAdmin != null) {
                unAdmin = em.getReference(unAdmin.getClass(), unAdmin.getId());
                usuario.setUnAdmin(unAdmin);
            }
            em.persist(usuario);
            if (unCamarero != null) {
                Usuario oldUnUsuarioOfUnCamarero = unCamarero.getUnUsuario();
                if (oldUnUsuarioOfUnCamarero != null) {
                    oldUnUsuarioOfUnCamarero.setUnCamarero(null);
                    oldUnUsuarioOfUnCamarero = em.merge(oldUnUsuarioOfUnCamarero);
                }
                unCamarero.setUnUsuario(usuario);
                unCamarero = em.merge(unCamarero);
            }
            if (unBarista != null) {
                Usuario oldUnUsuarioOfUnBarista = unBarista.getUnUsuario();
                if (oldUnUsuarioOfUnBarista != null) {
                    oldUnUsuarioOfUnBarista.setUnBarista(null);
                    oldUnUsuarioOfUnBarista = em.merge(oldUnUsuarioOfUnBarista);
                }
                unBarista.setUnUsuario(usuario);
                unBarista = em.merge(unBarista);
            }
            if (unAdmin != null) {
                Usuario oldUnUsuarioOfUnAdmin = unAdmin.getUnUsuario();
                if (oldUnUsuarioOfUnAdmin != null) {
                    oldUnUsuarioOfUnAdmin.setUnAdmin(null);
                    oldUnUsuarioOfUnAdmin = em.merge(oldUnUsuarioOfUnAdmin);
                }
                unAdmin.setUnUsuario(usuario);
                unAdmin = em.merge(unAdmin);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Usuario usuario) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Usuario persistentUsuario = em.find(Usuario.class, usuario.getId());
            Camarero unCamareroOld = persistentUsuario.getUnCamarero();
            Camarero unCamareroNew = usuario.getUnCamarero();
            Barista unBaristaOld = persistentUsuario.getUnBarista();
            Barista unBaristaNew = usuario.getUnBarista();
            Administrador unAdminOld = persistentUsuario.getUnAdmin();
            Administrador unAdminNew = usuario.getUnAdmin();
            if (unCamareroNew != null) {
                unCamareroNew = em.getReference(unCamareroNew.getClass(), unCamareroNew.getId());
                usuario.setUnCamarero(unCamareroNew);
            }
            if (unBaristaNew != null) {
                unBaristaNew = em.getReference(unBaristaNew.getClass(), unBaristaNew.getId());
                usuario.setUnBarista(unBaristaNew);
            }
            if (unAdminNew != null) {
                unAdminNew = em.getReference(unAdminNew.getClass(), unAdminNew.getId());
                usuario.setUnAdmin(unAdminNew);
            }
            usuario = em.merge(usuario);
            if (unCamareroOld != null && !unCamareroOld.equals(unCamareroNew)) {
                unCamareroOld.setUnUsuario(null);
                unCamareroOld = em.merge(unCamareroOld);
            }
            if (unCamareroNew != null && !unCamareroNew.equals(unCamareroOld)) {
                Usuario oldUnUsuarioOfUnCamarero = unCamareroNew.getUnUsuario();
                if (oldUnUsuarioOfUnCamarero != null) {
                    oldUnUsuarioOfUnCamarero.setUnCamarero(null);
                    oldUnUsuarioOfUnCamarero = em.merge(oldUnUsuarioOfUnCamarero);
                }
                unCamareroNew.setUnUsuario(usuario);
                unCamareroNew = em.merge(unCamareroNew);
            }
            if (unBaristaOld != null && !unBaristaOld.equals(unBaristaNew)) {
                unBaristaOld.setUnUsuario(null);
                unBaristaOld = em.merge(unBaristaOld);
            }
            if (unBaristaNew != null && !unBaristaNew.equals(unBaristaOld)) {
                Usuario oldUnUsuarioOfUnBarista = unBaristaNew.getUnUsuario();
                if (oldUnUsuarioOfUnBarista != null) {
                    oldUnUsuarioOfUnBarista.setUnBarista(null);
                    oldUnUsuarioOfUnBarista = em.merge(oldUnUsuarioOfUnBarista);
                }
                unBaristaNew.setUnUsuario(usuario);
                unBaristaNew = em.merge(unBaristaNew);
            }
            if (unAdminOld != null && !unAdminOld.equals(unAdminNew)) {
                unAdminOld.setUnUsuario(null);
                unAdminOld = em.merge(unAdminOld);
            }
            if (unAdminNew != null && !unAdminNew.equals(unAdminOld)) {
                Usuario oldUnUsuarioOfUnAdmin = unAdminNew.getUnUsuario();
                if (oldUnUsuarioOfUnAdmin != null) {
                    oldUnUsuarioOfUnAdmin.setUnAdmin(null);
                    oldUnUsuarioOfUnAdmin = em.merge(oldUnUsuarioOfUnAdmin);
                }
                unAdminNew.setUnUsuario(usuario);
                unAdminNew = em.merge(unAdminNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                int id = usuario.getId();
                if (findUsuario(id) == null) {
                    throw new NonexistentEntityException("The usuario with id " + id + " no longer exists.");
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
            Usuario usuario;
            try {
                usuario = em.getReference(Usuario.class, id);
                usuario.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The usuario with id " + id + " no longer exists.", enfe);
            }
            Camarero unCamarero = usuario.getUnCamarero();
            if (unCamarero != null) {
                unCamarero.setUnUsuario(null);
                unCamarero = em.merge(unCamarero);
            }
            Barista unBarista = usuario.getUnBarista();
            if (unBarista != null) {
                unBarista.setUnUsuario(null);
                unBarista = em.merge(unBarista);
            }
            Administrador unAdmin = usuario.getUnAdmin();
            if (unAdmin != null) {
                unAdmin.setUnUsuario(null);
                unAdmin = em.merge(unAdmin);
            }
            em.remove(usuario);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Usuario> findUsuarioEntities() {
        return findUsuarioEntities(true, -1, -1);
    }

    public List<Usuario> findUsuarioEntities(int maxResults, int firstResult) {
        return findUsuarioEntities(false, maxResults, firstResult);
    }

    private List<Usuario> findUsuarioEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Usuario.class));
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

    public Usuario findUsuario(int id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Usuario.class, id);
        } finally {
            em.close();
        }
    }

    public int getUsuarioCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Usuario> rt = cq.from(Usuario.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
