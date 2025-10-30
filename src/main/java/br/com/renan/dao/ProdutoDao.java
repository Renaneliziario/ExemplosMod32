package br.com.renan.dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import br.com.renan.domain.Produto;

public class ProdutoDao implements IProdutoDao {

        private static final EntityManagerFactory entityManagerFactory =
        Persistence.createEntityManagerFactory("ExemploJPA");

    @Override
    public Produto salvar(Produto produto) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            entityManager.getTransaction().begin();
            produto = entityManager.merge(produto);  // usar merge para evitar erro detached
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            if (entityManager.getTransaction().isActive()) {
                entityManager.getTransaction().rollback();
            }
            throw e;
        } finally {
            entityManager.close();
        }
        return produto;
    }

    // método para fechar a factory ao encerrar a aplicação, se necessário
    public static void fecharFactory() {
        if (entityManagerFactory.isOpen()) {
            entityManagerFactory.close();
        }
    }

}
