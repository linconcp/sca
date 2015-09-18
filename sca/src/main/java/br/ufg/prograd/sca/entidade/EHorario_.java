package br.ufg.prograd.sca.entidade;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2015-09-17T10:34:13.389-0300")
@StaticMetamodel(EHorario.class)
public class EHorario_ {
	public static volatile SingularAttribute<EHorario, Integer> id;
	public static volatile SingularAttribute<EHorario, Date> dataInicio;
	public static volatile SingularAttribute<EHorario, Date> dataFim;
}
