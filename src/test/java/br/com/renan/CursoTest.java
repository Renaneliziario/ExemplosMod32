package br.com.renan;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;

import br.com.renan.dao.CursoDao;
import br.com.renan.dao.ICursoDao;
import br.com.renan.domain.Curso;

public class CursoTest {

	private ICursoDao cursoDao;
	
	public CursoTest() {
		cursoDao = new CursoDao();
	}
	
	@Test
	public void cadastrar () {
		Curso curso =  new Curso();
		curso.setCodigo("A1");
		curso.setDescricao("curso teste");
		curso.setNome("Curso de Java");
		curso = cursoDao.cadastrar(curso);	
		
		assertNotNull(curso);
		assertNotNull(curso.getId());
		
	}

}
