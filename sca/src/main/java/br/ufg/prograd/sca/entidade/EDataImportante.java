package br.ufg.prograd.sca.entidade;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity(name = "data_importante")
@Table(name = "data_importante")
public class EDataImportante extends EntidadePadrao implements Serializable {

  private static final long serialVersionUID = 1290767203674720251L;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;

  @Column(name = "data")
  private Date data;

  @Column(name = "mensagem")
  private String mensagem;

  @Override
  public void setId(final int id) {
    this.id = id;
  }

  @Override
  public int getId() {
    return this.id;
  }

  public Date getData() {
    return this.data;
  }

  public void setData(final Date data) {
    this.data = data;
  }

  public String getMensagem() {
    return this.mensagem;
  }

  public void setMensagem(final String mensagem) {
    this.mensagem = mensagem;
  }

}
