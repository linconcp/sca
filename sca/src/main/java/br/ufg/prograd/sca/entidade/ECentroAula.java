package br.ufg.prograd.sca.entidade;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity(name = "centro_aula")
@Table(name = "centro_aula")
public class ECentroAula extends EntidadePadrao {

  @Id
  private int id;

  @Column(name = "nome")
  private String nome;

  @Override
  public int getId() {
    return this.id;
  }

  @Override
  public void setId(final int codigo) {
    this.id = codigo;
  }

  public String getNome() {
    return this.nome;
  }

  public void setNome(final String nome) {
    this.nome = nome;
  }
}
