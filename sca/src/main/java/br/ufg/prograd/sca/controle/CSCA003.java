package br.ufg.prograd.sca.controle;

import java.util.Date;
import java.util.List;

import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zkplus.databind.AnnotateDataBinder;
import org.zkoss.zkplus.databind.DataBinder;

import br.ufg.prograd.sca.entidade.EAviso;
import br.ufg.prograd.sca.negocio.NAviso;

public class CSCA003 extends GenericForwardComposer<Component> {

  private static final long serialVersionUID = -8575295845164368535L;

  private List<EAviso> listaDatas;

  private DataBinder binder;

  @Override
  public void doAfterCompose(final Component comp) throws Exception {
    super.doAfterCompose(comp);
    final NAviso nAviso = new NAviso();

    this.binder = new AnnotateDataBinder(comp);

    this.listaDatas = nAviso.listar(new Date());
    this.binder.loadAll();
  }

  public List<EAviso> getListaDatas() {
    return this.listaDatas;
  }

  public void setListaDatas(final List<EAviso> listaDatas) {
    this.listaDatas = listaDatas;
  }
}
