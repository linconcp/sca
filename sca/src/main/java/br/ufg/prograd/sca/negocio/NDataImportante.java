package br.ufg.prograd.sca.negocio;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import br.ufg.prograd.sca.entidade.EDataImportante;

public class NDataImportante extends NegocioPadrao<EDataImportante> {

  @Override
  public void incluir(final EDataImportante entidade) {
    super._incluir(entidade);
  }

  @Override
  public void alterar(final EDataImportante entidade) {
    super._alterar(entidade);
  }

  @Override
  public EDataImportante consultar(final EDataImportante entidade) {
    return super._consultar(entidade);
  }

  @Override
  public List<EDataImportante> listar(final EDataImportante entidade) {
    final EntityManagerFactory fabricaGerenciador = Persistence.createEntityManagerFactory("sca");

    final EntityManager gerenciadorEntidade = fabricaGerenciador.createEntityManager();

    List<EDataImportante> lista = null;

    try {
      final TypedQuery<EDataImportante> listaAux = gerenciadorEntidade.createQuery(
          "select d from data_importante d order by d.data", EDataImportante.class);

      lista = listaAux.getResultList();
    } finally {
      gerenciadorEntidade.close();
      fabricaGerenciador.close();
    }
    return lista;
  }

  @Override
  public void excluir(final EDataImportante entidade) {
    super._excluir(entidade);
  }

  public List<EDataImportante> listar(final Date data) {
    final EntityManagerFactory fabricaGerenciador = Persistence.createEntityManagerFactory("sca");

    final EntityManager gerenciadorEntidade = fabricaGerenciador.createEntityManager();

    List<EDataImportante> lista = null;

    try {
      final TypedQuery<EDataImportante> listaAux = gerenciadorEntidade.createQuery(
          "select d from data_importante d where d.data >= :dataParametro order by d.data", EDataImportante.class);

      listaAux.setParameter("dataParametro", data);

      lista = listaAux.getResultList();
    } finally {
      gerenciadorEntidade.close();
      fabricaGerenciador.close();
    }
    return lista;
  }
}
