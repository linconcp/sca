package br.ufg.prograd.sca.controle;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Authenticator;
import java.net.HttpURLConnection;
import java.net.InetSocketAddress;
import java.net.PasswordAuthentication;
import java.net.Proxy;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zul.Column;
import org.zkoss.zul.Grid;
import org.zkoss.zul.Label;
import org.zkoss.zul.ListModelList;
import org.zkoss.zul.Row;
import org.zkoss.zul.RowRenderer;
import org.zkoss.zul.Vlayout;

import br.ufg.prograd.sca.entidade.EHorario;

/**
 * Faz a pesquisa dos horÃ¡rios no sistem SiDS da UFG e apresnta a tabela de horÃ¡rios para um centro de aulas
 * especÃ­fico.
 *
 * @author Lincon CamÃªlo Pinto
 */
public class CSCA001 extends GenericForwardComposer<Component> {

  private static final long serialVersionUID = 2473421738379717736L;

  // private static final String HTTPS_URL =
  // "https://projetos.extras.ufg.br/DSU/SiDS/?ModuleName=Rooms&Action=TVTable&Building=2&Period=2014.1&WeekDay=4";
  private static final String HTTPS_URL = "http://projetos.extras.ufg.br/DSU/SiDS/?ModuleName=Rooms&Action=TVTable";

  private static final String SEPARADOR_DADOS = "\t";

  // private static final int QUANTIDADE_HORARIO = 8;

  private static final int QUANTIDADE_SALAS = 4;

  private List<String> rotuloColunas;

  private List<EHorario> resultadoConsulta;

  private List<String[]> conteudo;

  private Grid grid;

  @Override
  public void doAfterCompose(final Component comp) throws Exception {
    super.doAfterCompose(comp);

    // TODO retirar as linhas de autenticação para o proxy quando colocar em produção
    final String authUser = "m131741";
    final String authPassword = "OmPc5,SAN";

    Authenticator.setDefault(new Authenticator() {

      @Override
      public PasswordAuthentication getPasswordAuthentication() {
        return new PasswordAuthentication(authUser, authPassword.toCharArray());
      }
    });

    System.setProperty("http.proxyUser", authUser);
    System.setProperty("http.proxyPassword", authPassword);
    // TODO até aqui
    this.atualizar();
  }

