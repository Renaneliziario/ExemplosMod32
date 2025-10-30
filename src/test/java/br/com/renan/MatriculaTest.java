package br.com.renan;

import static org.junit.Assert.assertNotNull;

import java.time.Instant;
import java.util.UUID;

import org.junit.Test;

import br.com.renan.dao.IMatriculaDao;
import br.com.renan.dao.MatriculaDao;
import br.com.renan.domain.Matricula;

public class MatriculaTest {

    private IMatriculaDao matriculaDao;

    public MatriculaTest() {
        matriculaDao = new MatriculaDao();
    }

    @Test
    public void cadastrar() {
        Matricula mat = new Matricula();     
        
        String codigoUnico = UUID.randomUUID().toString();
        
        mat.setCodigo(codigoUnico);
        mat.setDataMatricula(Instant.now());
        mat.setStatus("ATIVA");
        mat.setValor(2000D);
        mat = matriculaDao.cadastrar(mat);

        assertNotNull(mat);
        assertNotNull(mat.getId()); // Verifica se o ID foi gerado no banco
    }
}
