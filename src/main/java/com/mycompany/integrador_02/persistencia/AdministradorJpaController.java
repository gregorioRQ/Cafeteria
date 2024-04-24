/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.integrador_02.persistencia;

import com.mycompany.integrador_02.logica.Administrador;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
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
public class AdministradorJpaController implements Serializable {

    public AdministradorJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }

    public AdministradorJpaController() {
        emf = Persistence.createEntityManagerFactory("int02JPAPU");
    }
    
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Administrador administrador) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Usuario unUsuario = administrador.getUnUsuario();
            if (unUsuario != null) {
                unUsuario = em.getReference(unUsuario.getClass(), unUsuario.getId());
                administrador.setUnUsuario(unUsuario);
            }
            em.persist(administrador);
            if (unUsuario != null) {
                Administrador oldUnAdminOfUnUsuario = unUsuario.getUnAdmin();
                if (oldUnAdminOfUnUsuario != null) {
                    oldUnAdminOfUnUsuario.setUnUsuario(null);
                    oldUnAdminOfUnUsuario = em.merge(oldUnAdminOfUnUsuario);
                }
                unUsuario.setUnAdmin(administrador);
                unUsuario = em.merge(unUsuario);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Administrador administrador) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Administrador persistentAdministrador = em.find(Administrador.class, administrador.getId());
            Usuario unUsuarioOld = persistentAdministrador.getUnUsuario();
            Usuario unUsuarioNew = administrador.getUnUsuario();
            if (unUsuarioNew != null) {
                unUsuarioNew = em.getReference(unUsuarioNew.getClass(), unUsuarioNew.getId());
                administrador.setUnUsuario(unUsuarioNew);
            }
            administrador = em.merge(administrador);
            if (unUsuarioOld != null && !unUsuarioOld.equals(unUsuarioNew)) {
                unUsuarioOld.setUnAdmin(null);
                unUsuarioOld = em.merge(unUsuarioOld);
            }
            if (unUsuarioNew != null && !unUsuarioNew.equals(unUsuarioOld)) {
                Administrador oldUnAdminOfUnUsuario = unUsuarioNew.getUnAdmin();
                if (oldUnAdminOfUnUsuario != null) {
                    oldUnAdminOfUnUsuario.setUnUsuario(null);
                    oldUnAdminOfUnUsuario = em.merge(oldUnAdminOfUnUsuario);
                }
                unUsuarioNew.setUnAdmin(administrador);
                unUsuarioNew = em.merge(unUsuarioNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                int id = administrador.getId();
                if (findAdministrador(id) == null) {
                    throw new NonexistentEntityException("The administrador with id " + id + " no longer exists.");
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
            Administrador administrador;
            try {
                administrador = em.getReference(Administrador.class, id);
                administrador.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The administrador with id " + id + " no longer exists.", enfe);
            }
            Usuario unUsuario = administrador.getUnUsuario();
            if (unUsuario != null) {
                unUsuario.setUnAdmin(null);
                unUsuario = em.merge(unUsuario);
            }
            em.remove(administrador);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Administrador> findAdministradorEntities() {
        return findAdministradorEntities(true, -1, -1);
    }

    public List<Administrador> findAdministradorEntities(int maxResults, int firstResult) {
        return findAdministradorEntities(false, maxResults, firstResult);
    }

    private List<Administrador> findAdministradorEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Administrador.class));
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

    public Administrador findAdministrador(int id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Administrador.class, id);
        } finally {
            em.close();
        }
    }

    public int getAdministradorCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Administrador> rt = cq.from(Administrador.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
