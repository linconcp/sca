package br.ufg.prograd.sca.negocio;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import br.ufg.prograd.sca.entidade.EAviso;

public class NAviso extends NegocioPadrao<EAviso> {

  @Override
  public void incluir(final EAviso entidade) {
    super._incluir(entidade);
  }

  @Override
  public void alterar(final EAviso entidade) {
    super._alterar(entidade);
  }

  @Override
  public EAviso consultar(final EAviso entidade) {
    return super._consultar(entidade);
  }

  @Override
  public List<EAviso> listar(final EAviso entidade) {
    final EntityManagerFactory fabricaGerenciador = Persistence.createEntityManagerFactory("sca");
    final EntityManager gerenciadorEntidade = fabricaGerenciador.createEntityManager();

    List<EAviso> lista = null;

    try {
      final TypedQuery<EAviso> listaAux = gerenciadorEntidade.createQuery(
          "select a from aviso a order by a.dataInicial", EAviso.class);

      lista = listaAux.getResultList();
    } finally {
      gerenciadorEntidade.close();
      fabricaGerenciador.close();
    }
    return lista;
  }

  @Override
  public void excluir(final EAviso entidade) {
    super._excluir(entidade);
  }

  public List<EAviso> listar(final Date data) {
    final EntityManagerFactory fabricaGerenciador = Persistence.createEntityManagerFactory("sca");

    final EntityManager gerenciadorEntidade = fabricaGerenciador.createEntityManager();

    List<EAviso> lista = null;

    try {
      final TypedQuery<EAviso> listaAux = gerenciadorEntidade.createQuery(
          "select a from aviso a where :dataParametro between a.dataInicial and a.dataFinal order by a.dataInicial",
          EAviso.class);

      listaAux.setParameter("dataParametro", data);

      lista = listaAux.getResultList();
    } finally {
      gerenciadorEntidade.close();
      fabricaGerenciador.close();
    }
    return lista;
  }
}
