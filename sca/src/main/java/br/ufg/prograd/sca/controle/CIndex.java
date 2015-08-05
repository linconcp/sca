package br.ufg.prograd.sca.controle;

import java.util.List;

import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zkplus.databind.AnnotateDataBinder;
import org.zkoss.zkplus.databind.DataBinder;
import org.zkoss.zul.Menu;
import org.zkoss.zul.Menuitem;
import org.zkoss.zul.Menupopup;

import br.ufg.prograd.sca.entidade.ECentroAula;
import br.ufg.prograd.sca.negocio.NCentroAula;

/**
 * Responsável pela tela inicial do Sistema.
 *
 * @author Lincon Camêlo Pinto
 */
public class CIndex extends GenericForwardComposer<Component> {

  private static final long serialVersionUID = -3338699315085500844L;

  private Menu menuPainel;

  private DataBinder binder;

  @Override
  public void doAfterCompose(final Component comp) throws Exception {
    super.doAfterCompose(comp);

    final NCentroAula nCentroAula = new NCentroAula();

    final List<ECentroAula> listaCentroAula = nCentroAula.listar(new ECentroAula());

    final Menupopup menupopup = new Menupopup();
    menupopup.setParent(this.menuPainel);

    for (final ECentroAula eCentroAula : listaCentroAula) {
      final Menuitem menuitem = new Menuitem(eCentroAula.getNome());
      menuitem.setHref("SCA000.zul?predio=" + eCentroAula.getId());
      menuitem.setParent(menupopup);
    }

    this.binder = new AnnotateDataBinder(comp);
    this.binder.loadAll();
  }
}