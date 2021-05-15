package projeto.spring.data.aula.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import projeto.spring.data.aula.model.Telefone;

@Repository
public interface InterfaceTelefone extends CrudRepository<Telefone, Long> {
	
	@Query(value = "select p from Telefone p where p.numero like %?1%")
	public List<Telefone> buscaPorNumero (String numero);
	
	
	@Query(value = "select p from Telefone p where p.numero = :paramnumero")
	public Telefone buscaPorNumeroParam (@Param("paramnumero") String paramnumero);
	
	default <S extends Telefone> S saveAtual(S entity) {
		//processa qualquer coisa
		return save(entity);
	}
	
	@Modifying
	@Transactional
	@Query("delete from Telefone u where u.numero = ?1")
	public void deletePorNumero(String numero);
	
	@Modifying
	@Transactional
	@Query("update Telefone u set u.tipo = ?1 where u.numero = ?2")
	public void updateEmailPorNumero(String email, String numero);
	
}
