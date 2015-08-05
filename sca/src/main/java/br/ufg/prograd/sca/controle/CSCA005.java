package br.ufg.prograd.sca.controle;

import java.util.List;

import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.WrongValueException;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zkplus.databind.AnnotateDataBinder;
import org.zkoss.zkplus.databind.DataBinder;
import org.zkoss.zul.Button;
import org.zkoss.zul.Datebox;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Textbox;

import br.ufg.prograd.sca.entidade.EDataImportante;
import br.ufg.prograd.sca.negocio.NDataImportante;

public class CSCA005 extends GenericForwardComposer<Component> {

  private static final long serialVersionUID = -6213147233491934980L;

  private Listbox lstDataImportante;
  private Button btIncluir;
  private Button btAlterar;
  private Button btExcluir;

  private List<EDataImportante> listaDataImportante;

  private Datebox data;

  private Textbox mensagem;

  private DataBinder binderCSCA005;

  final NDataImportante nDataImportante = new NDataImportante();

  private EDataImportante eDataImportante;

  @Override
  public void doAfterCompose(final Component comp) throws Exception {
    super.doAfterCompose(comp);
    this.eDataImportante = new EDataImportante();
    this.binderCSCA005 = new AnnotateDataBinder(comp);
    this.atualizarLista();
  }

  public void incluir() {
    this.validarDados();

    this.nDataImportante.incluir(this.eDataImportante);
    this.eDataImportante = new EDataImportante();
    this.atualizarLista();
  }

  public void alterar() {
    this.validarDados();

    this.nDataImportante.alterar(this.eDataImportante);
    this.eDataImportante = new EDataImportante();

    this.btIncluir.setDisabled(false);
    this.btAlterar.setDisabled(true);
    this.btExcluir.setDisabled(true);

    this.atualizarLista();
  }

  public void excluir() {
    this.nDataImportante.excluir(this.eDataImportante);
    this.eDataImportante = new EDataImportante();

    this.btIncluir.setDisabled(false);
    this.btAlterar.setDisabled(true);
    this.btExcluir.setDisabled(true);

    this.atualizarLista();
  }

  private void atualizarLista() {
    this.listaDataImportante = this.nDataImportante.listar(new EDataImportante());
    this.binderCSCA005.loadAll();
  }

  public void onSelect$lstDataImportante() {
    if (this.lstDataImportante.getSelectedItem() != null) {
      this.eDataImportante = (EDataImportante) this.lstDataImportante.getSelectedItem().getValue();

      this.eDataImportante = this.nDataImportante.consultar(this.eDataImportante);

      this.btIncluir.setDisabled(true);
      this.btAlterar.setDisabled(false);
      this.btExcluir.setDisabled(false);

      this.atualizarLista();
    }
  }

  private void validarDados() {
    if (this.eDataImportante.getData() == null) {
      this.data.setFocus(true);
      throw new WrongValueException(this.data, "É necessário informar a data.");
    }

    if (this.eDataImportante.getMensagem() == null) {
      throw new WrongValueException(this.mensagem, "É necessário informar a mensagem.");
    }
  }

  public List<EDataImportante> getListaDataImportante() {
    return this.listaDataImportante;
  }

  public void setListaDataImportante(final List<EDataImportante> listaDataImportante) {
    this.listaDataImportante = listaDataImportante;
  }

  public EDataImportante getEDataImportante() {
    return this.eDataImportante;
  }

  public void setEDataImportante(final EDataImportante eDataImportante) {
    this.eDataImportante = eDataImportante;
  }
}