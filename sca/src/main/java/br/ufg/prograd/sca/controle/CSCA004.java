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

import br.ufg.prograd.sca.entidade.EAviso;
import br.ufg.prograd.sca.negocio.NAviso;

public class CSCA004 extends GenericForwardComposer<Component> {

  private static final long serialVersionUID = -6213147233491934980L;

  private Listbox lstAviso;
  private Button btIncluir;
  private Button btAlterar;
  private Button btExcluir;
  private Datebox dataInicial;
  private Datebox dataFinal;
  private Textbox mensagem;

  private List<EAviso> listaAviso;

  private DataBinder binderCSCA004;

  final NAviso nAviso = new NAviso();

  private EAviso eAviso;

  @Override
  public void doAfterCompose(final Component comp) throws Exception {
    super.doAfterCompose(comp);
    this.eAviso = new EAviso();
    this.binderCSCA004 = new AnnotateDataBinder(comp);
    this.atualizarLista();
  }

  public void incluir() {
    this.validarDados();

    this.nAviso.incluir(this.eAviso);
    this.eAviso = new EAviso();
    this.atualizarLista();
  }

  public void alterar() {
    this.validarDados();

    this.nAviso.alterar(this.eAviso);
    this.eAviso = new EAviso();

    this.btIncluir.setDisabled(false);
    this.btAlterar.setDisabled(true);
    this.btExcluir.setDisabled(true);

    this.atualizarLista();
  }

  public void excluir() {
    this.nAviso.excluir(this.eAviso);
    this.eAviso = new EAviso();

    this.btIncluir.setDisabled(false);
    this.btAlterar.setDisabled(true);
    this.btExcluir.setDisabled(true);

    this.atualizarLista();
  }

  private void atualizarLista() {
    this.listaAviso = this.nAviso.listar(new EAviso());
    this.binderCSCA004.loadAll();
  }

  public void onSelect$lstAviso() {
    if (this.lstAviso.getSelectedItem() != null) {
      this.eAviso = (EAviso) this.lstAviso.getSelectedItem().getValue();

      final EAviso aviso = new EAviso();

      aviso.setId(this.eAviso.getId());

      this.eAviso = this.nAviso.consultar(this.eAviso);

      this.btIncluir.setDisabled(true);
      this.btAlterar.setDisabled(false);
      this.btExcluir.setDisabled(false);

      this.atualizarLista();
    }
  }

  private void validarDados() {
    if (this.eAviso.getDataInicial() == null) {
      this.dataInicial.setFocus(true);
      throw new WrongValueException(this.dataInicial, "É necessário informar a data.");
    }

    if (this.eAviso.getDataInicial() == null) {
      this.dataFinal.setFocus(true);
      throw new WrongValueException(this.dataFinal, "É necessário informar a data.");
    }

    if (this.eAviso.getMensagem() == null) {
      throw new WrongValueException(this.mensagem, "É necessário informar a mensagem.");
    }
  }

  public List<EAviso> getListaAviso() {
    return this.listaAviso;
  }

  public void setListaAviso(final List<EAviso> listaAviso) {
    this.listaAviso = listaAviso;
  }

  public EAviso getEAviso() {
    return this.eAviso;
  }

  public void setEAviso(final EAviso eAviso) {
    this.eAviso = eAviso;
  }
}