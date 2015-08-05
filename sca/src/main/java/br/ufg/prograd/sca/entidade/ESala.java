package br.ufg.prograd.sca.entidade;

import java.io.Serializable;
import java.util.List;

// @Entity(name = "SALA")
// @Table(name = "sala")
public class ESala implements Serializable {

  private static final long serialVersionUID = -4504569761222247415L;

  // @Id
  // @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer codigo;

  private List<EHorario> eHorario;

  public Integer getCodigo() {
    return this.codigo;
  }

  public void setCodigo(final Integer codigo) {
    this.codigo = codigo;
  }

  public List<EHorario> geteHorario() {
    return this.eHorario;
  }

  public void seteHorario(final List<EHorario> eHorario) {
    this.eHorario = eHorario;
  }
}
