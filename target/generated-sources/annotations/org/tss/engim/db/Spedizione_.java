package org.tss.engim.db;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import org.tss.engim.db.MerceSpedizione;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2018-07-12T12:18:54")
@StaticMetamodel(Spedizione.class)
public class Spedizione_ { 

    public static volatile SingularAttribute<Spedizione, Integer> numero;
    public static volatile SingularAttribute<Spedizione, Date> data;
    public static volatile CollectionAttribute<Spedizione, MerceSpedizione> merceSpedizioneCollection;
    public static volatile SingularAttribute<Spedizione, Integer> id;

}