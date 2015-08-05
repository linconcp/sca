package br.ufg.prograd.sca.negocio;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import br.ufg.prograd.sca.entidade.EntidadePadrao;

public abstract class NegocioPadrao<E extends EntidadePadrao> {

  public abstract void incluir(E entidade);

  public abstract void alterar(E entidade);

  public abstract E consultar(E entidade);

  public abstract List<E> listar(E entidade);

  public abstract void excluir(E entidade);

  protected void _incluir(final E entidade) {
    final EntityManagerFactory fabricaGerenciador = Persistence.createEntityManagerFactory("sca");

    final EntityManager gerenciadorEntidade = fabricaGerenciador.createEntityManager();
    gerenciadorEntidade.getTransaction().begin();

    gerenciadorEntidade.persist(entidade);
    gerenciadorEntidade.getTransaction().commit();

    gerenciadorEntidade.close();
    fabricaGerenciador.close();
  }

  protected void _alterar(final E entidade) {
    final EntityManagerFactory fabricaGerenciador = Persistence.createEntityManagerFactory("sca");

    final EntityManager gerenciadorEntidade = fabricaGerenciador.createEntityManager();
    gerenciadorEntidade.getTransaction().begin();
    gerenciadorEntidade.merge(entidade);
    gerenciadorEntidade.getTransaction().commit();

    gerenciadorEntidade.close();
    fabricaGerenciador.close();
  }

  protected E _consultar(final E entidade) {
    final EntityManagerFactory fabricaGerenciador = Persistence.createEntityManagerFactory("sca");

    final EntityManager gerenciadorEntidade = fabricaGerenciador.createEntityManager();
    @SuppressWarnings("unchecked")
    final E entidadeAux = (E) gerenciadorEntidade.find(entidade.getClass(), entidade.getId());

    return entidadeAux;
  }

  protected void _excluir(final E entidade) {
    final EntityManagerFactory fabricaGerenciador = Persistence.createEntityManagerFactory("sca");

    final EntityManager gerenciadorEntidade = fabricaGerenciador.createEntityManager();

    gerenciadorEntidade.getTransaction().begin();

    @SuppressWarnings("unchecked")
    final E entidadeAux = (E) gerenciadorEntidade.find(entidade.getClass(), entidade.getId());

    gerenciadorEntidade.remove(entidadeAux);
    gerenciadorEntidade.getTransaction().commit();
    gerenciadorEntidade.close();
    fabricaGerenciador.close();
  }
}