  private void tratarConteudo(final HttpURLConnection con) throws Exception {
    if (con != null) {
      try {
        final BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream()));

        String linhaBruta = null;
        String[] linhaTratada = null;
        int contadorLinha = 0;
        this.rotuloColunas = new ArrayList<String>();
        this.resultadoConsulta = new ArrayList<EHorario>();

        while ((linhaBruta = br.readLine()) != null) {
          System.out.println(linhaBruta);
          System.out.println(new String(linhaBruta.getBytes("ISO-8859-1"), "UTF-8"));

          switch (contadorLinha) {
            case 0:
              break;
            case 1:
              linhaTratada = linhaBruta.split(CSCA001.SEPARADOR_DADOS);
              linhaTratada[0] = "Horário/Salas";
              this.rotuloColunas = Arrays.asList(linhaTratada);
              break;
            default:
              linhaTratada = linhaBruta.split(CSCA001.SEPARADOR_DADOS, this.rotuloColunas.size());

              final EHorario eAula = new EHorario();

              final String[] horario = linhaTratada[0].split("-");
              final Calendar dataInicio = Calendar.getInstance();
              final Calendar dataFim = Calendar.getInstance();

              dataInicio.set(Calendar.HOUR_OF_DAY, Integer.parseInt(horario[0].substring(0, 2)));
              dataInicio.set(Calendar.MINUTE, Integer.parseInt(horario[0].substring(3, 5)));

              dataFim.set(Calendar.HOUR_OF_DAY, Integer.parseInt(horario[1].substring(0, 2)));
              dataFim.set(Calendar.MINUTE, Integer.parseInt(horario[1].substring(3, 5)));

              eAula.setDataInicio(dataInicio.getTime());
              eAula.setDataFim(dataFim.getTime());

              final String[] sala = Arrays.copyOfRange(linhaTratada, 1, linhaTratada.length);

              eAula.setDisciplinaSala(sala);
              this.resultadoConsulta.add(eAula);
              break;
          }

          contadorLinha++;
        }
        br.close();
      } catch (final IOException e) {
        throw new Exception("Problemas de conexão com o SiDS!");
      }
    }
  }

  public int prepararTabela(final int inicio) {
    this.conteudo = new ArrayList<String[]>();
    final DateFormat formatadorData = new SimpleDateFormat("HH:mm");
    final Date hoje = new Date();

    int fim = inicio + CSCA001.QUANTIDADE_SALAS;

    if (fim > this.rotuloColunas.size()) {
      fim = this.rotuloColunas.size();
    }

    final String[] colunasCabecalho = new String[CSCA001.QUANTIDADE_SALAS + 1];

    colunasCabecalho[0] = this.rotuloColunas.get(0);

    /** pegando os rótulos das colunas das salas a serem apresentadas no momento */
    for (int contador = inicio; contador < fim; contador++) {
      colunasCabecalho[(contador - inicio) + 1] = this.rotuloColunas.get(contador);
    }

    /** pegando os dados das salas a serem apresentadas no momento */
    for (int contador1 = 0; contador1 < this.resultadoConsulta.size(); contador1++) {

      /** selecionando somente os horários depois da hora atual */
      if (hoje.after(this.resultadoConsulta.get(contador1).getDataFim())) {
        continue;
      }

      final String[] conteudoAux = new String[colunasCabecalho.length];

      /** o horÃ¡rio */
      conteudoAux[0] = formatadorData.format(this.resultadoConsulta.get(contador1).getDataInicio()) + " - "
          + formatadorData.format(this.resultadoConsulta.get(contador1).getDataFim());

      /** as disciplinas nas salas nos horÃ¡rios */
      for (int contador2 = inicio; contador2 < fim; contador2++) {
        conteudoAux[(contador2 - inicio) + 1] = this.resultadoConsulta.get(contador1).getDisciplinaSala()[contador2 - 1];
      }

      this.conteudo.add(conteudoAux);

      // if (this.conteudo.size() == CSCA001.QUANTIDADE_HORARIO) {
      // break;
      // }
    }

    final Map<String, Object> params = new HashMap<String, Object>();
    params.put("columns", colunasCabecalho);
    this.grid.getColumns().detach();
    this.grid.appendChild(Executions.createComponents("colunas.zul", this.grid, params));
    final ListModelList<String[]> lmList = new ListModelList<String[]>();
    lmList.addAll(this.conteudo);
    this.grid.setModel(lmList);
    ((Column) this.grid.getColumns().getFirstChild()).setStyle("width: 10%; font-size: 22px; text-align: center;");

    if (fim == (this.rotuloColunas.size())) {
      fim = 1;
    }
    return fim;
  }

  @SuppressWarnings("unchecked")
  public void atualizar() {
    URL url;

    Integer salaInicial = 1;
    Integer predio = 1;
    Integer diaSemana = 1;

    if ((this.session.getAttribute("salaInicial") != null)) {
      salaInicial = (Integer) this.session.getAttribute("salaInicial");
    }

    if ((this.session.getAttribute("diaSemana") != null)) {
      diaSemana = (Integer) this.session.getAttribute("diaSemana");
    }

    if ((this.session.getAttribute("predio") != null)) {
      predio = (Integer) this.session.getAttribute("predio");
    }

    if ((this.session.getAttribute("rotuloColunas") != null)) {
      this.rotuloColunas = (List<String>) this.session.getAttribute("rotuloColunas");
    }

    if ((this.session.getAttribute("resultadoConsulta") != null)) {
      this.resultadoConsulta = (List<EHorario>) this.session.getAttribute("resultadoConsulta");
    }

    try {
      if ((diaSemana != (Calendar.getInstance().get(Calendar.DAY_OF_WEEK) - 1)) || (this.rotuloColunas == null)) {
        salaInicial = 1;

        final int ano = Calendar.getInstance().get(Calendar.YEAR);

        final int periodo = (Calendar.getInstance().get(Calendar.MONTH) <= 6) ? 1 : 2;

        diaSemana = Calendar.getInstance().get(Calendar.DAY_OF_WEEK) - 1;

        this.session.setAttribute("diaSemana", diaSemana);

        url = new URL(CSCA001.HTTPS_URL + "&Building=" + predio + "&Period=" + ano + "." + periodo + "&WeekDay="
            + diaSemana);

        // System.out.println(url);

        final Proxy proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress("172.16.1.58", 8080));

        final HttpURLConnection con = (HttpURLConnection) url.openConnection(proxy);

        // dump all the content
        this.tratarConteudo(con);
      }

      salaInicial = this.prepararTabela(salaInicial);
      this.session.setAttribute("salaInicial", salaInicial);
      this.session.setAttribute("diaSemana", diaSemana);
      this.session.setAttribute("rotuloColunas", this.rotuloColunas);
      this.session.setAttribute("resultadoConsulta", this.resultadoConsulta);

      this.grid.setRowRenderer(new RowRenderer<String[]>() {

        @Override
        public void render(final Row row, final String[] data, final int posicao) throws Exception {
          for (final String celula : data) {
            final Vlayout vlayout = new Vlayout();

            if (celula != null) {
              final String[] partesCelula = celula.split(",");

              for (int contador = 0; contador < partesCelula.length; contador++) {
                final Label rotulo = new Label(partesCelula[contador]);

                if (contador == 0) {
                  rotulo.setStyle("font-size: 22px; line-height: normal; font-weight: bold; ");
                } else {
                  rotulo.setStyle("font-size: 22px; line-height: normal;");
                }
                vlayout.appendChild(rotulo);
              }
            }
            row.appendChild(vlayout);
            row.setStyle("height: 60px; margin: 1px; padding: 1px;");
          }
        }
      });
    } catch (final Exception e) {
      e.printStackTrace();
    }
  }

  public Grid getGrid() {
    return this.grid;
  }

  public void setGrid(final Grid grid) {
    this.grid = grid;
  }
}