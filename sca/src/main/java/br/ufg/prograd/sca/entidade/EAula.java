package br.ufg.prograd.sca.entidade;

import java.io.Serializable;
import java.util.Date;

public class EAula implements Serializable {

  private static final long serialVersionUID = -2591154974918066513L;

  private Date dataInicio;

  private Date dataFim;

  private int sala;

  private String disciplina;

  public Date getDataInicio() {
    return this.dataInicio;
  }

  public void setDataInicio(final Date dataInicio) {
    this.dataInicio = dataInicio;
  }

  public Date getDataFim() {
    return this.dataFim;
  }

  public void setDataFim(final Date dataFim) {
    this.dataFim = dataFim;
  }

  public int getSala() {
    return this.sala;
  }

  public void setSala(final int sala) {
    this.sala = sala;
  }

  public String getDisciplina() {
    return this.disciplina;
  }

  public void setDisciplina(final String disciplina) {
    this.disciplina = disciplina;
  }

}
