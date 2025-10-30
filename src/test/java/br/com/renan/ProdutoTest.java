package br.com.renan;

import static org.junit.Assert.assertNotNull;

import java.math.BigDecimal;

import org.junit.Test;

import br.com.renan.dao.ProdutoDao;
import br.com.renan.domain.Produto;

public class ProdutoTest {

    private ProdutoDao produtoDao;

    public ProdutoTest() {
        produtoDao = new ProdutoDao();
    }

    @Test
    public void salvar() {
        Produto produto = new Produto();
        produto.setNome("Produto Exemplo");
        produto.setDescricao("Descrição exemplo");
        produto.setPreco(new BigDecimal("49.99"));

        produto = produtoDao.salvar(produto);

        assertNotNull(produto);
        assertNotNull(produto.getId());
        
    }


}
