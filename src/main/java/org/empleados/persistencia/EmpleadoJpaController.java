package org.empleados.persistencia;

import org.empleados.logica.Empleado;

import javax.persistence.*;
import javax.persistence.criteria.CriteriaQuery;
import java.io.Serializable;
import java.util.List;

public class EmpleadoJpaController implements Serializable {
    private EntityManagerFactory emf = null;

    // Se conecta con la unidad de persistencia
    public EmpleadoJpaController() {
        this.emf = Persistence.createEntityManagerFactory("empleadosPU");
    }

    // Se crea el gestor de entidades
    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    // Método para crear empleados
    public void create(Empleado empleado) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(empleado);
            em.getTransaction().commit();
        }finally {
            if (em != null) {
                em.close();
            }
        }
    }

    // Método para eliminar empleados
    public void destroy(int id) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Empleado empleado;
            empleado = em.getReference(Empleado.class, id);
            em.remove(empleado);
            em.getTransaction().commit();
        }finally {
            if (em != null) {
                em.close();
            }
        }
    }

    // Método para devolver una entidad de la BD según su id
    public Empleado findEntity(int id) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Empleado empleado = em.find(Empleado.class, id);
            return empleado;
        }finally {
            if (em != null) {
                em.close();
            }
        }
    }

    // Método para actualizar la información de un empleado
    public void edit(Empleado empleado) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.merge(empleado);
            em.getTransaction().commit();
        }finally {
            if (em != null) {
                em.close();
            }
        }
    }

    // Método para listar empleados
    public List<Empleado> findEmpleadoEntities() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Empleado.class));
            Query q = em.createQuery(cq);
            return q.getResultList();
        }finally {
            em.close();
        }
    }

    // Método para buscar empleados por cargo
    public List<Empleado> findEmpleado(String cargo) {
        EntityManager em = getEntityManager();
        try {
            List <Empleado> empleados;
            empleados = em.createQuery("SELECT c from Empleado c WHERE c.cargo = :cargo", Empleado.class).setParameter("cargo", cargo).getResultList();
            return empleados;
        }finally {
            em.close();
        }
    }
}
