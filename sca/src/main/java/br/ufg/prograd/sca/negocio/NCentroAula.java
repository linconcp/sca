package br.ufg.prograd.sca.negocio;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import br.ufg.prograd.sca.entidade.ECentroAula;

public class NCentroAula extends NegocioPadrao<ECentroAula> {

  @Override
  public ECentroAula consultar(final ECentroAula eCentroAula) {
    return super._consultar(eCentroAula);
  }

  @Override
  public void incluir(final ECentroAula entidade) {
    super._incluir(entidade);
  }

  @Override
  public void alterar(final ECentroAula entidade) {
    super._alterar(entidade);
  }

  @Override
  public List<ECentroAula> listar(final ECentroAula entidade) {
    final EntityManagerFactory fabricaGerenciador = Persistence.createEntityManagerFactory("sca");
    final EntityManager gerenciadorEntidade = fabricaGerenciador.createEntityManager();

    List<ECentroAula> lista = null;

    try {
      final TypedQuery<ECentroAula> listaAux = gerenciadorEntidade.createQuery(
          "select c from centro_aula c order by c.nome", ECentroAula.class);

      lista = listaAux.getResultList();
    } finally {
      gerenciadorEntidade.close();
      fabricaGerenciador.close();
    }
    return lista;
  }

  @Override
  public void excluir(final ECentroAula entidade) {
    super._excluir(entidade);
  }
}