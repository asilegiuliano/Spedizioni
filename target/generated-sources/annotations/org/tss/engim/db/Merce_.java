package org.tss.engim.db;

import java.math.BigDecimal;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import org.tss.engim.db.MerceSpedizione;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2018-07-13T15:59:30")
@StaticMetamodel(Merce.class)
public class Merce_ { 

    public static volatile SingularAttribute<Merce, String> descrizione;
    public static volatile SingularAttribute<Merce, BigDecimal> peso;
    public static volatile CollectionAttribute<Merce, MerceSpedizione> merceSpedizioneCollection;
    public static volatile SingularAttribute<Merce, String> codice;
    public static volatile SingularAttribute<Merce, Integer> id;

}