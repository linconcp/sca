package br.ufg.prograd.sca.entidade;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity(name = "aviso")
@Table(name = "aviso")
public class EAviso extends EntidadePadrao implements Serializable {

  private static final long serialVersionUID = -2806210981767246112L;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;

  @Column(name = "data_inicial")
  private Date dataInicial;

  @Column(name = "data_final")
  private Date dataFinal;

  @Column(name = "mensagem")
  private String mensagem;

  @Override
  public int getId() {
    return this.id;
  }

  @Override
  public void setId(final int id) {
    this.id = id;
  }

  public Date getDataInicial() {
    return this.dataInicial;
  }

  public void setDataInicial(final Date dataInicial) {
    this.dataInicial = dataInicial;
  }

  public Date getDataFinal() {
    return this.dataFinal;
  }

  public void setDataFinal(final Date dataFinal) {
    this.dataFinal = dataFinal;
  }

  public String getMensagem() {
    return this.mensagem;
  }

  public void setMensagem(final String mensagem) {
    this.mensagem = mensagem;
  }
}
