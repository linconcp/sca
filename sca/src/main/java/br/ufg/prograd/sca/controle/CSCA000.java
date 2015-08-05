package br.ufg.prograd.sca.controle;

import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zkplus.databind.AnnotateDataBinder;
import org.zkoss.zkplus.databind.DataBinder;

import br.ufg.prograd.sca.entidade.ECentroAula;
import br.ufg.prograd.sca.negocio.NCentroAula;

/**
 * Responsável pela tela inicial do Sistema
 *
 * @author Lincon Camêlo Pinto
 */
public class CSCA000 extends GenericForwardComposer<Component> {

  private static final long serialVersionUID = -8575295845164368535L;

  private String nome;

  private DataBinder binder;

  @Override
  public void doAfterCompose(final Component comp) throws Exception {
    super.doAfterCompose(comp);

    this.binder = new AnnotateDataBinder(comp);

    Integer predio = 0;
    Integer predioSessao = 0;

    if (Executions.getCurrent().getParameter("predio") != null) {
      predio = Integer.valueOf(Executions.getCurrent().getParameter("predio"));
    }

    if (this.session.getAttribute("predio") != null) {
      predioSessao = (Integer) this.session.getAttribute("predio");
    }

    if (predioSessao != predio) {
      this.session.setAttribute("diaSemana", Integer.valueOf(0));
    }

    ECentroAula eCentroAula = new ECentroAula();
    eCentroAula.setId(predio);

    final NCentroAula nCentroAula = new NCentroAula();

    eCentroAula = nCentroAula.consultar(eCentroAula);

    if (eCentroAula == null) {
      eCentroAula = new ECentroAula();
      eCentroAula.setNome("Centro de aula não cadastrado!");
    }

    this.setNome(eCentroAula.getNome());
    this.session.setAttribute("predio", predio);

    this.binder.loadAll();
  }

  public String getNome() {
    return nome;
  }

  public void setNome(String nome) {
    this.nome = nome;
  }
}
