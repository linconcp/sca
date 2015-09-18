package br.ufg.prograd.sca.entidade;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2015-09-17T09:03:35.841-0300")
@StaticMetamodel(EAviso.class)
public class EAviso_ {
	public static volatile SingularAttribute<EAviso, Integer> id;
	public static volatile SingularAttribute<EAviso, Date> dataInicial;
	public static volatile SingularAttribute<EAviso, Date> dataFinal;
	public static volatile SingularAttribute<EAviso, String> mensagem;
}
