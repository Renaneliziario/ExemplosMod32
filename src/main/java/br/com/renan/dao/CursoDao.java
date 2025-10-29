package br.com.renan.dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import br.com.renan.domain.Curso;

public class CursoDao implements ICursoDao {

    private static final EntityManagerFactory entityManagerFactory =
            Persistence.createEntityManagerFactory("ExemploJPA");

    @Override
    public Curso cadastrar(Curso curso) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            entityManager.getTransaction().begin();
            entityManager.persist(curso);
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            if (entityManager.getTransaction().isActive()) {
                entityManager.getTransaction().rollback();
            }
            e.printStackTrace();
        } finally {
            entityManager.close();
        }
        return curso;
    }

    // Método para fechar a fábrica quando a aplicação encerrar
    public static void fecharFabrica() {
        if (entityManagerFactory.isOpen()) {
            entityManagerFactory.close();
        }
    }
}
