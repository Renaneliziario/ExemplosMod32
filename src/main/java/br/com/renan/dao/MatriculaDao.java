package br.com.renan.dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import br.com.renan.domain.Curso;
import br.com.renan.domain.Matricula;

public class MatriculaDao implements IMatriculaDao {

    private static final EntityManagerFactory entityManagerFactory =
        Persistence.createEntityManagerFactory("ExemploJPA");

    @Override
    public Matricula cadastrar(Matricula mat) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            entityManager.getTransaction().begin();
            mat = entityManager.merge(mat);  // usar merge para evitar erro detached
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            if (entityManager.getTransaction().isActive()) {
                entityManager.getTransaction().rollback();
            }
            throw e;
        } finally {
            entityManager.close();
        }
        return mat;
    }

    // método para fechar a factory ao encerrar a aplicação, se necessário
    public static void fecharFactory() {
        if (entityManagerFactory.isOpen()) {
            entityManagerFactory.close();
        }
    }
}
