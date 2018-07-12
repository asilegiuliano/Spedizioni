package org.tss.engim.db;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import org.tss.engim.db.Merce;
import org.tss.engim.db.Spedizione;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2018-07-12T09:59:52")
@StaticMetamodel(MerceSpedizione.class)
public class MerceSpedizione_ { 

    public static volatile SingularAttribute<MerceSpedizione, Integer> id;
    public static volatile SingularAttribute<MerceSpedizione, Integer> quantita;
    public static volatile SingularAttribute<MerceSpedizione, Spedizione> idSpedizione;
    public static volatile SingularAttribute<MerceSpedizione, Merce> idMerce;

}