package br.ufg.prograd.sca.controle;

import java.util.Date;
import java.util.List;

import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zkplus.databind.AnnotateDataBinder;
import org.zkoss.zkplus.databind.DataBinder;

import br.ufg.prograd.sca.entidade.EDataImportante;
import br.ufg.prograd.sca.negocio.NDataImportante;

public class CSCA002 extends GenericForwardComposer<Component> {

  private static final long serialVersionUID = -8575295845164368535L;

  private List<EDataImportante> listaDatas;

  private DataBinder binder;

  @Override
  public void doAfterCompose(final Component comp) throws Exception {
    super.doAfterCompose(comp);

    final NDataImportante nDataImportante = new NDataImportante();

    this.binder = new AnnotateDataBinder(comp);
    this.listaDatas = nDataImportante.listar(new Date());

    this.binder.loadAll();
  }

  public List<EDataImportante> getListaDatas() {
    return this.listaDatas;
  }

  public void setListaDatas(final List<EDataImportante> listaDatas) {
    this.listaDatas = listaDatas;
  }
}
