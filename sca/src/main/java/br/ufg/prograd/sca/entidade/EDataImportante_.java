package br.ufg.prograd.sca.entidade;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2015-09-17T09:03:35.909-0300")
@StaticMetamodel(EDataImportante.class)
public class EDataImportante_ {
	public static volatile SingularAttribute<EDataImportante, Integer> id;
	public static volatile SingularAttribute<EDataImportante, Date> data;
	public static volatile SingularAttribute<EDataImportante, String> mensagem;
}
