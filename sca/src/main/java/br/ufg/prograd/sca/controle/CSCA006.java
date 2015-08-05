package br.ufg.prograd.sca.controle;

import java.util.List;

import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zkplus.databind.AnnotateDataBinder;
import org.zkoss.zkplus.databind.DataBinder;
import org.zkoss.zul.Button;
import org.zkoss.zul.Listbox;

import br.ufg.prograd.sca.entidade.ECentroAula;
import br.ufg.prograd.sca.negocio.NCentroAula;

/**
 * Manutenção na tabela de Prédios
 *
 * @author Lincon Camêlo Pinto
 */
public class CSCA006 extends GenericForwardComposer<Component> {

  private static final long serialVersionUID = -6213147233491934980L;

  private Listbox lstCentroAula;
  private Button btIncluir;
  private Button btAlterar;
  private Button btExcluir;

  private List<ECentroAula> listaCentroAula;

  private DataBinder binderCSCA006;

  final NCentroAula nCentroAula = new NCentroAula();

  private ECentroAula eCentroAula;

  @Override
  public void doAfterCompose(final Component comp) throws Exception {
    super.doAfterCompose(comp);
    this.eCentroAula = new ECentroAula();
    this.binderCSCA006 = new AnnotateDataBinder(comp);
    this.atualizarLista();
  }

  public void incluir() {
    this.nCentroAula.incluir(this.eCentroAula);
    this.eCentroAula = new ECentroAula();
    this.atualizarLista();
  }

  public void alterar() {
    this.nCentroAula.alterar(this.eCentroAula);
    this.eCentroAula = new ECentroAula();

    this.btIncluir.setDisabled(false);
    this.btAlterar.setDisabled(true);
    this.btExcluir.setDisabled(true);

    this.atualizarLista();
  }

  public void excluir() {
    this.nCentroAula.excluir(this.eCentroAula);
    this.eCentroAula = new ECentroAula();

    this.btIncluir.setDisabled(false);
    this.btAlterar.setDisabled(true);
    this.btExcluir.setDisabled(true);

    this.atualizarLista();
  }

  private void atualizarLista() {
    this.listaCentroAula = this.nCentroAula.listar(new ECentroAula());
    this.binderCSCA006.loadAll();
  }

  public void onSelect$lstCentroAula() {
    if (this.lstCentroAula.getSelectedItem() != null) {
      this.eCentroAula = (ECentroAula) this.lstCentroAula.getSelectedItem().getValue();

      this.eCentroAula = this.nCentroAula.consultar(this.eCentroAula);

      this.btIncluir.setDisabled(true);
      this.btAlterar.setDisabled(false);
      this.btExcluir.setDisabled(false);

      this.atualizarLista();
    }
  }

  public List<ECentroAula> getListaCentroAula() {
    return this.listaCentroAula;
  }

  public void setListaCentroAula(final List<ECentroAula> listaCentroAula) {
    this.listaCentroAula = listaCentroAula;
  }

  public ECentroAula getECentroAula() {
    return this.eCentroAula;
  }

  public void setECentroAula(final ECentroAula eCentroAula) {
    this.eCentroAula = eCentroAula;
  }
}